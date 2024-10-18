package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import shapes.*;

public class ShapeFileReader {

	// Method to read shapes from the file
	public static Shape[] readShapesFromFile(String fileName) {
		try (Scanner scanner = new Scanner(new File(fileName))) {
			int count = scanner.nextInt();
			scanner.nextLine();
			Shape[] shapes = new Shape[count];

			for (int i = 0; i < count; i++) {
				String line = scanner.nextLine();
				shapes[i] = parseShape(line);
			}
			return shapes;
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + fileName);
			return null;
		} catch (Exception e) {
			System.err.println("Error reading shapes: " + e.getMessage());
			return null;
		}
	}

	// Method to parse shape string into Shape object
	private static Shape parseShape(String line) {
		String[] parts = line.split(" ");
		String type = parts[0];
		double height = Double.parseDouble(parts[1]);
		double dimension = Double.parseDouble(parts[2]);

		switch (type) {
		case "Cone":
			return new Cone(height, dimension);
		case "Cylinder":
			return new Cylinder(height, dimension);
		case "OctagonalPrism":
			return new OctagonalPrism(height, dimension);
		case "PentagonalPrism":
			return new PentagonalPrism(height, dimension);
		case "Pyramid":
			return new Pyramid(height, dimension);
		case "SquarePrism":
			return new SquarePrism(height, dimension);
		case "TriangularPrism":
			return new TriangularPrism(height, dimension);
		default:
			throw new IllegalArgumentException("Unknown shape type: " + type);
		}
	}
}
