package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (isEmpty()) {
			return 0;
		} else {
			return this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());
		}
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		if (balance > 1) {
			BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();
			if (this.calculateBalance(leftChild) <= -1) {
				this.leftRotation(leftChild);
			}
			this.rightRotation(node);
		} else if (balance < -1) {
			BSTNode<T> rightChild = (BSTNode<T>) node.getRight();
			if (this.calculateBalance(rightChild) >= 1) {
				this.rightRotation(rightChild);
			}
			this.leftRotation(node);
		}
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> balancedNode = Util.rightRotation(node);
		if (balancedNode.getParent() == null) {
			this.root = balancedNode;
		}
	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> balancedNode = Util.leftRotation(node);
		if (balancedNode.getParent() == null) {
			this.root = balancedNode;
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		if (Math.abs(balance) >= 2) {
			this.rebalance(node);
		}
		if (node.getParent() != null) {
			this.rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public void insert(T element) {
		super.insert(element);
		BSTNode<T> node = this.search(element);
		this.rebalanceUp(node);
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			node = super.remove(node);
			this.rebalanceUp(node);
		}
	}
}
