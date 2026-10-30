/**
 * Classic recursive functions.
 *
 * <p>Each method has a base case that stops the recursion. Each method also has a recursive case.
 * The recursive case calls the method again with a smaller input.
 */
public class Recursion {

    private Recursion() {
    }

    /**
     * Computes {@code n!} = n * (n - 1) * ... * 1.
     *
     * <p>Base case: {@code 0! = 1}. Runs in O(n) time and uses O(n) stack space.
     * The result only fits in a {@code long} up to {@code n = 20}; larger values overflow
     * and return a wrong (even negative) number.
     *
     * @param n the number; must not be negative
     * @return n factorial (correct for 0 to 20)
     * @throws IllegalArgumentException if {@code n} is negative
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        if (n > 20) {
            throw new IllegalArgumentException("n is too large");
        }
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * Returns the n-th Fibonacci number: 0, 1, 1, 2, 3, 5, 8, ...
     *
     * <p>Base cases: {@code fibonacci(0) = 0} and {@code fibonacci(1) = 1}. This method calls itself
     * two times for each step. Thus it takes O(2^n) time and becomes very slow near n = 40.
     * The result only fits in an {@code int} up to {@code n = 46}; larger values overflow.
     *
     * @param n the position in the sequence, starting at 0; must not be negative
     * @return the n-th Fibonacci number (correct for 0 to 46)
     * @throws IllegalArgumentException if {@code n} is negative
     */
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Adds up the decimal digits of a number, for example 472 gives 4 + 7 + 2 = 13.
     *
     * <p>The method uses the absolute value of a negative number: {@code sumOfDigits(-472)} is 13.
     * Base case: a single digit is its own sum. Runs in O(number of digits) time.
     *
     * @param n the number
     * @return the sum of its digits
     */
    public static int sumOfDigits(int n) {
        n = Math.abs(n);
        if (n < 10) {
            return n;
        }
        return n % 10 + sumOfDigits(n / 10);
    }

    /**
     * Tells if a string reads the same forwards and backwards.
     *
     * <p>The test is case-sensitive. It includes every character, also spaces:
     * {@code "racecar"} is a palindrome but {@code "Racecar"} is not.
     * Base case: strings of length 0 or 1 are palindromes.
     *
     * @param s the string to test; must not be {@code null}
     * @return {@code true} if {@code s} is a palindrome
     */
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        if (Character.toLowerCase(s.charAt(0)) != Character.toLowerCase(s.charAt(s.length() - 1))) {
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));
    }
}
