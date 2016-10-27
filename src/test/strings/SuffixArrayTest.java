package test.strings;

import core.strings.SuffixArray;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/**
 * Unit tests for suffix array construction.
 * @author Hieu Le
 * @version 10/27/16
 */
public class SuffixArrayTest {

    @org.junit.Test
    public void testConstructBasic() throws Exception {
        assertArrayEquals(SuffixArray.construct("a"), new int[] {0});
        assertArrayEquals(SuffixArray.construct("ab"), new int[] {0, 1});
        assertArrayEquals(SuffixArray.construct("aa"), new int[] {1, 0});
        assertArrayEquals(SuffixArray.construct("dcba"), new int[] {3, 2, 1, 0});
        assertArrayEquals(SuffixArray.construct("aabaab"), new int[] {3, 0, 4, 1, 5, 2});
        assertArrayEquals(SuffixArray.construct("bbaa"), new int[] {3, 2, 1, 0});
    }

    @org.junit.Test
    public void testConstructLarge() throws Exception {
        Random rng = new Random(System.currentTimeMillis());
        final int ALPHABET = 256;
        final int LENGTH = 10000;
        StringBuilder s = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; ++i) {
            char c = (char) (rng.nextInt() % ALPHABET);
            s.append(c);
        }
        assertArrayEquals(SuffixArray.construct(s), constructNaive(s));
    }

    private int[] constructNaive(CharSequence str) {
        final int length = str.length();
        Integer[] suffixArr = new Integer[length];
        for (int i = 0; i < length; ++i)
            suffixArr[i] = i;
        Arrays.sort(suffixArr, (a, b) -> {
            while (a < length && b < length) {
                if (str.charAt(a) != str.charAt(b))
                    return str.charAt(a) - str.charAt(b);
                ++a;
                ++b;
            }
            return b - a;
        });

        int[] result = new int[length];
        for (int i = 0; i < length; ++i)
            result[i] = suffixArr[i];
        return result;
    }
}