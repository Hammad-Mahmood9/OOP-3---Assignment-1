package appDomain;

import shapes.*;
import java.lang.reflect.Constructor;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Comparator;

public class AppDriver {

    public static void main(String[] args) {
        String fileName = null;
        Shape[] shapes = null;
        String comparisonType = null;
        String sortingAlgorithm = null;

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i].toLowerCase()) {
                case "-f":
                    if (i + 1 < args.length) {
                        fileName = args[++i]; // File name
                    } else {
                        System.err.println("Missing file name after -f");
                        return;
                    }
                    break;
                case "-t":
                    if (i + 1 < args.length) {
                        comparisonType = args[++i]; // Type for comparison
                    } else {
                        System.err.println("Missing comparison type after -t");
                        return;
                    }
                    break;
                case "-s":
                    if (i + 1 < args.length) {
                        sortingAlgorithm = args[++i]; // Sorting algorithm
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

        // Now read the shapes from the file
        shapes = readShapesFromFile(fileName);
        if (shapes == null) {
            System.err.println("Error reading shapes from file.");
            return;
        }

        // Benchmarking: Capture start time after reading the shapes
        long startTime = System.nanoTime();

        // Sort shapes by comparison type and sorting algorithm
        sortShapes(shapes, comparisonType, sortingAlgorithm);

        // Benchmarking: Capture end time and calculate duration
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        System.out.println("Sorting completed in: " + duration + " ms");

        // Output some results (first, last, every 1000th element)
        if (shapes.length > 0) {
            System.out.println("First shape: " + shapes[0]);
            for (int i = 1000; i < shapes.length; i += 1000) {
                System.out.println("Shape at position " + i + ": " + shapes[i]);
            }
            System.out.println("Last shape: " + shapes[shapes.length - 1]);
        }
    }

    // Method to read shapes from the file
    public static Shape[] readShapesFromFile(String fileName) {
        Shape[] shapeArray = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // First line contains the number of shapes
            String line = br.readLine();
            int numShapes = Integer.parseInt(line.trim());
            shapeArray = new Shape[numShapes]; // Initialize array with size

            // Read each shape and create objects
            for (int i = 0; i < numShapes; i++) {
                line = br.readLine();
                String[] tokens = line.split(" ");
                String shapeType = tokens[0];

                double height = Double.parseDouble(tokens[1]);
                double size = Double.parseDouble(tokens[2]);

                // Using reflection to create shape objects based on shape type
                switch (shapeType) {
                    case "Cylinder":
                    case "Cone":
                        shapeArray[i] = createShapeUsingReflection("shapes." + shapeType, height, size);
                        break;
                    case "Pyramid":
                    case "SquarePrism":
                    case "TriangularPrism":
                    case "PentagonalPrism":
                    case "OctagonalPrism":
                        shapeArray[i] = createShapeUsingReflection("shapes." + shapeType, height, size);
                        break;
                    default:
                        System.err.println("Unknown shape type: " + shapeType);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shapeArray; // Return the array of shapes
    }

    // Method to sort shapes
    public static void sortShapes(Shape[] shapes, String comparisonType, String sortingAlgorithm) {
        Comparator<Shape> comparator = new ShapesComparator(comparisonType);

        switch (sortingAlgorithm.toLowerCase()) {
            case "b": // Bubble Sort
                bubbleSort(shapes, comparator);
                break;
            case "i": // Insertion Sort
                insertionSort(shapes, comparator);
                break;
            case "s": // Selection Sort
                selectionSort(shapes, comparator);
                break;
            case "m": // Merge Sort
                mergeSort(shapes, comparator);
                break;
            case "q": // Quick Sort
                quickSort(shapes, comparator);
                break;
            case "h": // Heap Sort
                heapSort(shapes, comparator);
                break;
            default:
                System.err.println("Invalid sorting algorithm: " + sortingAlgorithm);
        }
    }

    // Shape Comparator
    static class ShapesComparator implements Comparator<Shape> {
        private String compareType;

        public ShapesComparator(String compareType) {
            this.compareType = compareType;
        }

        @Override
        public int compare(Shape s1, Shape s2) {
            switch (compareType) {
                case "a": // Compare by base area
                    return Double.compare(s1.calcBaseArea(), s2.calcBaseArea());
                case "v": // Compare by volume
                    return Double.compare(s1.calcVolume(), s2.calcVolume());
                default: // Default: compare by height
                    return Double.compare(s1.getHeight(), s2.getHeight());
            }
        }
    }

    // Sorting algorithms
    public static void bubbleSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(shapes[j], shapes[j + 1]) > 0) {
                    Shape temp = shapes[j];
                    shapes[j] = shapes[j + 1];
                    shapes[j + 1] = temp;
                }
            }
        }
    }

    public static void insertionSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;
        for (int i = 1; i < n; i++) {
            Shape key = shapes[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(shapes[j], key) > 0) {
                shapes[j + 1] = shapes[j];
                j = j - 1;
            }
            shapes[j + 1] = key;
        }
    }

    public static void selectionSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(shapes[j], shapes[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            Shape temp = shapes[minIdx];
            shapes[minIdx] = shapes[i];
            shapes[i] = temp;
        }
    }

    public static void mergeSort(Shape[] shapes, Comparator<Shape> comparator) {
        if (shapes.length < 2) {
            return;
        }
        int mid = shapes.length / 2;
        Shape[] left = new Shape[mid];
        Shape[] right = new Shape[shapes.length - mid];

        System.arraycopy(shapes, 0, left, 0, mid);
        System.arraycopy(shapes, mid, right, 0, shapes.length - mid);

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
        while (i < left.length) {
            shapes[k++] = left[i++];
        }
        while (j < right.length) {
            shapes[k++] = right[j++];
        }
    }

    public static void quickSort(Shape[] shapes, Comparator<Shape> comparator) {
        quickSort(shapes, 0, shapes.length - 1, comparator);
    }

    private static void quickSort(Shape[] shapes, int low, int high, Comparator<Shape> comparator) {
        if (low < high) {
            int pi = partition(shapes, low, high, comparator);
            quickSort(shapes, low, pi - 1, comparator);
            quickSort(shapes, pi + 1, high, comparator);
        }
    }

    private static int partition(Shape[] shapes, int low, int high, Comparator<Shape> comparator) {
        Shape pivot = shapes[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(shapes[j], pivot) <= 0) {
                i++;
                // Swap shapes[i] and shapes[j]
                Shape temp = shapes[i];
                shapes[i] = shapes[j];
                shapes[j] = temp;
            }
        }
        // Swap shapes[i + 1] and shapes[high] (or pivot)
        Shape temp = shapes[i + 1];
        shapes[i + 1] = shapes[high];
        shapes[high] = temp;
        return i + 1;
    }

    public static void heapSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;

        // Build the heap (rearrange the array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(shapes, n, i, comparator);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            Shape temp = shapes[0];
            shapes[0] = shapes[i];
            shapes[i] = temp;

            // Call max heapify on the reduced heap
            heapify(shapes, i, 0, comparator);
        }
    }

    // Helper method to heapify a subtree rooted at node i, with size n
    private static void heapify(Shape[] shapes, int n, int i, Comparator<Shape> comparator) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1;  // left child
        int right = 2 * i + 2;  // right child

        // If left child is larger than root
        if (left < n && comparator.compare(shapes[left], shapes[largest]) > 0) {
            largest = left;
        }

        // If right child is larger than the largest so far
        if (right < n && comparator.compare(shapes[right], shapes[largest]) > 0) {
            largest = right;
        }

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            Shape swap = shapes[i];
            shapes[i] = shapes[largest];
            shapes[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(shapes, n, largest, comparator);
        }
    }

    // Create shape using reflection
    public static Shape createShapeUsingReflection(String className, Object... params) throws Exception {
        try {
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
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
            throw e;
        } catch (Exception e) {
            System.err.println("Error creating shape: " + className);
            throw e;
        }
    }
}

