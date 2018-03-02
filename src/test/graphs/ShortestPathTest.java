package test.graphs;

import core.graphs.DijkstraShortestPath;
import core.graphs.Edge;
import core.graphs.FloydWarshallShortestPath;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Hieu Le
 * @version 4/16/17
 */
public class ShortestPathTest {
    private List<Edge>[] graph;

    @Before
    public void setUp() throws Exception {
        graph = new List[3];
        for (int i = 0; i < graph.length; ++i)
            graph[i] = new ArrayList<>();
        addEdge(0, 1, 10, graph);
        addEdge(0, 2, 1000, graph);
        addEdge(1, 2, 20, graph);
    }

    @Test
    public void dijkstra() throws Exception {
        int[] dist = DijkstraShortestPath.dijkstra(graph, 0);
        assertEquals(0, dist[0]);
        assertEquals(10, dist[1]);
        assertEquals(30, dist[2]);
    }

    @Test
    public void floydWarshall() throws Exception {
        int[][] dist = FloydWarshallShortestPath.floydWarshall(graph);
        for (int i = 0; i < graph.length; ++i)
            assertEquals(0, dist[i][i]);
        assertEquals(10, dist[0][1]);
        assertEquals(10, dist[1][0]);
        assertEquals(30, dist[0][2]);
        assertEquals(30, dist[2][0]);
        assertEquals(20, dist[1][2]);
        assertEquals(20, dist[2][1]);
    }

    private void addEdge(int a, int b, int weight, List<Edge>[] graph) {
        graph[a].add(new Edge(b, weight));
        graph[b].add(new Edge(a, weight));
    }
}