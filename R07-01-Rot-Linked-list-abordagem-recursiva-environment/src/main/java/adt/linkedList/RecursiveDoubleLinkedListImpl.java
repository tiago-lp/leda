package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			if(isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
				aux.setPrevious(this);
				this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
				this.getPrevious().setNext(this);
			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>();
				newNode.setData(this.getData());
				this.setData(element);
				RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
				aux.setPrevious(newNode);
				newNode.setNext(this.getNext());
				newNode.setPrevious(this);
				this.setNext(newNode);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!this.isEmpty()) {
			this.setData(this.getNext().getData());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			this.setNext(this.getNext().getNext());
		}
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()) {
			if(this.getNext().getData() == null) {
				this.setData(null);
			} else {
				RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
				aux.removeLast();
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(this.getData() == null) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
				RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
				aux.setPrevious(this);
				this.getPrevious().setNext(this);
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!this.isEmpty()) {
			if(this.getData().equals(element)) {
				RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
				aux.setPrevious(this.previous);
				this.getPrevious().setNext(aux);
			} else {
				this.getNext().remove(element);
			}
		}
	}
	
	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
