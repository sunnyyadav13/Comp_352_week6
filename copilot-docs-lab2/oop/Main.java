public class Main {

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(1), new Rectangle(2, 3), new Square(2)};
        for (Shape s : shapes) {
            System.out.println(s);
        }

        System.out.println("Square(2) equals Rectangle(2, 2)? " + new Square(2).equals(new Rectangle(2, 2)));
        System.out.println("Circle(1) equals Circle(1)? " + new Circle(1).equals(new Circle(1)));
        System.out.println("Square(2) larger than Rectangle(1, 4)? " + new Square(2).isLargerThan(new Rectangle(1, 4)));

        BankAccount checking = new BankAccount("Ada", 50);
        SavingsAccount savings = new SavingsAccount("Ada", 500, 2.5);
        System.out.println(checking);
        System.out.println(savings);
        System.out.println("withdraw 80 from checking: " + checking.withdraw(80));
        System.out.println("withdraw 450 from savings: " + savings.withdraw(450));
        System.out.println("withdraw 400 from savings: " + savings.withdraw(400));
        savings.addInterest();
        System.out.println(savings);
        System.out.println("checking equals savings? " + checking.equals(savings));
    }
}
