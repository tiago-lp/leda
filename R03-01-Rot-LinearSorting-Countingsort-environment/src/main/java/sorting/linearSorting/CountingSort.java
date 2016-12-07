package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

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
				
				countingSort(array, leftIndex, rightIndex);
			}
		}
	}

	public void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		
		Integer k = array[leftIndex];
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			if(array[i].compareTo(k) > 0) {
				k = array[i];
			}
		}
		
        int[] c = new int[k + 1];
        // frequência
        for (int i = leftIndex; i <= rightIndex; i++) {
            c[array[i]] += 1;
        }
        
        // cumulativa
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i-1];
        }

        int[] b = new int[rightIndex + 1];
        for (int i = rightIndex; i >= leftIndex; i--) {
            b[c[array[i]] -1] = array[i];
            c[array[i]] -= 1;
        }

        int ind = 0;
        for (int i = leftIndex; i <= rightIndex; i++){
			array[i] = b[ind];
			ind++;
		}
    
	}
}