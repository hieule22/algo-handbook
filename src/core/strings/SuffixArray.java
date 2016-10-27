package core.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Suffix array construction.
 * @author Hieu Le
 * @version 10/27/16
 */
public class SuffixArray {
    /**
     * Returns a suffix array constructed from an input character sequence.
     * @param str the input character sequence
     * @return a suffix array constructed from str
     */
    public static int[] construct(CharSequence str) {
        final int length = str.length();
        // maxPower gives the smallest power of 2 greater than or equal to length.
        final int maxPower = (int) Math.ceil(Math.log(length) / Math.log(2));
        Integer[] suffixArr = new Integer[length];
        // ranks[k][] stores the relatives orders of all substrings of length 2^k.
        int[][] ranks = new int[maxPower + 1][length];
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

        return toPrimitive(suffixArr);
    }

    private static int[] toPrimitive(Integer[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; ++i)
            result[i] = arr[i];
        return result;
    }
}
