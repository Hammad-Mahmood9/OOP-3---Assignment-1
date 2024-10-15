package shapes;

import java.util.Comparator;

public class ShapesComparator implements Comparator<Shape>{

	private String compareType;
	
	public ShapesComparator(String compareType) {
		this.compareType = compareType;
	}
	
	@Override
	public int compare(Shapes o1, Shapes o2) {
	
		switch (compareType) {
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
