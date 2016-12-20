package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			
			DoubleLinkedListNode<T> create = (DoubleLinkedListNode<T>) this.getHead();
            DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<>(element, create , null);
            ((DoubleLinkedListNode<T>) this.getHead()).setPrevious(aux);
            this.setHead(aux);
            
            if(this.getLast().isNIL()){
                this.setLast(aux);
                
            }else if(this.getLast().getPrevious() == null){
                this.getLast().setPrevious(aux);
            }
        }
	}

	@Override
	public void removeFirst() {
		 if(!this.getHead().isNIL()) {
			 
	            if(this.getHead().getNext() != null) {
	            	
	                this.setHead(this.getHead().getNext());
	                ((DoubleLinkedListNode<T>)this.getHead()).setPrevious(null);
	            } else {
	                this.setHead(new DoubleLinkedListNode<T>());
	            }
	        }
	}

	@Override
	public void removeLast() {
		if(this.size() > 1) {
			
            if(this.getLast() != null && this.getLast().getPrevious() != null) {
                
            	this.getLast().getPrevious().setNext(null);
                this.setLast(this.getLast().getPrevious());
            }
        } else if(this.size() == 1) {
            this.getHead().setData(null);
            this.getLast().setData(null);
        }
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
	
	@Override
    public void insert(T element) {
        if(element != null) {
        	
            DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<>(element, null, this.getLast());
            
            if(isEmpty()) {
            	
                this.setHead(aux);
                this.setLast(aux);
                
            } else {
            	
                this.getLast().setNext(aux);
                this.setLast(aux);
            }
        }
    }

    @Override
    public void remove(T element) {
        if(!this.isEmpty()) {
        	
            if(this.getHead().getData().equals(element)) {
                this.removeFirst();
                
            } else if(this.getLast().getData().equals(element)) {
                this.removeLast();
                
            } else {
            	
                DoubleLinkedListNode<T> aux = this.getLast();
                DoubleLinkedListNode<T> remove = null;

                while(!aux.isNIL() && aux.getPrevious() != null){
                    if(aux.getData().equals(element)){
                        remove = aux;
                    }
                    aux = aux.getPrevious();
                }
                if(remove != null){
                    remove.getPrevious().setNext(remove.getNext());
                    ((DoubleLinkedListNode<T>) remove.getNext()).setPrevious(remove.getPrevious());
                }
            }
        }
    }
}
