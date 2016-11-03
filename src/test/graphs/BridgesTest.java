package test.graphs;

import com.sun.tools.javac.util.Pair;
import core.graphs.Bridges;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Hieu Le
 * @version 11/3/16
 */
public class BridgesTest {

    @Test
    public void testFindBridges() throws Exception {
        Bridges bridgeFinder = new Bridges();
        List<Pair<Integer, Integer>> bridges = null;
        List<List<Integer>> graph = null;

        // TODO(hieule): Make testing more comprehensive.
        graph = construct(5, new int[][]{{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}});
        bridges = bridgeFinder.findBridges(graph);
        assertEquals(bridges.size(), 2);

        graph = construct(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        bridges = bridgeFinder.findBridges(graph);
        assertEquals(bridges.size(), 3);

        graph = construct(7, new int[][]{
                {0, 1}, {1, 2}, {2, 0}, {1, 3}, {1, 4}, {1, 6}, {3, 5}, {4, 5}});
        bridges = bridgeFinder.findBridges(graph);
        assertEquals(bridges.size(), 1);
    }

    // Returns the adjacency list representation of a graph.
    private List<List<Integer>> construct(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; ++i)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }
}