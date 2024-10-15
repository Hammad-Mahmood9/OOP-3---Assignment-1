package shapes;

public abstract class Shape implements Comparable<Shape> {
    protected double height;

    public Shape(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    // Abstract methods for volume and base area
    public abstract double calcVolume();
    public abstract double calcBaseArea();

    // Comparable implementation to compare by height
    @Override
    public int compareTo(Shape s) {
        return Double.compare(this.height, s.height);
    }
}
