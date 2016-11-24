package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {
	/**
	 * SelectionSort
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
			selectionSort(array, leftIndex, rightIndex);
		}
	}
	
	public void selectionSort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex; i < rightIndex; i++) {
			int menor = i;
			for(int j = i + 1; j <= rightIndex; j++) {
				if(array[j].compareTo(array[menor]) < 0) {
					menor = j;
				}
			}
			Util.swap(array, i, menor);
		}
	}
}
