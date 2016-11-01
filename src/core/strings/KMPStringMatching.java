package core.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Searches for string w in string s (of length k). Returns the
 * 0-based indices of all matches. Algorithm runs in O(k) time.
 * @author Hieu Le
 * @version 11/1/16
 */
public class KMPStringMatching {
    // The pattern string to match.
    private String pattern;
    // The reset table used when a mismatch occurs.
    private int[] backTable;

    /**
     * Constructs a string matcher from an input pattern.
     * @param pattern the pattern to match
     */
    public KMPStringMatching(String pattern) {
        this.pattern = pattern;
        prepare();
    }

    /**
     * Returns a list of indices where the pattern is found in a target text.
     * @param text the string to search from
     * @return a list of matching indices
     */
    public List<Integer> findMatch(String text) {
        List<Integer> matches = new ArrayList<>();
        int i = 0, j = 0;
        while (i < text.length()) {
            while (j >= 0 && text.charAt(i) != pattern.charAt(j))
                j = backTable[j];
            ++i;
            ++j;
            if (j == pattern.length()) {
                matches.add(i - j);
                j = backTable[j];
            }
        }
        return matches;
    }

    private void prepare() {
        backTable = new int[pattern.length() + 1];
        int i = 0, j = -1;
        backTable[0] = -1;
        while (i < pattern.length()) {
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j))
                j = backTable[j];
            ++i;
            ++j;
            backTable[i] = j;
        }
    }
}
