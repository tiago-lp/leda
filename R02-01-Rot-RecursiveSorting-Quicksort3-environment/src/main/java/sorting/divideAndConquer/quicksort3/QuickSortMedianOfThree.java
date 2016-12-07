package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte: 1. Comparar o elemento mais a
 * esquerda, o central e o mais a direita do intervalo. 2. Ordenar os elemento,
 * tal que: A[left] < A[center] < A[right]. 3. Adotar o A[center] como pivô. 4.
 * Colocar o pivô na penúltima posição A[right-1]. 5. Aplicar o particionamento
 * considerando o vetor menor, de A[left+1] até A[right-1]. 6. Aplicar o
 * algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
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
			quickSortMedianOfThree(array, leftIndex, rightIndex);
		}
	}

	private void quickSortMedianOfThree(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			if((rightIndex - leftIndex) <= 3){
				bubble(array,leftIndex, rightIndex);
			} else{ 
				int median = med(array, leftIndex, rightIndex);
				Util.swap(array, median, rightIndex-1);
				int pos_pivot = particiona(array, leftIndex, rightIndex);
				quickSortMedianOfThree(array, leftIndex, pos_pivot - 1);
				quickSortMedianOfThree(array, pos_pivot + 1, rightIndex);
			}
		}
	}

	public int particiona(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;
		for(int j = leftIndex + 1; j < rightIndex; j++) {
			if(array[j].compareTo(pivot) < 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, leftIndex, i);
		return i;
	}
	
	/**
	 * verifica as possibilidades do inicio, meio e fim do vetor
	 * verifica se o inicio eh menor que o meio e o meio maior que o fim
	 * e assim por diante, ate verificar todos os casos e fazer suas respectivas trocas
	 * 
	 */
	public int med(T[] array, int leftIndex, int rightIndex) {
		
		int center = (leftIndex + rightIndex) / 2;
		
		if(array[center].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, rightIndex, center);
		}
		if(array[center].compareTo(array[leftIndex]) < 0) {
			Util.swap(array, leftIndex, center);
		}
		if(array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, leftIndex, rightIndex);
		}
		
		return center;
		
	}
	
	private void bubble(T[] array, int ini, int fim) {
		boolean achou;
		do {
			achou = false;
			for(int i = ini; i < fim; i++) {
				if(array[i].compareTo(array[i+1]) > 0) {
					achou = true;
					Util.swap(array, i, i+1);
				}
			}

		}while(achou);
	}
}
