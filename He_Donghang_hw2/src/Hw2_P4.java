import java.util.Arrays;

/**
 * @author Donghang He
 * @date 2020/11/8 3:32 下午
 */
public class Hw2_P4 {
    /**
     * reverse first n numbers in a array
     * @param a array of some numbers
     * @param n numbers needs to be reverse
     */
    public static void reverseFirstN(int[] a, int n) {
        reverseN(a, 0, n - 1);
    }

    /**
     * reverse method, reverse the first and last number
     * @param a array
     * @param first start flag
     * @param last end flag
     */
    public static void reverseN(int[] a, int first, int last) {
        if (first < last) {
            int temp = a[first];
            a[first] = a[last];
            a[last] = temp;
            reverseN(a, first + 1, last - 1);
        }
    }

    /**
     * move even before odd number method
     * @param a array
     */
    public static void evenBeforeOdd(int[] a) {
        rearrange(a, 0, a.length - 1);
    }

    /**
     * rearrange method, check from the first to end if current is odd change to the last one
     * @param a array
     * @param first first number flag
     * @param end last number flag
     */
    public static void rearrange(int[] a, int first, int end) {
        if (first != end) {
            for (int i = first; i <= end; i++) {
                if (a[i] % 2 != 0) {
                    int temp = a[i];
                    a[i] = a[end];
                    a[end] = temp;
                    rearrange(a, i ,end - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (i + 1) * 10;
        }

        System.out.println("Initial array: ");
        System.out.println(Arrays.toString(a));
        System.out.println();

        int[] intArrayCopy;
        intArrayCopy = a.clone();

        int N = 2;
        reverseFirstN(intArrayCopy, N);
        System.out.println("\nAfter reversing first " + N + " elements: ");
        System.out.println(Arrays.toString(intArrayCopy));
        System.out.println();

        intArrayCopy = a.clone();
        N = 7;
        reverseFirstN(intArrayCopy, N);
        System.out.println("\nAfter reversing first " + N + " elements: ");
        System.out.println(Arrays.toString(intArrayCopy));
        System.out.println();

        int[] b = {10, 15, 20, 30, 25, 35, 40, 45};
        System.out.println("\nBefore rearrange: ");
        System.out.println(Arrays.toString(b));
        System.out.println();

        evenBeforeOdd(b);
        System.out.println("\nAfter rearrange: ");
        System.out.println(Arrays.toString(b));
        System.out.println();
    }
}
