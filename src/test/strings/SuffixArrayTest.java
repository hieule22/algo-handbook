package test.strings;

import core.strings.SuffixArray;

import java.util.Arrays;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * Unit tests for suffix array construction.
 * @author Hieu Le
 * @version 10/27/16
 */
public class SuffixArrayTest {

    @org.junit.Test
    public void testGetSuffixArrayBasic() throws Exception {
        assertArrayEquals(new SuffixArray("a").getSuffixArray(), new int[] {0});
        assertArrayEquals(new SuffixArray("ab").getSuffixArray(), new int[] {0, 1});
        assertArrayEquals(new SuffixArray("aa").getSuffixArray(), new int[] {1, 0});
        assertArrayEquals(new SuffixArray("dcba").getSuffixArray(), new int[] {3, 2, 1, 0});
        assertArrayEquals(new SuffixArray("aabaab").getSuffixArray(), new int[] {3, 0, 4, 1, 5, 2});
        assertArrayEquals(new SuffixArray("bbaa").getSuffixArray(), new int[] {3, 2, 1, 0});
    }

    @org.junit.Test
    public void testGetSuffixArrayLarge() throws Exception {
        final int LENGTH = 100000;
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; ++i)
            sb.append('a');

        int[] expected = new int[LENGTH];
        for (int i = 0; i < LENGTH; ++i)
            expected[i] = LENGTH - i - 1;
        SuffixArray suffixArray = new SuffixArray(sb);

        assertArrayEquals(suffixArray.getSuffixArray(), expected);
    }

    @org.junit.Test
    public void testGetSuffixArrayRandom() throws Exception {
        Random rng = new Random(System.currentTimeMillis());
        final int ALPHABET = 256;
        final int LENGTH = 10000;
        StringBuilder s = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; ++i) {
            char c = (char) (rng.nextInt() % ALPHABET);
            s.append(c);
        }
        assertArrayEquals(new SuffixArray(s).getSuffixArray(), getSuffixArrayNaive(s));
    }

    private int[] getSuffixArrayNaive(CharSequence str) {
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

    @org.junit.Test
    public void testGetLongestCommonPrefixBasic() throws Exception {
        SuffixArray suffixArray = new SuffixArray("aabaabaa");
        assertEquals(suffixArray.getLongestCommonPrefix(0, 2), 0);
        assertEquals(suffixArray.getLongestCommonPrefix(2, 5), 3);
        assertEquals(suffixArray.getLongestCommonPrefix(0, 6), 2);
        assertEquals(suffixArray.getLongestCommonPrefix(0, 7), 1);
        assertEquals(suffixArray.getLongestCommonPrefix(4, 4), 4);
    }

    @org.junit.Test
    public void testGetLongestCommonPrefixLarge() throws Exception {
        final int LENGTH = 100000;
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; ++i)
            sb.append('a');
        SuffixArray suffixArray = new SuffixArray(sb);
        assertEquals(suffixArray.getLongestCommonPrefix(0, 0), LENGTH);
        assertEquals(suffixArray.getLongestCommonPrefix(0, LENGTH - 1), 1);
        assertEquals(suffixArray.getLongestCommonPrefix(0, 1), LENGTH - 1);
    }

    @org.junit.Test
    public void testGetLongestCommonPrefixRandom() throws Exception {
        final int LENGTH = 10000;
        Random rng = new Random(System.nanoTime());
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; ++i) {
            char c = (char)('a' + rng.nextInt(2));
            sb.append(c);
        }
        SuffixArray suffixArray = new SuffixArray(sb);
        for (int i = 0; i < 100; ++i) {
            int a = rng.nextInt(LENGTH);
            int b = rng.nextInt(LENGTH);
            assertEquals(suffixArray.getLongestCommonPrefix(a, b),
                    getLongestCommonPrefixNaive(sb, a, b));
        }
    }

    private int getLongestCommonPrefixNaive(CharSequence s, int a, int b) {
        int result = 0;
        while (a < s.length() && b < s.length() && s.charAt(a) == s.charAt(b)) {
            ++result;
            ++a;
            ++b;
        }
        return result;
    }
}