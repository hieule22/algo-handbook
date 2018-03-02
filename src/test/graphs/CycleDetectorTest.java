package test.graphs;

import core.graphs.AdjacencyList;
import core.graphs.CycleDetector;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hieu Le
 * @version 3/1/18
 */
public class CycleDetectorTest {
    @Test
    public void isCyclicDirectedGraph() throws Exception {
        AdjacencyList cyclicGraph = new AdjacencyList(4);
        cyclicGraph.addEdge(0, 1);
        cyclicGraph.addEdge(0, 2);
        cyclicGraph.addEdge(1, 2);
        cyclicGraph.addEdge(2, 0);
        cyclicGraph.addEdge(2, 3);
        cyclicGraph.addEdge(3, 3);

        assertTrue(CycleDetector.isCyclicDirectedGraph(cyclicGraph));
    }

    @Test
    public void isCyclicUndirectedGraph() throws Exception {
        AdjacencyList cyclicGraph = new AdjacencyList(5);
        cyclicGraph.addEdge(1, 0);
        cyclicGraph.addEdge(0, 1);
        cyclicGraph.addEdge(0, 2);
        cyclicGraph.addEdge(2, 0);
        cyclicGraph.addEdge(1, 2);
        cyclicGraph.addEdge(2, 1);
        cyclicGraph.addEdge(0, 3);
        cyclicGraph.addEdge(3, 0);
        cyclicGraph.addEdge(3, 4);
        cyclicGraph.addEdge(4, 3);

        assertTrue(CycleDetector.isCyclicGraph(cyclicGraph));

        AdjacencyList acyclicGraph = new AdjacencyList(3);
        acyclicGraph.addEdge(0, 1);
        acyclicGraph.addEdge(1, 0);
        acyclicGraph.addEdge(1, 2);
        acyclicGraph.addEdge(2, 1);

        assertFalse(CycleDetector.isCyclicGraph(acyclicGraph));
    }
}