package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;
import adt.hashtable.hashfunction.*;
import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		if (desiredSize < 0) {
			desiredSize = 0;
		}
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		while (!Util.isPrime(++number));
		return number;
	}

	@Override
	public void insert(T element) {
		if (element != null) {

			int hash = ((HashFunctionClosedAddress) this.hashFunction).hash(element);
			int index = this.celIndexOf(element, hash);

			if (index == -1) {
				this.insert(element, hash);
			} else {
				((LinkedList<T>) this.table[hash]).set(index, element);
			}
		}
	}

	private void insert(T element, int hash) {
		if (this.table[hash] == null) {
			this.table[hash] = new LinkedList<>();
		}
		if (((LinkedList<T>) this.table[hash]).size() > 0) {
			this.COLLISIONS++;
		}
		((LinkedList<T>) this.table[hash]).add(element);
		this.elements++;
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int hash = ((HashFunctionClosedAddress) this.hashFunction).hash(element);
			int index = this.celIndexOf(element, hash);

			if (index != -1) {
				((LinkedList<T>) this.table[hash]).remove(index);
				this.elements--;
				if (((LinkedList<T>) this.table[hash]).size() > 0) {
					this.COLLISIONS--;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (this.containsInTable(element)) {
			return element;
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(T element) {
		if (element == null) {
			return -1;
		}
		int hash = ((HashFunctionClosedAddress) this.hashFunction).hash(element);

		if (this.celIndexOf(element, hash) == -1) {
			return -1;
		} else {
			return hash;
		}
	}

	public int celIndexOf(T element, int hash) {
		if (this.table[hash] == null) {
			return -1;
		}
		int from = 0;
		int to = ((LinkedList<T>) this.table[hash]).size();
		for (int index = from; index < to; index++) {
			T tableElement = ((LinkedList<T>) this.table[hash]).get(index);
			if (tableElement.hashCode() == element.hashCode()) {
				return index;
			}
		}
		return -1;
	}

	public boolean containsInTable(T element) {
		return this.indexOf(element) != -1;
	}
}
