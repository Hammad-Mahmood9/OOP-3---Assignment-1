package shapes;

public class Pyramid extends Shape {
    private double side;

    public Pyramid(double height, double side) {
        super(height);
        this.side = side;
    }

    @Override
    public double calcVolume() {
        return (Math.pow(side, 2) * height) / 3;
    }

    @Override
    public double calcBaseArea() {
        return Math.pow(side, 2);
    }

    @Override
    public String toString() {
        return "Pyramid [Height=" + height + ", Side=" + side +
               ", Base Area=" + calcBaseArea() + ", Volume=" + calcVolume() + "]";
    }

    @Override
    public int compareTo(Shape o) {
        return Double.compare(this.height, o.height);
    }
}
