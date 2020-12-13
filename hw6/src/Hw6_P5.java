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
        // array list for save name
        ArrayList<String> name = new ArrayList<>();
        // hash map for saving friend list of a user, eg: Jhon -> friend: Mary, Mike, Tom
        HashMap<String, ArrayList<String>> friendMap = new HashMap<>();
        // read friend input to friend map
        readFile(name, friendMap);

        // adjacency matrix for saving friend relationship
        String[][] adjacencyMatrix = new String[name.size() + 1][name.size() + 1];
        // set the matrix for first row and first column with user name
        getAdjMatrix(adjacencyMatrix, name);

        // hash map for saving the user sequence (user index), eg: jhon -> 1
        HashMap<String, Integer> nameMap = new HashMap<>();
        // set friend relationship in matrix
        setFriend(name, friendMap, adjacencyMatrix, nameMap);

        // print the matrix
        printAdjMatrix(adjacencyMatrix);

        // operation menu
        menu(name, nameMap, adjacencyMatrix);
    }

    public static void readFile(ArrayList<String> name, HashMap<String, ArrayList<String>> friendMap) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/friends_input.txt"));
        String str;
        // read file
        while ((str = in.readLine()) != null) {
            // split each row with ", "
            String[] data = str.split(", +");
            // if not exist in name list add user to list
            for (int i = 0; i < 2; i++) {
                if (!name.contains(data[i])) {
                    name.add(data[i]);
                }
            }
            // if user in friend map has a friend list and new friend to list
            if (friendMap.containsKey(data[0])) {
                friendMap.get(data[0]).add(data[1]);
            } else { // if not create a array list and save it in the map
                ArrayList<String> friendList = new ArrayList<>();
                friendList.add(data[1]);
                friendMap.put(data[0], friendList);
            }
        }
    }

    public static void getAdjMatrix(String[][] adjMatrix, ArrayList<String> name) {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                // set each element in the 2D array empty string
                adjMatrix[i][j] = "";
                // set name by sequence in array list name
                if (i == 0 && j != 0) {
                    // if it is the first row set the name from the second element
                    adjMatrix[i][j] = name.get(j - 1);
                }
                if (i != 0 && j == 0) {
                    // if it is the first element in each row but not in first row set the name
                    adjMatrix[i][j] = name.get(i - 1);
                }
            }
        }
    }

    public static void setFriend(ArrayList<String> name, HashMap<String, ArrayList<String>> friendMap, String[][] adjMatrix, HashMap<String, Integer> nameMap) {
        // set name and index in hash map according to the sequence in array list name
        for (int i = 0; i < name.size(); i++) {
            nameMap.put(name.get(i), i + 1);
        }
        // for each element in friend map, set the friend relationship in matrix
        friendMap.forEach((k, v) -> {
            // row for map key
            Integer row = nameMap.get(k);
            for (String s : v) {
                // col for map value
                Integer col = nameMap.get(s);
                // set both mike,jhon and jhon,mike
                adjMatrix[row][col] = "1";
                adjMatrix[col][row] = "1";
            }
        });
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
                // menu
                System.out.println("-------enter-------");
                System.out.println("Main Menu\n");
                System.out.println("Search options:");
                System.out.println("1. Friends of a person\n2. Friend or not?\n3. Exit");
                Scanner in = new Scanner(System.in);
                int type = in.nextInt();

                if (type == 1) {
                    // get friend of a person
                    ArrayList<String> friendList = new ArrayList<>();
                    System.out.print("Please enter a name, first letter must be uppercase:");
                    // get person name
                    String personName = in.next();
                    // check whether exist
                    if (nameMap.containsKey(personName)) {
                        // get person's friend
                        getFriend(nameMap, adjMatrix, name, friendList, personName);
                    } else {
                        System.out.println("name invalid!");
                        continue;
                    }
                    // print friend list
                    System.out.printf("Friend of %s are : %s\n%n", personName, friendList);

                } else if (type == 2) {
                    // check friend or not
                    System.out.println("Enter 2 names like(amy,mike) without space, first letter must be uppercase:");
                    String names = in.next();
                    checkFriend(names, nameMap, adjMatrix);
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

    public static void getFriend(HashMap<String, Integer> nameMap, String[][] adjMatrix, ArrayList<String> name,
                                 ArrayList<String> friendList, String person) {
        // get person index
        int index = nameMap.get(person);
        // check person row in matrix
        for (int i = 0; i < adjMatrix[index].length; i++) {
            // if has friend
            if (adjMatrix[index][i].equals("1")) {
                // add to list
                friendList.add(name.get(i - 1));
                // check this friend's friend for indirect friend
                for (int j = 0; j < adjMatrix[i].length; j++) {
                    // if has friend add to list
                    if (adjMatrix[i][j].equals("1")) {
                        // check not person himself
                        if (!name.get(j - 1).equals(person))
                            friendList.add(name.get(j - 1));
                    }
                }
            }
        }
    }

    public static void checkFriend(String names, HashMap<String, Integer> nameMap, String[][] adjMatrix) {
        // split input name by ","
        String[] friendName = names.split(",");
        // set check flag
        String flag = "No";
        // if both name are the key in the friend map
        if (nameMap.containsKey(friendName[0]) && nameMap.containsKey(friendName[1])) {
            // if the element of the correspond row and column is 1
            int row = nameMap.get(friendName[0]);
            int col = nameMap.get(friendName[1]);
            if (adjMatrix[row][col].equals("1")) {
                // set flag yes
                flag = "Yes";
            } else {
                // check indirect friend
                for (int i = 0; i < adjMatrix[row].length; i++) {
                    // if they both are friend with the third person
                    if (adjMatrix[row][i].equals("1") && adjMatrix[i][col].equals("1")) {
                        // they are friend
                        flag = "Yes";
                        break;
                    }
                }
            }
            System.out.println(flag);
        } else {
            System.out.println("name invalid!");
        }
    }
}