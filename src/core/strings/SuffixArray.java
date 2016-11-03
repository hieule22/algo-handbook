package core.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Algorithm to construct a suffix array for an input character sequence
 * and return the length of the longest common prefix between any two suffixes
 * of the sequence.
 * @author Hieu Le
 * @version 10/27/16
 */
public class SuffixArray {
    // suffixArr[i] gives the starting index of the ith smallest suffix.
    private Integer[] suffixArr;
    // ranks[k][] stores the relative orders of all substrings of length 2^k.
    private int[][] ranks;

    /**
     * Constructs a suffix array for an input character sequence.
     * @param str the input character sequence
     */
    public SuffixArray(CharSequence str) {
        final int length = str.length();
        // maxPower gives the smallest power of 2 greater than or equal to length.
        final int maxPower = (int) Math.ceil(Math.log(length) / Math.log(2));
        suffixArr = new Integer[length];
        ranks = new int[maxPower + 1][length];
        for (int i = 0; i < length; ++i) {
            suffixArr[i] = i;
            ranks[0][i] = str.charAt(i);
        }

        for (int i = 1; i <= maxPower; ++i) {
            final int currentPower = i;
            // Custom comparator to sort suffix arrays.
            Comparator<Integer> comparator = (a, b) -> {
                if (ranks[currentPower - 1][a] == ranks[currentPower - 1][b]) {
                    a += (1 << (currentPower - 1));
                    b += (1 << (currentPower - 1));
                }

                if (a >= length)
                    return -1;
                if (b >= length)
                    return 1;
                return ranks[currentPower - 1][a] - ranks[currentPower - 1][b];
            };
            Arrays.sort(suffixArr, comparator);

            // Update ranks.
            int currentRank = ranks[currentPower][suffixArr[0]] = 0;
            for (int j = 1; j < length; ++j) {
                if (comparator.compare(suffixArr[j], suffixArr[j - 1]) != 0)
                    ++currentRank;
                ranks[currentPower][suffixArr[j]] = currentRank;
            }
        }
    }

    /**
     * Gets the underlying suffix array.
     * @return the suffix array associated with this instance.
     */
    public int[] getSuffixArray() {
        return toPrimitive(suffixArr);
    }

    /**
     * Returns the length of the longest common prefix between the suffixes starting at a
     * and b respectively.
     * @param a the starting index of the first suffix
     * @param b the starting index of the second suffix
     * @return the length of the longest prefix between s[a..] and s[b..]
     */
    public int getLongestCommonPrefix(int a, int b) {
        for (int i = ranks.length - 1; i >= 0; --i) {
            if (a + (1 << i) > suffixArr.length || b + (1 << i) > suffixArr.length)
                continue;
            if (ranks[i][a] == ranks[i][b]) {
                return (1 << i) + getLongestCommonPrefix(a + (1 << i), b + (1 << i));
            }
        }
        return 0;
    }

    private static int[] toPrimitive(Integer[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; ++i)
            result[i] = arr[i];
        return result;
    }
}
