package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < 0) {
			leftIndex = 0;
		}
		if(rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}
		
		rightIndex = Util.trataNulos(array, leftIndex, rightIndex);
		
		if((array != null) && (array.length > 0) && (leftIndex < rightIndex) && (array instanceof Comparable[])) {
			mergeSort(array, leftIndex, rightIndex);
		}
	}

	public void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			
			int med = (leftIndex + rightIndex) / 2;
			mergeSort(array, leftIndex, med);
			mergeSort(array, med+1, rightIndex);
			merge(array, leftIndex, med, rightIndex);
		}
	}

	public void merge(T[] array, int leftIndex, int med, int rightIndex) {
		
		T[] auxiliar = (T[]) new Comparable[rightIndex + 1];
		
		for(int i = leftIndex; i <= rightIndex; i++) {
			auxiliar[i] = array[i];
		}

		int i = leftIndex;
		int j = med+1;
		int k = leftIndex;

		while(i <= med && j <= rightIndex) {
			if(auxiliar[i].compareTo(auxiliar[j]) < 0) {
				array[k] = auxiliar[i];
				i++;
			} else {
				array[k] = auxiliar[j];
				j++;
			}
			k++;
		}

		while(i <= med) {
			array[k++] = auxiliar[i++];	
		}

		while(j <= rightIndex) {
			array[k++] = auxiliar[j++];
		}
	}
}
