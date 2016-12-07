package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(array != null) {
			if (leftIndex < 0) {
				leftIndex = 0;
			}
			if (rightIndex > array.length - 1) {
				rightIndex = array.length - 1;
			}
			
			rightIndex = Util.trataNulos(array, leftIndex, rightIndex);
			
			if((array.length > 1) && (leftIndex < rightIndex)) {
				
				extendedCountingSort(array, leftIndex, rightIndex);
			}
		}
	}

	public void extendedCountingSort(Integer[] array, int leftIndex, int rightIndex) {
		
		Integer max = array[leftIndex];
		Integer min = array[leftIndex];
		
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			if(array[i].compareTo(max) > 0) {
				max = array[i];
			}
			if(array[i].compareTo(min) < 0) {
				min = array[i];
			}
		}
 
		int[] frequencia = new int[max - min + 1];
 
		for(int i = leftIndex; i <= rightIndex; i++){
			frequencia[array[i] - min]++;
		}
 
		for(int i = 1; i < frequencia.length; i++){
			frequencia[i] += frequencia[i - 1];
		}
 
		Integer[] b = new Integer[rightIndex - leftIndex + 1];
 
		for(int i = rightIndex; i >= leftIndex; i--){
			b[frequencia[array[i] - min] - 1] = array[i];
			frequencia[array[i] - min]--;
		}
 
		for(int i = leftIndex; i <= rightIndex; i++){
			array[i] = b[i - leftIndex];
		}
	}
}
