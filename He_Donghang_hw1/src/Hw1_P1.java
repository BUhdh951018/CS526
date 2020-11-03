import java.util.Arrays;

/**
 * @author donghanghe
 * @date 2020-11-3
 */

public class Hw1_P1 {

	public static void stats(int[] a) {
		float avg = 0, total = 0;
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int j : a) {
			total += j;
			min = Math.min(min, j);
			max = Math.max(max, j);
		}
		avg = total / a.length;

		System.out.println("average = " + String.format("%.2f", avg) + ", min = " + min + ", max = " + max);
	}
	
	public static void subarray(int[] a, int from, int to) {
		// error check w/o using Java's exception handling
		try {
			if (from < 0 || to >= a.length) {
				System.out.println("Index out of bound");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println();
		}

		int[] subA = new int[to - from + 1];
		if (to + 1 - from >= 0) System.arraycopy(a, from, subA, 0, to + 1 - from);
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
	}

}
