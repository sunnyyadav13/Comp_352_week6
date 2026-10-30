public abstract class Shape {

    private final String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double area();

    public abstract double perimeter();

    public boolean isLargerThan(Shape other) {
        return area() > other.area();
    }

    @Override
    public String toString() {
        return name + " with area " + String.format("%.2f", area());
    }
}
