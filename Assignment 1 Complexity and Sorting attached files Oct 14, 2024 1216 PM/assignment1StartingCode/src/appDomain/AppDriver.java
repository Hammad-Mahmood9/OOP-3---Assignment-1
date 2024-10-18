package appDomain;

import shapes.*;
import utilities.*;
import manager.sortManager;
import java.util.Comparator;

public class AppDriver {

	public static void main(String[] args) {
		String baseDir = "res/"; // Set your base directory here
		String fileName = null;
		Shape[] shapes = null;
		String comparisonType = null;
		String sortingAlgorithm = null;

		// Parse command line arguments
		for (String arg : args) {
			if (arg.toLowerCase().startsWith("-f")) {
				fileName = baseDir + arg.substring(2); // Extract file name
			} else if (arg.toLowerCase().startsWith("-t")) {
				comparisonType = arg.substring(2); // Extract comparison type
			} else if (arg.toLowerCase().startsWith("-s")) {
				sortingAlgorithm = arg.substring(2); // Extract sorting algorithm
			} else if (arg.startsWith("-")) { // Handle unknown arguments starting with '-'
				System.err.println("Unknown argument: " + arg);
				return;
			}
		}

		// Check if required arguments are provided
		if (fileName == null || comparisonType == null || sortingAlgorithm == null) {
			System.err.println("Usage: java -jar Sort.jar -f<file_name> -t<type> -s<sort>");
			System.err.println("Where <type> can be:");
			System.err.println("  h - sort by height");
			System.err.println("  v - sort by volume");
			System.err.println("  a - sort by base area");
			return;
		}

		// Read shapes from file using ShapeFileReader
		shapes = ShapeFileReader.readShapesFromFile(fileName);
		if (shapes == null) {
			System.err.println("Error reading shapes from file.");
			return;
		}

		// Create SortManager instance
		sortManager sortManager = new sortManager(shapes);

		// Timing the sort
		long startTime = System.nanoTime();

		// Determine the comparator based on the comparison type
		Comparator<Shape> comparator = getComparator(comparisonType);
		if (comparator == null) {
			System.err.println("Unknown comparison type: " + comparisonType);
			return;
		}

		// Perform sorting based on the selected algorithm
		switch (sortingAlgorithm.toLowerCase()) {
		case "b":
			sortManager.sortUsingBubbleSort(comparator);
			break;
		case "s":
			sortManager.sortUsingSelectionSort(comparator);
			break;
		case "i":
			sortManager.sortUsingInsertionSort(comparator);
			break;
		case "m":
			sortManager.sortUsingMergeSort(comparator);
			break;
		case "q":
			sortManager.sortUsingQuickSort(comparator);
			break;
		case "h":
			sortManager.sortUsingHeapSort(comparator);
			break;
		default:
			System.err.println("Unknown sorting algorithm: " + sortingAlgorithm);
			return;
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1_000_000;

		System.out.println("Sorting completed in: " + duration + " ms");

		// Output shapes
		outputShapes(shapes, comparisonType); // Pass the comparison type directly
	}

	// Method to get the comparator based on the comparison type
	private static Comparator<Shape> getComparator(String comparisonType) {
		switch (comparisonType.toLowerCase()) {
		case "h":
			return Comparator.comparingDouble(Shape::getHeight);
		case "v":
			return new ShapeComparatorByVolume();
		case "a":
			return new ShapeComparatorByArea();
		default:
			return null;
		}
	}

	// Method to output shapes
	private static void outputShapes(Shape[] shapes, String comparisonType) {
	    String typeLabel = comparisonType.equalsIgnoreCase("v") ? "volume" : "area";

	    if (shapes.length > 0) {
	        // First shape
	        System.out.printf("First element is: The %s has a %s of: %.3f%n",
	                          shapes[0].getName(), typeLabel, getValueByType(shapes[0], comparisonType));

	        // Output every thousandth shape
	        for (int i = 1000; i < shapes.length; i += 1000) {
	            System.out.printf("%d-th element is: The %s has a %s of: %.3f%n",
	                              i, shapes[i].getName(), typeLabel, getValueByType(shapes[i], comparisonType));
	        }

	        // Last shape
	        System.out.printf("Last element is: The %s has a %s of: %.3f%n",
	                          shapes[shapes.length - 1].getName(), typeLabel, getValueByType(shapes[shapes.length - 1], comparisonType));
	    }
	}

	// Helper method to get the appropriate value based on type
	private static double getValueByType(Shape shape, String comparisonType) {
	    switch (comparisonType.toLowerCase()) {
	        case "v":
	            return shape.calcVolume(); 
	        case "a":
	            return shape.getBaseArea(); 
	        default:
	            return 0;
	    }
	}
}
