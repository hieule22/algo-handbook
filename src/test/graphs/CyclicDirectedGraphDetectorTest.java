package test.graphs;

import core.graphs.AdjacencyList;
import core.graphs.CyclicDirectedGraphDetector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Hieu Le
 * @version 3/1/18
 */
public class CyclicDirectedGraphDetectorTest {
    @Test
    public void isCyclic() throws Exception {
        AdjacencyList graph = new AdjacencyList(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        assertTrue(CyclicDirectedGraphDetector.isCyclic(graph));
    }
}