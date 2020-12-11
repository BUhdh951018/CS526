import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Donghang He
 * @date 2020/12/6 3:44 下午
 */
public class Hw6_P5 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/friends_input.txt"));
        String str;
        ArrayList<String> name = new ArrayList<String>();
        HashMap<String, ArrayList<String>> friendMap = new HashMap<>();
        while ((str = in.readLine()) != null) {
            String[] data = str.split(", ");
            for (int i = 0; i < 2; i++) {
                if (!check(name, data[i])) {
                    name.add(data[i]);
                }
            }
            if (friendMap.containsKey(data[0])) {
                friendMap.get(data[0]).add(data[1]);
            } else {
                ArrayList<String> friendList = new ArrayList<>();
                friendList.add(data[1]);
                friendMap.put(data[0], friendList);
            }
        }
        String[][] adjacencyMatrix = new String[name.size() + 1][name.size() + 1];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                adjacencyMatrix[i][j] = "";
                if (i == 0 && j != 0) {
                    adjacencyMatrix[i][j] = name.get(j - 1);
                }
                if (i != 0 && j == 0) {
                    adjacencyMatrix[i][j] = name.get(i - 1);
                }
            }
        }
        HashMap<String, Integer> nameMap = new HashMap<>();
        for (int i = 0; i < name.size(); i++) {
            nameMap.put(name.get(i), i + 1);
        }
        friendMap.forEach((k, v) -> {
            Integer row = nameMap.get(k);
            for (String s : v) {
                Integer col = nameMap.get(s);
                adjacencyMatrix[row][col] = "1";
                adjacencyMatrix[col][row] = "1";
            }
        });
        printAdjMatrix(adjacencyMatrix);
        menu(name, nameMap, adjacencyMatrix);
    }

    public static boolean check(ArrayList<String> friend, String name) {
        boolean flag = false;
        for (int i = 0; i < friend.size(); i++) {
            if (friend.contains(name)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void printAdjMatrix(String[][] matrix) {
        System.out.println("\nPrint adjacency matrix\n");
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%10s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void menu(ArrayList<String> name, HashMap<String, Integer> nameMap, String[][] adjMatrix) {
        while (true) {
            try {
                System.out.println("-------enter-------");
                System.out.println("Main Menu\n");
                System.out.println("Search options:");
                System.out.println("1. Friends of a person\n2. Friend or not?\n3. Exit");
                Scanner in = new Scanner(System.in);
                int type = in.nextInt();

                if (type == 1) {
                    ArrayList<String> friendList = new ArrayList<>();
                    System.out.print("Please enter a name:");
                    String friendName = in.next();
                    if (nameMap.containsKey(friendName)) {
                        int index = nameMap.get(friendName);
                        for (int i = 0; i < adjMatrix[index].length; i++) {
                            if (adjMatrix[index][i].equals("1")) {
                                friendList.add(name.get(i - 1));
                            }
                        }
                    } else {
                        System.out.println("name invalid!");
                        continue;
                    }
                    System.out.printf("Friend of %s are : %s\n%n", friendName, friendList);

                } else if (type == 2) {
                    System.out.println("Enter 2 names like(amy,mike) without space:");
                    String names = in.next();
                    String[] friendName = names.split(",");
                    if (nameMap.containsKey(friendName[0]) && nameMap.containsKey(friendName[1])) {
                        if (adjMatrix[nameMap.get(friendName[0])][nameMap.get(friendName[1])] == "1") {
                            System.out.println("Yes");
                        } else {
                            System.out.println("No");
                        }
                    } else {
                        System.out.println("name invalid!");
                    }
                } else if (type == 3) {
                    System.out.println("-------exit-------");
                    break;
                } else {
                    System.out.println("Invalid input, please enter number between 1-3");
                }
            } catch (Exception e) {
                System.out.println("Input invalid!");
            }
        }
    }
}