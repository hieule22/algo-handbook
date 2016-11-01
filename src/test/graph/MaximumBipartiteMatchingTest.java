package test.graph;

import core.graph.MaximumBipartiteMatching;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

/**
 * @author Hieu Le
 * @version 10/31/16
 */
public class MaximumBipartiteMatchingTest {

    @Test
    public void testMaxBipartiteMatch() throws Exception {
        boolean[][] graph = new boolean[][] {
                {false, true, true, false, false, false},
                {true, false, false, true, false, false},
                {false, false, true, false, false, false},
                {false, false, true, true, false, false},
                {false, false, false, false, false, false},
                {false, false, false, false, false, true}
        };

        int[] matchRow = new int[graph.length];
        int[] matchCol = new int[graph[0].length];

        int result = MaximumBipartiteMatching.maximizeBipartiteMatch(graph, matchRow, matchCol);
        assertEquals(result, 5);

        boolean[] seen = new boolean[matchCol.length];
        int actualCount = 0;
        for (int i = 0; i < matchRow.length; ++i) {
            int column = matchRow[i];
            if (column >= 0) {
                assertFalse(seen[column]);
                seen[column] = true;
                assertEquals(matchCol[column], i);
                actualCount++;
            }
        }
        assertEquals(actualCount, result);

        actualCount = 0;
        for (int row : matchCol) {
            if (row >= 0) {
                actualCount++;
            }
        }
        assertEquals(actualCount, result);
    }
}