package adt.heap;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos
 * armazenados, mas sim usando um comparator. Dessa forma, dependendo do
 * comparator, a heap pode funcionar como uma max-heap ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] resp = (T[]) new Comparable[index + 1];
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int left = this.left(position);
		int right = this.right(position);
		int big = position;

		if (left <= this.index) {
			big = left;
			if (right <= this.index) {
				big = IndexOfBigElement(this.heap, left, right);
			}
		}

		big = IndexOfBigElement(this.heap, position, big);

		if (position != big) {
			Util.swap(this.heap, position, big);
			this.heapify(big);
		}
	}

	private int IndexOfBigElement(T[] array, int i, int j) {
		if (comparator.compare(array[i], array[j]) > 0) {
			return i;
		} else {
			return j;
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}

		if (element != null) {
			this.index++;
			this.heap[this.index] = element;

			int position = this.index;
			while (IndexOfBigElement(this.heap, position, this.parent(position)) == position
					&& this.parent(position) != position) {
				Util.swap(this.heap, position, this.parent(position));
				position = this.parent(position);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array;
		this.index = array.length - 1;
		for (int i = this.parent(array.length - 1); i >= 0; i--) {
			this.heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		T element = this.rootElement();
		if (this.index >= 0) {
			Util.swap(this.heap, 0, this.index);
			index--;
			this.heapify(0);
		}
		return element;
	}

	@Override
	public T rootElement() {
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		Comparator<T> comparator = this.comparator;

		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = (a, b) -> b.compareTo(a);
		this.index = -1;

		for (T element : array) {
			this.insert(element);
		}

		T[] newArray = (T[]) (new Comparable[this.size()]);
		for (int index = 0; index < newArray.length; index++) {
			newArray[index] = this.extractRootElement();
		}

		this.comparator = comparator;
		return newArray;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
