package util;

/**
 * Class containing useful methods for arrays manipulation.
 */
public class Util {

	/**
	 * Swaps the contents of two positions in an array.
	 *
	 * @param array
	 *            The array to be modified, not null
	 * @param i
	 *            One of the target positions
	 * @param j
	 *            The other target position
	 */
	public static void swap(Object[] array, int i, int j) {
		if (array == null)
			throw new IllegalArgumentException();

		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Creates a generic array of the specified size.
	 * <p>
	 * Ex.: {@code Util.<Integer>makeArray(10);}
	 *
	 * @param size
	 *            The desired size
	 * @return An array of the type and size chosen
	 */
	public static <T> T[] makeArray(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		return array;
	}

	/**
	 * It says if a specific number is prime or not.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(long n) {
		boolean result = true;
		for (int i = 2; i <= n / 2 && result; i++) {
			result = result && (n % i != 0);
		}
		return result;
	}

	public static int trataNulos(Object[] array, int leftIndex, int rightIndex) {
		boolean achou;
		do
		{
			int j = leftIndex;
			achou = false;
			while(j <= rightIndex){
				if(array[j] == null) {
					achou = true;
					if(j < rightIndex) {
						swap(array, j, j+1);
					}
				}
				j++;
			}
			if(achou){
				rightIndex--;
			}
		}while(achou);
		return rightIndex;
	}
}