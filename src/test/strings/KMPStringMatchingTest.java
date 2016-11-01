package test.strings;

import core.strings.KMPStringMatching;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Unit tests for KMP String Matching.
 * @author Hieu Le
 * @version 11/1/16
 */
public class KMPStringMatchingTest {

    @Test
    public void testFindMatch() throws Exception {
        {
            final String pattern = "ABCD";
            KMPStringMatching matcher = new KMPStringMatching(pattern);
            List<Integer> matches = matcher.findMatch("ABCD");
            assertTrue(matches.size() == 1 && matches.get(0) == 0);

            matches = matcher.findMatch("ABCABCD");
            assertTrue(matches.size() == 1 && matches.get(0) == 3);

            matches = matcher.findMatch("ABDCACBD");
            assertTrue(matches.isEmpty());

            matches = matcher.findMatch("ABCDMNABCDABC");
            assertTrue(matches.size() == 2);
            assertTrue(matches.get(0) == 0 && matches.get(1) == 6);
        }
        {
            final String pattern = "ABCABCA";
            KMPStringMatching matcher = new KMPStringMatching(pattern);
            List<Integer> matches = matcher.findMatch("ABCABCABCA");
            assertTrue(matches.size() == 2);
            assertTrue(matches.get(0) == 0 && matches.get(1) == 3);
        }
    }
}