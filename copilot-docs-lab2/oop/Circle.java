public class Circle extends Shape {

    private final double radius;

    public Circle(double radius) {
        super("Circle");
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Circle)) {
            return false;
        }
        Circle that = (Circle) other;
        return Double.compare(radius, that.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}
