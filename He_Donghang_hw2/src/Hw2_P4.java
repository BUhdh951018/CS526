import java.util.Arrays;

/**
 * @author Donghang He
 * @date 2020/11/8 3:32 下午
 */
public class Hw2_P4 {
    public static void reverseFirstN(int[] a, int n) {
        reverseN(a, 0, n - 1);
    }

    public static void reverseN(int[] a, int first, int last) {
        if (first < last) {
            int temp = a[first];
            a[first] = a[last];
            a[last] = temp;
            reverseN(a, first + 1, last - 1);
        }
    }

    public static void evenBeforeOdd(int[] a) {
        rearrange(a, 0, a.length - 1);
    }

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
        int[] a = {1, 2, 3, 4, 5};
        reverseFirstN(a, 3);
        System.out.println(Arrays.toString(a));

        int[] b = {10, 15, 20, 30, 25, 35, 40, 45};
        evenBeforeOdd(b);
        System.out.println(Arrays.toString(b));
    }
}
