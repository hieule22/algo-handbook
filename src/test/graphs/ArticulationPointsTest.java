package test.graphs;

import core.graphs.ArticulationPoints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Hieu Le
 * @version 11/3/16
 */
public class ArticulationPointsTest {

    @Test
    public void testFindArticulationPoints() throws Exception {
        ArticulationPoints apSolver = new ArticulationPoints();
        {
            List<List<Integer>> graph =
                    construct(5, new int[][]{{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}});
            List<Integer> articulationPoints = apSolver.findArticulationPoints(graph);
            assertTrue(articulationPoints.size() == 2);
            assertTrue(articulationPoints.indexOf(0) >= 0);
            assertTrue(articulationPoints.indexOf(3) >= 0);
        }
        {
            List<List<Integer>> graph =
                    construct(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
            List<Integer> articulationPoints = apSolver.findArticulationPoints(graph);
            assertTrue(articulationPoints.size() == 2);
            assertTrue(articulationPoints.indexOf(1) >= 0);
            assertTrue(articulationPoints.indexOf(2) >= 0);
        }
        {
            List<List<Integer>> graph = construct(7, new int[][]{
                    {0, 1}, {1, 2}, {2, 0}, {1, 3}, {1, 4}, {1, 6}, {3, 5}, {4, 5}});
            List<Integer> articulationPoints = apSolver.findArticulationPoints(graph);
            assertTrue(articulationPoints.size() == 1);
            assertTrue(articulationPoints.indexOf(1) >= 0);
        }
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