package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		if(this.getData() == null) {
			return 0;
		} else {
			return this.getNext().size() + 1;
		}
	}

	@Override
	public T search(T element) {
		if(element == null) {
			return null;
		}
		if(this.getData() != null) {
			if(this.getData().equals(element)) {
				return this.getData();
			} else {
				return this.getNext().search(element);
			}
		} else {
			return null;
		}

	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(this.getData() == null) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<T>());
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && this.getData() != null) {
			if(this.getData().equals(element)) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.size()];
		
		if(this.getData() != null){
			int cont = 0; 
			array[cont] = this.getData();
			toArray(array, cont, this.getNext());
		}
		return array;
	}
 
	private void toArray(T[] array, int cont, RecursiveSingleLinkedListImpl<T> node) {
		if(node.getData() != null) {
			cont++;
			array[cont] = node.getData();
			toArray(array, cont, node.getNext());
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
}
