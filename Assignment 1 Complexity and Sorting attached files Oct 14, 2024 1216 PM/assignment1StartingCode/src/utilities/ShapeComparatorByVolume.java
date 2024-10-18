package utilities;

import java.util.Comparator;
import shapes.Shape;

public class ShapeComparatorByVolume implements Comparator<Shape> {
	@Override
	public int compare(Shape shape1, Shape shape2) {
		return Double.compare(shape1.calcVolume(), shape2.calcVolume());
	}
}
