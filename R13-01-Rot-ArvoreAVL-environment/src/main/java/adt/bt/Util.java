package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();

		node.setRight(right.getLeft());
		right.setLeft(node);
		
		if (node.getParent() != null) {
			if (!node.getParent().isEmpty() && !node.getParent().getLeft().isEmpty()
					&& node.getParent().getLeft().getData().equals(node.getData())) {

				node.getParent().setLeft(right);
			} else {
				node.getParent().setRight(right);
			}
		}

		right.setParent(node.getParent());
		node.setParent(right);

		return right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();

		node.setLeft(left.getRight());
		left.setRight(node);
		if (node.getParent() != null) {
			if (!(!node.getParent().isEmpty() && !node.getParent().getLeft().isEmpty()
					&& node.getParent().getLeft().getData().equals(node.getData()))) {

				node.getParent().setLeft(left);
			} else {
				node.getParent().setRight(left);
			}
		}
		left.setParent(node.getParent());
		node.setParent(left);

		return left;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
