package shapes;

/**
 * Represents a Prism.
 */
public abstract class Prism extends Shape {
	private double side;

	/**
	 * Creates a Prism with specified height and side.
	 * 
	 * @param height the height
	 * @param side   the height
	 */
	public Prism(double height, double side) {
		super(height);
		this.side = side;
	}

	/**
	 * Returns the Prism's side.
	 * 
	 * @return the side
	 */
	public double getSide() {
		return side;
	}

	/**
	 * Sets the Prism's side.
	 * 
	 * @param side the side to set
	 */
	public void setSide(double side) {
		this.side = side;
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * getHeight();
	}

	@Override
	public String toString() {
		return "Prism [getSide()=" + getSide() + ", calcVolume()=" + calcVolume() + ", getHeight()=" + getHeight()
				+ ", calcBaseArea()=" + calcBaseArea() + "]";
	}

}