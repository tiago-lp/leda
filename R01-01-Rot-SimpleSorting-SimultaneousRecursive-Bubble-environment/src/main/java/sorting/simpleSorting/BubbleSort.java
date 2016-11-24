package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {
	/**
	 * BubbleSort
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
			bubbleSort(array, leftIndex, rightIndex);
		}
	}
		
	public void bubbleSort(T[] array, int leftIndex, int rightIndex) {
		boolean achou;
		do
		{
			achou = false;
			for(int j = leftIndex; j < rightIndex; j++) {
				if(array[j].compareTo(array[j + 1]) > 0) {
					achou = true;
					Util.swap(array, j, j + 1);
				}
			}
		}while(achou);
	}
}

