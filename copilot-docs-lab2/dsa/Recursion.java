public class Recursion {

    private Recursion() {
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int sumOfDigits(int n) {
        n = Math.abs(n);
        if (n < 10) {
            return n;
        }
        return n % 10 + sumOfDigits(n / 10);
    }

    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));
    }
}
