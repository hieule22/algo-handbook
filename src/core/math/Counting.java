package core.math;

/**
 * Collections of utility functions for solving combinatorial and counting problems.
 * @author Hieu Le
 * @version 2/18/17
 */
public class Counting {

    /**
     * An inline, iterative implementation of exponentiation by squaring.
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

    /**
     * A recursive implementation of exponentiation by squaring.
     */
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

    /**
     * Constructs a Pascal's triangle of given height. Each entry can then be used
     * to compute the value of n choose k.
     */
    public static long[][] constructPascalTriangle(int bound, long mod) {
        long[][] nCr = new long[bound + 1][bound + 1];
        for (int n = 0; n <= bound; ++n) {
            nCr[n][0] = 1;
            for (int k = 1; k <= n; ++k) {
                nCr[n][k] = (nCr[n - 1][k - 1] + nCr[n - 1][k]) % mod;
            }
        }
        return nCr;
    }
}
