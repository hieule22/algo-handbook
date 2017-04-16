package core.math;

import java.util.Arrays;

/**
 * Library of algorithms and techniques to work with prime numbers and prime factorization.
 * @author Hieu Le
 * @version 11/26/16
 */
public class Primality {

    /**
     * Sieve of Eratosthenes algorithm to find all primes up to a maximal value.
     * @param maxValue the upper bound
     * @return an array of boolean values isPrime[] in which for all i <= maxValue, isPrime[i] is
     * true if and only if i is a prime number
     */
    public static boolean[] sieve(int maxValue) {
        boolean[] isPrime = new boolean[maxValue + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        int limit = (int) Math.sqrt(maxValue);
        for (int i = 2; i <= limit; ++i) {
            if (isPrime[i]) {
                for (int j = i; i * j <= maxValue; ++j)
                    isPrime[i * j] = false;
            }
        }
        return isPrime;
    }

    /**
     * Computes the Euler's totient function for a specified value. The totient function, phi(n),
     * is defined as the number of positive integers not exceeding n that are relatively prime
     * to n.
     * @param n a positive integer
     * @return the Euler's totient function of n
     */
    public static int phi(int n) {
        int result = n;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0)
                result -= result / i;
            while (n % i == 0)
                n /= i;
        }
        if (n > 1)
            result -= result / n;
        return result;
    }

    /**
     * Checks if a positive number n is a prime.
     * @param n the positive number whose primality is to be checked
     * @return true if n is prime; false otherwise
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        // Optimization since no even number greater than 2 is prime.
        if (n % 2 == 0) {
            return false;
        }

        int threshold = (int) Math.sqrt(n);
        for (int divisor = 3; divisor <= threshold; divisor += 2) {
            if (n % divisor == 0) {
                return false;
            }
        }
        return true;
    }
}
