/**
 * 
 */
package com.xrds.basic.component.common.util;

/**
 * @author GSZY
 *
 */
public class SortUtil {

	public static void quickSort(int[] arr, int min, int max) {
		if (arr == null || min >= max || arr.length < 1)
			return;

	}

	// private static int partition(int[] arr, int min, int max) {
	// int tmp = arr[max];
	// while (min < max) {
	// while (min < max && arr[max] >= tmp) {
	// max--;
	// }
	// while (min < max && arr[min] <= tmp) {
	// min++;
	// }
	// if (max > min) {
	// swap(arr, min, max);
	//
	// }
	//
	// arr[min] = arr[max];
	//
	// }
	//
	// }

	/**
	 * 交换
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;

	}

}
