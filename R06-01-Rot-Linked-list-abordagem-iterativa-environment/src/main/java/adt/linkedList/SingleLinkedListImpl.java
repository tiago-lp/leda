package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int size() {
		
		int count = 0;
        SingleLinkedListNode<T> node = this.getHead();
        
        while(!(node.isNIL()) && (node.getNext() != null)){
            node = node.getNext();
            count++;
        }

        if(!node.isNIL()){
            count++;
        }
        return count;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> result = this.getHead();
        while(!result.isNIL() && result.getNext() != null && !result.getData().equals(element)) {
            result = result.getNext();
        }
        if(!result.isNIL() && result.getData().equals(element)) {
        	return result.getData();
        } else {
        	return null;
        }
	}

	@Override
	public void insert(T element) {
		if(element != null) {
            SingleLinkedListNode<T> aux = this.getHead();
            while(!(aux.isNIL()) && (aux.getNext() != null)) {
                aux = aux.getNext();
            }
            if(aux.isNIL()) {
                aux.setData(element);
            } else {
                aux.setNext(new SingleLinkedListNode<T>(element, null));
            }
        }
	}

	@Override
	public void remove(T element) {
		
		if(element != null && !this.isEmpty()) {
			
            if(this.getHead().getData().equals(element)) {
            	
                if(this.getHead().getNext() == null) {
                    this.setHead(new SingleLinkedListNode<T>());
                } else {
                    this.setHead(this.getHead().getNext());
                }
            } else {
            	
                SingleLinkedListNode<T> aux = this.getHead();
                SingleLinkedListNode<T> prev = new SingleLinkedListNode<>(null, aux);
                
                while (!(aux.isNIL()) && (aux.getNext() != null) && !(aux.getData().equals(element))) {
                    prev = aux;
                    aux = aux.getNext();
                }
                if(!(aux.isNIL()) && (aux.getData().equals(element))) {
                    prev.setNext(aux.getNext());
                }
            }
        }
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
        SingleLinkedListNode<T> node = this.getHead();

        int index = 0;
        while(!node.isNIL() && node.getNext() != null) {
            array[index] = node.getData();
            index++;
            node = node.getNext();
        }

        if(!node.isNIL()) {
            array[index] = node.getData();
            index++;
        }
        return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
