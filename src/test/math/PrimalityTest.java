package test.math;

import core.math.Modular;
import core.math.Primality;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hieu Le
 * @version 11/26/16
 */
public class PrimalityTest {

    @Test
    public void sieve() throws Exception {
        final int MAX_VALUE = 10;
        boolean[] isPrime = Primality.sieve(MAX_VALUE);
        int[] primes = {2, 3, 5, 7};
        assertEquals(MAX_VALUE + 1, isPrime.length);
        for (int i = 0; i <= MAX_VALUE; ++i) {
            if (Arrays.binarySearch(primes, i) >= 0)
                assertTrue(isPrime[i]);
            else
                assertFalse(isPrime[i]);
        }

        isPrime = Primality.sieve(1);
        assertTrue(isPrime.length == 2 && !isPrime[0] && !isPrime[1]);

        isPrime = Primality.sieve(10000);
        boolean[] isPrimeExpected = sieveNaive(10000);
        assertEquals(isPrimeExpected.length, isPrime.length);
        for (int i = 0; i < isPrime.length; ++i)
            assertEquals(isPrimeExpected[i], isPrime[i]);
    }

    private boolean[] sieveNaive(int maxValue) {
        boolean[] isPrime = new boolean[maxValue + 1];
        for (int i = 0; i < isPrime.length; ++i)
            isPrime[i] = isPrimeNaive(i);
        return isPrime;
    }

    private boolean isPrimeNaive(int n) {
        if (n == 0 || n == 1)
            return false;
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; ++i) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    @Test
    public void phi() throws Exception {
        assertEquals(1, Primality.phi(1));
        assertEquals(1, Primality.phi(2));
        assertEquals(4, Primality.phi(12));
        assertEquals(96, Primality.phi(97));
        assertEquals(24, Primality.phi(35));

        Random rng = new Random(System.nanoTime());
        for (int i = 0; i < 10; ++i) {
            int n = rng.nextInt(10000);
            assertEquals(phiNaive(n), Primality.phi(n));
        }
    }

    private int phiNaive(int n) {
        int result = 0;
        for (int i = 1; i <= n; ++i) {
            if (Modular.gcd(n, i) == 1)
                ++result;
        }
        return result;
    }
}