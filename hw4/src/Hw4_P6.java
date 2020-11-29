import java.util.*;

/**
 * @author Donghang He
 * @date 2020/11/22 10:24 下午
 */
public class Hw4_P6 {
    public static void main(String[] args) {
        insertTimeAndSearchTime();
    }

    public static void insertTimeAndSearchTime() {
        // create HashMap instance, ArrayList instance, LinkedList instance
        HashMap<Integer, Integer> myMap = new HashMap<>();
        ArrayList<Integer> myArrayList = new ArrayList<>();
        LinkedList<Integer> myLinkedList = new LinkedList<>();

        long startTime, endTime, elapsedTime;
        // long array for saving insert time and search time
        // sequence of array map, arrayList, linkedList
        long[] insertTime = new long[3];
        long[] searchTime = new long[3];
        // int array for store random numbers
        Integer[] insertKeys = new Integer[100000];
        Integer[] searchKeys = new Integer[100000];
        Random r = new Random();
        // repeat for 10 times
        for (int i = 0; i < 10; i++) {
            // generate a sequence of random numbers
            r.setSeed(System.currentTimeMillis());
            // store the random integers
            for (int j = 0; j < 100000; j++) {
                insertKeys[j] = r.nextInt(100000) + 1;
            }
            // empty the hashmap, arraylist and linkedlist
            myMap.clear();
            myArrayList.clear();
            myLinkedList.clear();

            // HashMap insert and measure total time
            startTime = System.currentTimeMillis();
            for (int m = 0; m < 100000; m++) {
                myMap.put(insertKeys[m], m);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            insertTime[0] += elapsedTime;

            // ArrayList insert and measure total time
            startTime = System.currentTimeMillis();
            myArrayList.addAll(Arrays.asList(insertKeys).subList(0, 100000));
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            insertTime[1] += elapsedTime;

            // LinkedList insert and measure total time
            startTime = System.currentTimeMillis();
            myLinkedList.addAll(Arrays.asList(insertKeys).subList(0, 100000));
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            insertTime[2] += elapsedTime;

            // generate random numbers for search keys
            r.setSeed(System.currentTimeMillis());
            for (int j = 0; j < 100000; j++) {
                searchKeys[j] = r.nextInt(200000) + 1;
            }

            // HashMap search and measure total time
            startTime = System.currentTimeMillis();
            for (int m = 0; m < 100000; m++) {
                myMap.containsKey(searchKeys[m]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            searchTime[0] += elapsedTime;

            // ArrayList search and measure total time
            startTime = System.currentTimeMillis();
            for (int m = 0; m < 100000; m++) {
                myArrayList.contains(searchKeys[m]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            searchTime[1] += elapsedTime;

            // LinkedList search and measure total time
            startTime = System.currentTimeMillis();
            for (int m = 0; m < 100000; m++) {
                myLinkedList.contains(searchKeys[m]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            searchTime[2] += elapsedTime;
        }
        System.out.println("Number of keys = " + insertKeys.length + "\n");
        System.out.println("HashMap average total insert time = " + insertTime[0]);
        System.out.println("ArrayList average total insert time = " + insertTime[1]);
        System.out.println("LinkedList average total insert time = " + insertTime[2] + "\n");
        System.out.println("HashMap average total search time = " + searchTime[0]);
        System.out.println("ArrayList average total search time = " + searchTime[1]);
        System.out.println("LinkedList average total search time = " + searchTime[2]);
    }
}
