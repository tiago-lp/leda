package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		if (isEmpty()) {
			return -1;
		}
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
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
		} else if (node.getData().compareTo(element) > 0) {
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
		} else if (node.getData().compareTo(element) > 0) {
			this.insert(node, (BSTNode<T>) node.getLeft(), element);
		} else if (node.getData().compareTo(element) < 0) {
			this.insert(node, (BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return null;
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node == null) {
			return null;
		} else {
			return sucessor(node);
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> sucessor = minimum((BSTNode<T>) node.getRight());
		if (sucessor != null && !sucessor.isEmpty()) {
			return sucessor;
		} else {
			sucessor = (BSTNode<T>) node.getParent();
			while (sucessor != null && !sucessor.isEmpty() && (sucessor.getData().compareTo(node.getData()) < 0)) {
				sucessor = (BSTNode<T>) sucessor.getParent();
			}
			if (sucessor != null && sucessor.isEmpty()) {
				return null;
			} else {
				return sucessor;
			}
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (node == null) {
			return null;
		} else {
			return predecessor(node);
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> predecessor = maximum((BSTNode<T>) node.getLeft());
		if (predecessor != null && !predecessor.isEmpty()) {
			return predecessor;
		} else {
			predecessor = (BSTNode<T>) node.getParent();
			while (predecessor != null && !predecessor.isEmpty()
					&& (predecessor.getData().compareTo(node.getData()) > 0)) {
				predecessor = (BSTNode<T>) predecessor.getParent();
			}
			if (predecessor != null && predecessor.isEmpty()) {
				return null;
			} else {
				return predecessor;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (node != null && !node.isEmpty()) {
				remove(node);
			}
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
		} else if ((node.getLeft().isEmpty() && !node.getRight().isEmpty())
				|| (!node.getLeft().isEmpty() && node.getRight().isEmpty())) {
			removeNode1Grau(node);
		} else {
			removeNode2Graus(node);
		}

	}

	private void removeNode1Grau(BSTNode<T> node) {
		BSTNode<T> filho = (node.getLeft().isEmpty() && !node.getRight().isEmpty()) ? (BSTNode<T>) node.getRight()
				: (BSTNode<T>) node.getLeft();

		if (node.getParent() == null) {
			filho.setParent(null);
			this.root = filho;
		} else {
			if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				node.getParent().setLeft(filho);
			} else {
				node.getParent().setRight(filho);
			}
			filho.setParent(node);
		}
	}

	private void removeNode2Graus(BSTNode<T> node) {
		BSTNode<T> aux = minimum((BSTNode<T>) node.getRight());
		if (aux.isEmpty()) {
			aux = maximum((BSTNode<T>) node.getLeft());
		}
		T element = node.getData();
		node.setData(aux.getData());
		aux.setData(element);
		remove(aux);
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(array, this.root, 0);
		return array;
	}

	private int preOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			array[index] = node.getData();
			index++;
			index = preOrder(array, (BSTNode<T>) node.getLeft(), index);
			index = preOrder(array, (BSTNode<T>) node.getRight(), index);
		}
		return index;

	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(array, this.root, 0);
		return array;
	}

	private int order(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			index = order(array, (BSTNode<T>) node.getLeft(), index);
			array[index] = node.getData();
			index++;
			index = order(array, (BSTNode<T>) node.getRight(), index);
		}
		return index;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(array, this.root, 0);
		return array;
	}

	private int postOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			index = postOrder(array, (BSTNode<T>) node.getLeft(), index);
			index = postOrder(array, (BSTNode<T>) node.getRight(), index);
			array[index] = node.getData();
			index++;
		}
		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
