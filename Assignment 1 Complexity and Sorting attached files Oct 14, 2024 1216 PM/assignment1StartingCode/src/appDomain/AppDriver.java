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
					return Double.compare(s1.calcBaseArea(), s2.calcBaseAreaa());
				case "v":
					return Double.compare(s1.calcVolume(), s2.calcVolume());
				default:
					throw new IllegalArgumentException("Invalid comparison type: " + compareType);
			}
		}
	}

        
        //
        // Name Here
		// refer to demo02 KittySort.java on how to use a custom sorting algorithm on a list of comparables to sort using either the natural order (comparable) or other orders (comparators)
        //

        
        //
        // Curtis Borson
		// refer to demo03 OfficeManager.java on how to create specific objects using reflection from a String
        //
        try {
            // Example: Create a Cylinder with height and radius using reflection
            Shape cylinder = createShapeUsingReflection("shapes.Cylinder", 10.0, 5.0);
            System.out.println("Created object: " + cylinder.getClass().getSimpleName() + 
                               ", Volume: " + cylinder.calcVolume() + 
                               ", Base Area: " + cylinder.calcBaseArea());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    // Method for dynamic shape creation using reflection
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
