package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortingUtil {
	// Bubble Sort implementation
	public static <T> void bubbleSort(T[] array, Comparator<? super T> comparator) {
		int n = array.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (comparator.compare(array[j], array[j + 1]) > 0) {
					// Swap array[j] and array[j + 1]
					T temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	// Selection Sort implementation
	public static <T> void selectionSort(T[] array, Comparator<? super T> comparator) {
		int n = array.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (comparator.compare(array[j], array[minIndex]) < 0) {
					minIndex = j;
				}
			}
			// Swap the found minimum element with the first element
			T temp = array[minIndex];
			array[minIndex] = array[i];
			array[i] = temp;
		}
	}

	// Insertion Sort implementation
	public static <T> void insertionSort(T[] array, Comparator<? super T> comparator) {
		int n = array.length;
		for (int i = 1; i < n; i++) {
			T key = array[i];
			int j = i - 1;

			// Move elements of array[0..i-1] that are greater than key
			while (j >= 0 && comparator.compare(array[j], key) > 0) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
	}

	// Merge Sort implementation
	public static <T> void mergeSort(T[] array, Comparator<? super T> comparator) {
		mergeSort(Arrays.asList(array), comparator);
	}

	public static <T> void mergeSort(List<T> list, Comparator<? super T> comparator) {
		if (list.size() < 2)
			return;

		int mid = (list.size() + 1) / 2;
		List<T> left = new ArrayList<>(list.subList(0, mid));
		List<T> right = new ArrayList<>(list.subList(mid, list.size()));

		mergeSort(left, comparator);
		mergeSort(right, comparator);
		merge(list, left, right, comparator);
	}

	private static <T> void merge(List<T> list, List<T> left, List<T> right, Comparator<? super T> comparator) {
		int i = 0, j = 0, k = 0;
		// Merge the two lists
		while (i < left.size() && j < right.size()) {
			if (comparator.compare(left.get(i), right.get(j)) <= 0) {
				list.set(k++, left.get(i++));
			} else {
				list.set(k++, right.get(j++));
			}
		}
		while (i < left.size()) {
			list.set(k++, left.get(i++));
		}
		while (j < right.size()) {
			list.set(k++, right.get(j++));
		}
	}

	// Quick Sort implementation
	public static <T> void quickSort(T[] array, Comparator<? super T> comparator) {
		quickSort(array, 0, array.length - 1, comparator);
	}

	private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
		if (low < high) {
			int pi = partition(array, low, high, comparator);
			quickSort(array, low, pi - 1, comparator);
			quickSort(array, pi + 1, high, comparator);
		}
	}

	private static <T> int partition(T[] array, int low, int high, Comparator<? super T> comparator) {
		T pivot = array[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (comparator.compare(array[j], pivot) < 0) {
				i++;
				// Swap array[i] and array[j]
				T temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		// Swap array[i + 1] and array[high] (or pivot)
		T temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;
		return i + 1;
	}
	
	// Heap Sort implementation
	public static <T> void heapSort(T[] array, Comparator<? super T> comparator) {
	    int n = array.length;

	    // Build heap (rearrange array)
	    for (int i = n / 2 - 1; i >= 0; i--) {
	        heapify(array, n, i, comparator);
	    }

	    // One by one extract an element from heap
	    for (int i = n - 1; i > 0; i--) {
	        // Move current root to end
	        T temp = array[0];
	        array[0] = array[i];
	        array[i] = temp;

	        // Call max heapify on the reduced heap
	        heapify(array, i, 0, comparator);
	    }
	}

	private static <T> void heapify(T[] array, int n, int i, Comparator<? super T> comparator) {
	    int largest = i; // Initialize largest as root
	    int left = 2 * i + 1; // left = 2*i + 1
	    int right = 2 * i + 2; // right = 2*i + 2

	    // If left child is larger than root
	    if (left < n && comparator.compare(array[left], array[largest]) > 0) {
	        largest = left;
	    }

	    // If right child is larger than largest so far
	    if (right < n && comparator.compare(array[right], array[largest]) > 0) {
	        largest = right;
	    }

	    // If largest is not root
	    if (largest != i) {
	        T swap = array[i];
	        array[i] = array[largest];
	        array[largest] = swap;

	        // Recursively heapify the affected sub-tree
	        heapify(array, n, largest, comparator);
	    }
	}

	
	
	
	
	
}