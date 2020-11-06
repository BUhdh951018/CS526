import java.util.Arrays;
/**
 * @author donghanghe
 * @date 2020-11-3
 */
public class Hw1_P1 {
	/* stats method: to calculate the average, minimum and maximum of the integer array
	 * input argument: an array of integer
	 * output argument: void method no output
	 */
	public static void stats(int[] a) {
		// set variables to save the result
		float avg = 0, total = 0;
		// set min the biggest int and max the smallest int
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		// loop over the array to get the total, min and max
		for (int j : a) {
			total += j;
			if (j < min)
				min = j;
			if (j > max)
				max = j;
		}
		// calculate the average
		avg = total / a.length;
		// print the results
		System.out.println("average = " + String.format("%.2f", avg) + ", min = " + min + ", max = " + max);
	}
	/*
	 * subarray method: to get a subarray
	 * input argument: a: an array of integer, from: first element index, to: last element index
	 * output argument: void method no output
	 */
	public static void subarray(int[] a, int from, int to) {
		// error check w/o using Java's exception handling
		try {
			if (from < 0 || to >= a.length) {
				System.out.println("Index out of bound");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println();
		}
		// create a int array for subarray
		int[] subA = new int[to - from + 1];
		// user arraycopy method to get the subarray between from and to
		if (to + 1 - from >= 0) System.arraycopy(a, from, subA, 0, to + 1 - from);
		// print the subarray
		System.out.println("The subarray, from index " + from + " to index " + to +
				", is: " + Arrays.toString(subA));
	}
	
	public static void main(String[] args) {
		// test 
		int[] a = {15, 25, 10, 65, 30, 55, 65};
		System.out.println("\nGiven array is: " + Arrays.toString(a));
		System.out.println();
		stats(a);
		System.out.println();
		subarray(a, 1, 4);
		
		// test with other arrays
		int[] b = {30, 27, 90, 140, 308, 175, 269, 144, 388};
		System.out.println("\nGiven array is: " + Arrays.toString(b));
		System.out.println();
		stats(b);
		System.out.println();
		subarray(a, 2, 6);
	}
}
