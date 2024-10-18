package manager;

import shapes.Shape;
import utilities.SortingUtil;

import java.util.Arrays;
import java.util.Comparator;

public class sortManager {
	private Shape[] shapes;

	public sortManager(Shape[] shapes) {
		this.shapes = shapes;
	}

	public void sortUsingBubbleSort(Comparator<Shape> comparator) {
		SortingUtil.bubbleSort(shapes, comparator);
	}

	public void sortUsingSelectionSort(Comparator<Shape> comparator) {
		SortingUtil.selectionSort(shapes, comparator);
	}

	public void sortUsingInsertionSort(Comparator<Shape> comparator) {
		SortingUtil.insertionSort(shapes, comparator);
	}

	public void sortUsingMergeSort(Comparator<Shape> comparator) {
		SortingUtil.mergeSort(Arrays.asList(shapes), comparator);
	}

	public void sortUsingQuickSort(Comparator<Shape> comparator) {
		SortingUtil.quickSort(shapes, comparator);
	}
	
	// Custom sorting below
	
	public void sortUsingHeapSort(Comparator<Shape> comparator) {
		SortingUtil.heapSort(shapes, comparator);
	}
}
