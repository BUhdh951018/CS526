import java.util.Arrays;
import java.util.Random;

/**
 * @author Donghang He
 * @date 2020/11/29 3:29 下午
 */
public class Hw5_P7 {
    public static void main(String[] args) {
        // array size
        int temp = 0;
        // loop for 10 times
        for (int i = 0; i < 10; i++) {
            temp += 10000;
            System.out.println("Length: " + temp);
            sorting(temp);
        }
    }

    /**
     * sorting function
     * @param n, array length
     */
    public static void sorting(int n) {
        long startTime, endTime, elapsedTime;
        int[] random = new int[n];
        // insert random int number to random array
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            random[i] = r.nextInt(100000) + 1;
        }
        // initial unsorted array
        int[] test1 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        // insertion sort
        insertionSort(test1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of insertion sort: " + elapsedTime);
        // initial unsorted array
        int[] test2 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        // merge sort
        mergeSort(test2);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of merge sort: " + elapsedTime);
        // initial unsorted array
        int[] test3 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        // quick sort
        quickSortInPlace(test3, 0, test3.length - 1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of quick sort: " + elapsedTime);
        // initial unsorted array
        int[] test4 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        // heap sort
        heapSort(test4);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of heap sort: " + elapsedTime);
    }

    public static void insertionSort(int[] data) {
        int n = data.length;
        // begin with second character
        for (int k = 1; k < n; k++) {
            // time to insert cur=data[k]
            int cur = data[k];
            // find correct index j for cur
            int j = k;
            // thus, data[j-1] must go after cur
            while (j > 0 && data[j - 1] > cur) {
                // slide data[j-1] rightward
                data[j] = data[j - 1];
                // and consider previous j for cur
                j--;
            }
            // this is the proper place for cur
            data[j] = cur;
        }
    }

    public static void merge(int[] s1, int[] s2, int[] s) {
        int i = 0, j = 0;
        while (i + j < s.length) {
            if (j == s2.length || (i < s1.length && (s1[i] - s2[i]) < 0))
                // copy ith element of s1 and increment i
                s[i + j] = s1[i++];
            else
                // copy jth element of s2 and increment j
                s[i + j] = s2[j++];
        }
    }

    public static void mergeSort(int[] s) {
        int n = s.length;
        // array is trivially sorted
        if (n < 2)
            return;
        // divide
        int mid = n / 2;
        // copy of first half
        int[] s1 = Arrays.copyOfRange(s, 0, mid);
        // copy of second half
        int[] s2 = Arrays.copyOfRange(s, mid, n);
        // sort copy of first half
        mergeSort(s1);
        // sort copy of second half
        mergeSort(s2);
        // merge results
        // merge sorted halves back into original
        merge(s1, s2, s);
    }

    public static void quickSortInPlace(int[] s, int a, int b) {
        // subarray is trivially sorted
        if (a >= b)
            return;
        int left = a;
        int right = b - 1;
        int pivot = s[b];
        // temp object used for swapping
        int temp;
        while (left < right) {
            // scan until reaching value equal or larger than pivot (or right marker)
            while (left <= right && (s[left] - pivot) < 0)
                left++;
            // scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && (s[right] - pivot) > 0)
                right--;
            // indices did not strictly cross
            if (left <= right) {
                // so swap values and shrink range
                temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
        // put pivot into its final place (currently marked by left index)
        temp = s[left];
        s[left] = s[b];
        s[b] = temp;
        // make recursive calls
        quickSortInPlace(s, a, left - 1);
        quickSortInPlace(s, left + 1, b);
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        // build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // one by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    /**
     * to heapify a subtree rooted with node i which is an index in arr[] n is size od heap
     * @param arr
     * @param n
     * @param i
     */
    public static void heapify(int[] arr, int n, int i) {
        // initialize largest as root
        int largest = i;
        // left
        int l = 2 * i + 1;
        // right
        int r = 2 * i + 2;
        // if left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
        // if right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // if largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}
