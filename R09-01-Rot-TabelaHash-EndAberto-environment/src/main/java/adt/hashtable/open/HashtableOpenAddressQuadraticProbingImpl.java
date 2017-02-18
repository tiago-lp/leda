package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size < 0 ? 0 : size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element == null || search(element) != null) {
			return;
		}
		if (isFull()) {
			throw new HashtableOverflowException();
		}

		for (int probe = 0; probe < table.length; probe++) {
			int hash = getHashFunction().hash(element, probe);
			if (table[hash] == null || table[hash].equals(deletedElement)) {
				this.table[hash] = element;
				break;
			}
			COLLISIONS++;
		}
		elements++;
	}

	@Override
	public void remove(T element) {
		if (element == null || isEmpty()) {
			return;
		}

		for (int probe = 0; probe < table.length; probe++) {
			int hash = getHashFunction().hash(element, probe);
			if (table[hash] == null) {
				break;
			}
			if (table[hash].equals(element)) {
				table[hash] = deletedElement;
				elements--;
				break;
			}
		}
	}

	@Override
	public T search(T element) {
		if (indexOf(element) == -1) {
			return null;
		} else {
			return element;
		}
	}

	@Override
	public int indexOf(T element) {
		if (element == null) {
			return -1;
		}

		for (int probe = 0; probe < table.length; probe++) {
			int hash = getHashFunction().hash(element, probe);
			if (table[hash] == null) {
				return -1;
			}
			if (table[hash].equals(element)) {
				return hash;
			}
		}
		return -1;
	}

	@Override
	public HashFunctionQuadraticProbing<T> getHashFunction() {
		return (HashFunctionQuadraticProbing<T>) this.hashFunction;
	}
}
