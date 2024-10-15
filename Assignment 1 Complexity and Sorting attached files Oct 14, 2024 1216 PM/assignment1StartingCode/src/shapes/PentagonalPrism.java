package shapes;

public class PentagonalPrism extends Shape {
    private double side;

    public PentagonalPrism(double height, double side) {
        super(height);
        this.side = side;
    }

    @Override
    public double calcVolume() {
        return (5 * Math.pow(side, 2) * Math.tan(Math.toRadians(54)) / 4) * height;
    }

    @Override
    public double calcBaseArea() {
        return 5 * Math.pow(side, 2) * Math.tan(Math.toRadians(54)) / 4;
    }
}
