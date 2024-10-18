package shapes;

public abstract class Shape implements Comparable<Shape> {
	protected double height;

	/**
	 * Creates a Shape with specified height.
	 * 
	 * @param height the height
	 */
	public Shape(double height) {
		super();
		this.height = height;
	}

	/**
	 * Returns the height of the Shape.
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Sets the height of the Shape.
	 * 
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public int compareTo(Shape other) {
		if (this.height > other.height)
			return 1;
		if (this.height < other.height)
			return -1;
		return 0;
	}

	/**
	 * Returns the calculated base area of the Shape.
	 * 
	 * @return the base area
	 */
	public abstract double calcBaseArea();

	/**
	 * Getter for base area.
	 * 
	 * @return the base area
	 */
	public double getBaseArea() {
		return calcBaseArea(); // Call the abstract method to get the base area
	}

	/**
	 * Returns the calculated volume of the Shape.
	 * 
	 * @return the volume
	 */
	public abstract double calcVolume();
	
	// New method to get the shape's name
    public abstract String getName();

	@Override
	public String toString() {
		return "height:" + getHeight() + ", base area:" + getBaseArea() + ", volume:" + calcVolume();
	}
}
