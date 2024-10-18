package appDomain;

import shapes.*;
import java.lang.reflect.Constructor;

public class AppDriver {

    public static void main(String[] args)
    {
        String fileName = null;
        Shape[] shapes = null;
        String comparisonType = null;
        String sortingAlgorithm = null;
        
        
    	//
        // Curtis Borson
		// refer to demo001 BasicFileIO.java for a simple example on how to read data from a text file
    	//
        shapes = readShapesFromFile(fileName);
        if (shapes == null) {
            System.err.println("Error reading shapes from file.");
            return;
        }

        // Benchmarking: Capture start time
        long startTime = System.nanoTime();

        // natural order (comparable) or other orders (comparators)
        sortShapes(shapes, comparisonType, sortingAlgorithm);

        // Benchmarking: Capture end time and calculate duration
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        System.out.println("Sorting completed in: " + duration + " ms");

        // Output some results (first, last, every 1000th element)
        System.out.println("First shape: " + shapes[0]);
        System.out.println("Last shape: " + shapes[shapes.length - 1]);
        for (int i = 1000; i < shapes.length; i += 1000) {
            System.out.println("Shape at position " + i + ": " + shapes[i]);
        }
    	
        
        //
        // Curtis Borson
		// refer to demo01 Test.java for an example on how to parse command line arguments and benchmarking tests
        //
        for (int i = 0; i < args.length; i++) {
            switch (args[i].toLowerCase()) {
                case "-f":
                    if (i + 1 < args.length) {
                        fileName = args[++i]; // file name
                    } else {
                        System.err.println("Missing file name after -f");
                        return;
                    }
                    break;
                case "-t":
                    if (i + 1 < args.length) {
                        comparisonType = args[++i]; // type for comparison
                    } else {
                        System.err.println("Missing comparison type after -t");
                        return;
                    }
                    break;
                case "-s":
                    if (i + 1 < args.length) {
                        sortingAlgorithm = args[++i]; // sorting algorithm
                    } else {
                        System.err.println("Missing sorting algorithm after -s");
                        return;
                    }
                    break;
                default:
                    System.err.println("Unknown argument: " + args[i]);
                    return;
            }
        }

        // Check if required arguments are provided
        if (fileName == null || comparisonType == null || sortingAlgorithm == null) {
            System.err.println("Usage: java -jar Sort.jar -f <file_name> -t <type> -s <sort>");
            return;
        }
        
        
        //
        // Manjot Sigh
		// refer to demo02 Student.java for comparable implementation, and NameCompare.java or GradeCompare for comparator implementations
        //
	class ShapesComparator implements Comparator<Shape>{
		private String compareType;

        	public ShapesComparator(String compareType) {
                	this.compareType = compareType;
		}

		@Override
		public int compare(Shape s1, Shape s2){
			switch(compareType){
				case "a":
					if (o1.calcBaseArea() == o2.calcBaseArea()) return 0;
					else if (o1.calcBaseArea() > o2.calcBaseArea()) return 1;
					else return -1;
			
				case "v":
					if (o1.calcVolume() == o2.calcVolume()) return 0;
					else if (o1.calcVolume() > o2.calcVolume()) return 1;
					else return -1;
			
			        default:
			            throw new IllegalArgumentException("Invalid comparison type: " + compareType);
			}
		}
	}

        
        //
        // Hammad Mahmood
		// refer to demo02 KittySort.java on how to use a custom sorting algorithm on a list of comparables to sort using either the natural order (comparable) or other orders (comparators)
        	private static void sortShapes(Shape[] shapes, String comparisonType, String sortingAlgorithm) {
		    Comparator<Shape> comparator = new ShapeComparator(comparisonType);

		    switch (sortingAlgorithm.toLowerCase()) { 
		    	case "b":
		    		bubbleSort(shapes, comparator);
		    	case "i":
		            insertionSort(shapes, comparator);
		            break;
		        case "m":
		            mergeSort(shapes, comparator);
		            break;
		        case "s":
		            selectionSort(shapes, comparator);
		            break;
		        case "q":
		            quickSort(shapes, 0, shapes.length - 1, comparator);
		            break;
		        case "z":
		            heapSort(shapes, comparator);
		            break;
		    }
		}

	    // Bubble sort 
	    private static void bubbleSort(Shape[] shapes, Comparator<Shape> comparator) {
	        for (int i = 0; i < shapes.length - 1; i++) {
	            for (int j = 0; j < shapes.length - i - 1; j++) {
	                if (comparator.compare(shapes[j], shapes[j + 1]) > 0) {
	                    // Swap shapes[j] and shapes[j + 1]
	                    Shape temp = shapes[j];
	                    shapes[j] = shapes[j + 1];
	                    shapes[j + 1] = temp;
	                }
	            }
	        }
	    }

	    // Insertion Sort
	    private static void insertionSort(Shape[] shapes, Comparator<Shape> comparator) {
	        for (int i = 1; i < shapes.length; i++) {
	            Shape key = shapes[i];
	            int j = i - 1;

	            while (j >= 0 && comparator.compare(shapes[j], key) > 0) {
	                shapes[j + 1] = shapes[j];
	                j--;
	            }
	            shapes[j + 1] = key;
	        }
	    }

	    // Selection Sort
	    private static void selectionSort(Shape[] shapes, Comparator<Shape> comparator) {
	        for (int i = 0; i < shapes.length - 1; i++) {
	            int minIndex = i;
	            for (int j = i + 1; j < shapes.length; j++) {
	                if (comparator.compare(shapes[j], shapes[minIndex]) < 0) {
	                    minIndex = j;
	                }
	            }
	            Shape temp = shapes[i];
	            shapes[i] = shapes[minIndex];
	            shapes[minIndex] = temp;
	        }
	    }

	    // Quick Sort
	    private static void quickSort(Shape[] shapes, int low, int high, Comparator<Shape> comparator) {
	        if (low < high) {
	            int pivotIndex = partition(shapes, low, high, comparator);
	            quickSort(shapes, low, pivotIndex - 1, comparator);
	            quickSort(shapes, pivotIndex + 1, high, comparator);
	        }
	    }

	    private static int partition(Shape[] shapes, int low, int high, Comparator<Shape> comparator) {
	        Shape pivot = shapes[high];
	        int i = low - 1;

	        for (int j = low; j < high; j++) {
	            if (comparator.compare(shapes[j], pivot) <= 0) {
	                i++;
	                Shape temp = shapes[i];
	                shapes[i] = shapes[j];
	                shapes[j] = temp;
	            }
	        }
	        Shape temp = shapes[i + 1];
	        shapes[i + 1] = shapes[high];
	        shapes[high] = temp;
	        return i + 1;
	    }

	    // Merge Sort
	    private static void mergeSort(Shape[] shapes, Comparator<Shape> comparator) {
	        if (shapes.length < 2) return;

	        int mid = shapes.length / 2;
	        Shape[] left = Arrays.copyOfRange(shapes, 0, mid);
	        Shape[] right = Arrays.copyOfRange(shapes, mid, shapes.length);

	        mergeSort(left, comparator);
	        mergeSort(right, comparator);
	        merge(shapes, left, right, comparator);
	    }

	    private static void merge(Shape[] shapes, Shape[] left, Shape[] right, Comparator<Shape> comparator) {
	        int i = 0, j = 0, k = 0;
	        while (i < left.length && j < right.length) {
	            if (comparator.compare(left[i], right[j]) <= 0) {
	                shapes[k++] = left[i++];
	            } else {
	                shapes[k++] = right[j++];
	            }
	        }
	        while (i < left.length) shapes[k++] = left[i++];
	        while (j < right.length) shapes[k++] = right[j++];
	    }

	    // Heap Sort
	    private static void heapSort(Shape[] shapes, Comparator<Shape> comparator) {
	        int n = shapes.length;

	        // Build max heap
	        for (int i = n / 2 - 1; i >= 0; i--) {
	            heapify(shapes, n, i, comparator);
	        }

	        // Extract elements from heap one by one
	        for (int i = n - 1; i > 0; i--) {
	            Shape temp = shapes[0];
	            shapes[0] = shapes[i];
	            shapes[i] = temp;

	            heapify(shapes, i, 0, comparator);
	        }
	    }

	    private static void heapify(Shape[] shapes, int n, int i, Comparator<Shape> comparator) {
	        int largest = i;
	        int left = 2 * i + 1;
	        int right = 2 * i + 2;

	        if (left < n && comparator.compare(shapes[left], shapes[largest]) > 0) {
	            largest = left;
	        }

	        if (right < n && comparator.compare(shapes[right], shapes[largest]) > 0) {
	            largest = right;
	        }

	        if (largest != i) {
	            Shape swap = shapes[i];
	            shapes[i] = shapes[largest];
	            shapes[largest] = swap;

	            heapify(shapes, n, largest, comparator);
	        }
	    }

        
        //
        // Curtis Borson
		// refer to demo03 OfficeManager.java on how to create specific objects using reflection from a String
        //
    public static Shape createShapeUsingReflection(String className, Object... params) throws Exception {
        // Load the class by name
        Class<?> clazz = Class.forName(className);

        // Find the constructor with matching parameter types
        Constructor<?> constructor = clazz.getConstructors()[0];
        for (Constructor<?> ctor : clazz.getConstructors()) {
            if (ctor.getParameterCount() == params.length) {
                constructor = ctor;
                break;
            }
        }

        // Create a new instance of the class using the constructor
        return (Shape) constructor.newInstance(params);
    }
}
