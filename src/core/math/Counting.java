package core.math;

/**
 * Collections of utility functions for solving combinatorial and counting problems.
 * @author Hieu Le
 * @version 2/18/17
 */
public class Counting {

    /**
     * Computes the value of a given base raised to a given exponent
     * modulo a given mod using exponentiation by squaring.
     */
    public static long modPow(long base, long exponent, long mod) {
        if (base == 0) {
            return 0;
        }
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exponent /= 2;
        }
        return result;
    }

    public static long modPowRecursive(long base, long exponent, long mod) {
        if (base == 0) {
            return 0;
        } else if (exponent == 0) {
            return 1;
        }
        long result = modPowRecursive(base, exponent / 2, mod);
        result = result * result % mod;
        if (exponent % 2 == 1) {
            result = result * base % mod;
        }
        return result;
    }
}
