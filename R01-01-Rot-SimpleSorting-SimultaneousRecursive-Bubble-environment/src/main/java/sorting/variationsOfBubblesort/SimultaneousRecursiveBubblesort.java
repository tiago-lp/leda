package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * Este algoritmo eh RECURSIVO e aplica o bubblesort na entrada empurrando os 
 * elementos grandes apra o final e trazendo os elementos menores para o início.
 * Dessa forma, ao final da primeira iteração, o menor elemento está na primeira
 * posição e o maioe elemento está na ultima. 
 * Nas proximas iterações as posicoes dos elementos das iterações anteriores
 * são excluidas do espaço de varredura do array.
 */
public class SimultaneousRecursiveBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	
	public void sort(T[] array, int leftIndex, int rightIndex) {		
		if(leftIndex < 0) {
			leftIndex = 0;
		}
		if(rightIndex > array.length - 1) {
			rightIndex = array.length - 1;
		}
		
		rightIndex = Util.trataNulos(array, leftIndex, rightIndex);
		
		if((array != null) && (array.length > 0) && (leftIndex < rightIndex)) {
			simultaneousRecursiveBubbleSort(array, leftIndex, rightIndex);
		}
	}

	public void simultaneousRecursiveBubbleSort(T[] array, int leftIndex, int rightIndex) {
		if((leftIndex < rightIndex) && (rightIndex > 0)) {
			int fim = rightIndex;
			for(int i = leftIndex; i < rightIndex; i++) {
			
				if(array[i].compareTo(array[i + 1]) > 0) {
					Util.swap(array, i, i + 1);
				}
				
				if((fim > leftIndex) && (array[fim].compareTo(array[fim - 1]) < 0)) {
					Util.swap(array, fim, fim - 1);
				}
				fim--;
			}
			simultaneousRecursiveBubbleSort(array, leftIndex + 1, rightIndex - 1);
		}	
	}	
}