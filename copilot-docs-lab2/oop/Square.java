public class Square extends Rectangle {

    public Square(double side) {
        super("Square", side, side);
    }

    public double getSide() {
        return getWidth();
    }
}
