import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("factorial(5) = " + Recursion.factorial(5));
        System.out.println("factorial(21) = " + Recursion.factorial(21));
        System.out.println("fibonacci(10) = " + Recursion.fibonacci(10));
        System.out.println("sumOfDigits(-472) = " + Recursion.sumOfDigits(-472));
        System.out.println("isPalindrome(\"racecar\") = " + Recursion.isPalindrome("racecar"));
        System.out.println("isPalindrome(\"Racecar\") = " + Recursion.isPalindrome("Racecar"));

        int[] sorted = {2, 4, 4, 4, 7, 9, 12};
        System.out.println("array = " + Arrays.toString(sorted));
        System.out.println("indexOf(4) = " + BinarySearch.indexOf(sorted, 4));
        System.out.println("firstIndexOf(4) = " + BinarySearch.firstIndexOf(sorted, 4));
        System.out.println("indexOf(5) = " + BinarySearch.indexOf(sorted, 5));

        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("empty tree height = " + tree.height());
        for (int v : new int[] {8, 3, 10, 1, 6, 14, 3}) {
            System.out.println("insert " + v + ": " + tree.insert(v));
        }
        System.out.println("size = " + tree.size() + ", height = " + tree.height() + ", min = " + tree.min());
        System.out.println("in order = " + tree.inOrder());

        BinarySearchTree chain = new BinarySearchTree();
        for (int v = 1; v <= 5; v++) {
            chain.insert(v);
        }
        System.out.println("sorted inserts 1..5 -> height " + chain.height());
    }
}
