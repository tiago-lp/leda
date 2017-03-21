package adt.bt;

import adt.bst.BSTNode;
import adt.rbtree.RBNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(RBNode<T> node) {

		BTNode<T> nodeMid = node.getRight();
		node.setRight(nodeMid.getLeft());
		nodeMid.getLeft().setParent(node);
		nodeMid.setLeft(node);
		nodeMid.setParent(node.getParent());
		node.setParent(nodeMid);

		return (BSTNode<T>) nodeMid;
	}

	/**
	 * A rotacao a direita em node deve subir seu filho a esquerda s retorna-lo
	 * em seguida
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(RBNode<T> node) {
		BTNode<T> nodeMid = node.getLeft(); 
		node.setLeft(nodeMid.getRight()); 
		nodeMid.getRight().setParent(node); 
		nodeMid.setRight(node);
		nodeMid.setParent(node.getParent()); 
		node.setParent(nodeMid); 
		return (BSTNode<T>) nodeMid;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
