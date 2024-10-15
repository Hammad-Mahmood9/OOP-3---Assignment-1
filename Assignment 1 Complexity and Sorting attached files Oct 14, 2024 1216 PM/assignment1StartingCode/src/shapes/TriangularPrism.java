package shapes;

public class TriangularPrism extends Shape {
    private double side;

    public TriangularPrism(double height, double side) {
        super(height);
        this.side = side;
    }

    @Override
    public double calcVolume() {
        return (Math.sqrt(3) / 4) * Math.pow(side, 2) * height;
    }

    @Override
    public double calcBaseArea() {
        return (Math.sqrt(3) / 4) * Math.pow(side, 2);
    }
}
