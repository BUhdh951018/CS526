import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Donghang He
 * @date 2020/11/17 3:28 下午
 */
public class project {

    // read file name
    private static final String input_file1 = "src/graph_input.txt";
    private static final String input_file2 = "src/direct_distance.txt";

    // hash map for store two txt file
    private static final HashMap<String, Integer> graphInput = new HashMap<>();
    private static final HashMap<String, Integer> directDistance = new HashMap<>();
    // array list for save all cities
    private static final ArrayList<String> city = new ArrayList<>();

    private static final Scanner input = new Scanner(System.in);

    // read data from file
    public static void readData() {
        try {
            // two buffer reader for two files
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(input_file1));
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(input_file2));

            String line1;
            String[][] graph = new String[22][22];
            int row = 0;
            while((line1 = bufferedReader1.readLine()) != null) {
                String[] data = line1.split(" +");
                System.arraycopy(data, 0, graph[row], 0, data.length);
                row++;
            }

            for (int i = 1; i < 22; i++) {
                city.add(graph[i][0]);
                for (int j = 1; j < 22; j++) {
                    StringBuilder citys = new StringBuilder();
                    citys.append(graph[0][j]);
                    citys.append(graph[i][0]);
                    int weight = Integer.parseInt(graph[i][j]);
                    if (weight > 0) {
                        graphInput.put(citys.toString(), weight);
                    }
                }
            }

            String line2;
            while ((line2 = bufferedReader2.readLine()) != null) {
                String[] data = line2.split(" ");
                directDistance.put(data[0], Integer.parseInt(data[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputStartCity() {
        System.out.println("Please enter a start node (enter exit to finish): ");
        String inputNode = input.nextLine();
        while (!inputNode.equalsIgnoreCase("exit")) {
            if (city.contains(inputNode.toUpperCase())) {
                findWay(inputNode.toUpperCase());
            } else {
                System.out.println("The node you input is not a valid start node, please try again!!");
            }
            inputStartCity();
        }
        System.out.println("Goodbye");
        System.exit(0);
    }

    public static void findWay(String input) {
        System.out.println("Algorithm 1");
        Algorithm1 algorithm1 = new Algorithm1(input, graphInput, directDistance);
        algorithm1.getPath();

        System.out.println("Algorithm 2");
        Algorithm2 algorithm2 = new Algorithm2(input, graphInput, directDistance);
        algorithm2.getPath();
    }

    public static void main(String[] args) {
        readData();

        inputStartCity();
    }
}
