package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		SortComparatorBSTImpl<T> tree = new SortComparatorBSTImpl<T>(this.comparator);
		for(int i = 0; i < array.length; i++) {
			tree.insert(array[i]);
		}
		return tree.order();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		reverseOrder(array, this.root, 0);
		return array;
	}

	private int reverseOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			index = reverseOrder(array, (BSTNode<T>) node.getRight(), index);
			array[index] = node.getData();
			index++;
			index = reverseOrder(array, (BSTNode<T>) node.getLeft(), index);
		}
		return index;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public BSTNode<T> search(T element) {
		if (element == null) {
			return null;
		}
		return search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty() || node.getData().equals(element)) {
			return node;
		} else if (comparator.compare(node.getData(), element) > 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(null, this.root, element);
		}
	}

	private void insert(BSTNode<T> parent, BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setParent(parent);
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
		} else if (comparator.compare(node.getData(), element) > 0) {
			this.insert(node, (BSTNode<T>) node.getLeft(), element);
		} else if (comparator.compare(node.getData(), element) < 0) {
			this.insert(node, (BSTNode<T>) node.getRight(), element);
		}
	}
	
}
