package core.math;

/**
 * Collection of utility functions for solving problems that involve modular
 * linear equations.
 * @author Hieu Le
 * @version 11/3/16
 */
public class Modular {

    // Computes a % b (positive value).
    public static int mod(int a, int b) {
        return ((a % b) + b) % b;
    }

    // Computes the greatest common divisor of a and b.
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // Computes the lowest common multiple of a and b.
    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
