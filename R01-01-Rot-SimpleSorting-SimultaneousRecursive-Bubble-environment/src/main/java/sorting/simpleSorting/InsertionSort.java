package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * InsertionSort
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < 0) {
			leftIndex = 0;
		}
		if(rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}
		
		rightIndex = Util.trataNulos(array, leftIndex, rightIndex);
		
		if((array != null) && (array.length > 0) && (leftIndex < rightIndex)) {
			insertionSort(array, leftIndex, rightIndex);
		}
	}
	
	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		T atual;
		int j;
		for(int i = leftIndex + 1; i <= rightIndex; i++) {
			atual = array[i];
			j = i-1;
			while(j >= leftIndex && (atual.compareTo(array[j]) < 0)) {
				array[j+1] = array[j];
				j--;
			}
			array[j + 1] = atual;
		}
	}
}
