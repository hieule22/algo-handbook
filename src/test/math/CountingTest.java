package test.math;

import core.math.Counting;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Hieu Le
 * @version 2/18/17
 */
public class CountingTest {
    @Test
    public void testModPow() throws Exception {
        testModPowAlgorithm(Counting::modPow);
    }

    @Test
    public void testModPowRecursive() throws Exception {
        testModPowAlgorithm(Counting::modPowRecursive);
    }

    private interface ModPowAlgorithm {
        long compute(long base, long exponent, long mod);
    }

    private void testModPowAlgorithm(ModPowAlgorithm algorithm) {
        assertEquals(algorithm.compute(0, 1, 1000), 0);
        assertEquals(algorithm.compute(1, 1000, 1000), 1);
        assertEquals(algorithm.compute(100, 0, 1000), 1);
        assertEquals(algorithm.compute(2, 2, 1000), 4);

        Random rng = new Random(System.nanoTime());
        final int EXPONENT_BOUND = 10000;
        for (int i = 0; i < 100; ++i) {
            long base = rng.nextInt(Integer.MAX_VALUE);
            long exponent = rng.nextInt(EXPONENT_BOUND);
            long modulo = rng.nextInt(Integer.MAX_VALUE) + 1;
            // Exclude the indeterminate case of 0^0.
            if (base == 0) {
                while (exponent == 0) {
                    exponent = rng.nextInt(EXPONENT_BOUND);
                }
            }
            assertEquals(algorithm.compute(base, exponent, modulo),
                    modPowNaive(base, exponent, modulo));
        }
    }

    private long modPowNaive(long base, long exponent, long mod) {
        long result = 1;
        for (int i = 0; i < exponent; ++i) {
            result = result * base % mod;
        }
        return result;
    }
}