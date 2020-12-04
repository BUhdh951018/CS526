import java.util.Arrays;
import java.util.Random;

/**
 * @author Donghang He
 * @date 2020/11/29 3:29 下午
 */
public class Hw5_P7 {
    public static void main(String[] args) {
        int temp = 0;
        for (int i = 0; i < 10; i++) {
            temp += 10000;
            System.out.println("Length: " + temp);
            sorting(temp);
        }
    }

    public static void sorting(int n) {
        long startTime, endTime, elapsedTime;
        int[] random = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            random[i] = r.nextInt(100000) + 1;
        }

        int[] test1 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        insertionSort(test1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of insertion sort: " + elapsedTime);

        int[] test2 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        mergeSort(test2);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of merge sort: " + elapsedTime);

        int[] test3 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        quickSortInPlace(test3, 0, test3.length - 1);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of quick sort: " + elapsedTime);

        int[] test4 = Arrays.copyOf(random, n);
        startTime = System.currentTimeMillis();
        heapSort(test4);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time of heap sort: " + elapsedTime);
    }

    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int k = 1; k < n; k++) {
            int cur = data[k];
            int j = k;
            while (j > 0 && data[j - 1] > cur) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = cur;
        }
    }

    public static void merge(int[] s1, int[] s2, int[] s) {
        int i = 0, j = 0;
        while (i + j < s.length) {
            if (j == s2.length || (i < s1.length && (s1[i] - s2[i]) < 0))
                s[i + j] = s1[i++];
            else
                s[i + j] = s2[j++];
        }
    }

    public static void mergeSort(int[] s) {
        int n = s.length;
        if (n < 2)
            return;

        int mid = n / 2;
        int[] s1 = Arrays.copyOfRange(s, 0, mid);
        int[] s2 = Arrays.copyOfRange(s, mid, n);
        mergeSort(s1);
        mergeSort(s2);
        merge(s1, s2, s);
    }

    public static void quickSortInPlace(int[] s, int a, int b) {
        if (a >= b)
            return;
        int left = a;
        int right = b - 1;
        int pivot = s[b];
        int temp;
        while (left < right) {
            while (left <= right && (s[left] - pivot) < 0)
                left++;
            while (left <= right && (s[right] - pivot) > 0)
                right--;
            if (left <= right) {
                temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
        temp = s[left];
        s[left] = s[b];
        s[b] = temp;
        quickSortInPlace(s, a, left - 1);
        quickSortInPlace(s, left + 1, b);
    }

    public static void heapSort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
