package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if(size < 0) {
			size = 0;
		}
		this.array = (T[]) new Object[size];
		this.tail = -1;
	}

	@Override
	public T head() {
		if (this.isEmpty()) {
            return null;
		}
        return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail + 1 == array.length;
	}

	private void shiftLeft() {
		int control = 1;
		while((control < array.length) && (array[control] != null)) {
			array[control - 1] = array[control];
			control++;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		}
		if(element != null) {
			tail++;
			array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		T element = array[0];
		this.shiftLeft();
		tail--;
		return element;
	}
}
