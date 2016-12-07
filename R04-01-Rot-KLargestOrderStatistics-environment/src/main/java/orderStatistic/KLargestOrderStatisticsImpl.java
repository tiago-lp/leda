package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		if((array == null) || (k > array.length + 1) || (k < 1)) {
			return null;
		}

		T[] auxiliar =  (T[]) new Integer[array.length - k];
		T estatistica = orderStatistics(array, k);
		
		int ind_aux = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i].compareTo(estatistica) > 0){
				auxiliar[ind_aux] = array[i];
				ind_aux++;
			}
		}
		return auxiliar;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		
		for(int i = 0; i < k; i++){
			int menor = i;
			for(int j = i+1; j < array.length; j++ ){
				if ((array[menor].compareTo(array[j])) > 0) {
		               menor = j;
		            }
		         }
			if(!(menor == i)) {
	            Util.swap(array, menor, i);
	         }
 
		}	
		return array[k-1];		
	}
}
