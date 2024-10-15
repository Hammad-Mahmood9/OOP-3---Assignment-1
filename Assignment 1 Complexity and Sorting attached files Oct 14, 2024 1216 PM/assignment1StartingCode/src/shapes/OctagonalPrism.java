package shapes;

public class OctagonalPrism extends Shape {
    private double side;

    public OctagonalPrism(double height, double side) {
        super(height);
        this.side = side;
    }

    @Override
    public double calcVolume() {
        return (2 * (1 + Math.sqrt(2)) * Math.pow(side, 2)) * height;
    }

    @Override
    public double calcBaseArea() {
        return 2 * (1 + Math.sqrt(2)) * Math.pow(side, 2);
    }
}
