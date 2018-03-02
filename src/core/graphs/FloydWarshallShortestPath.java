package core.graphs;

import java.util.Arrays;
import java.util.List;

/**
 * Floyd-Warshall's all pair shortest path algorithm.
 * @author Hieu Le
 * @version 4/16/17
 */
public class FloydWarshallShortestPath {
    public static final int INF = (int) 1e9;

    /**
     * Finds the lengths of the shortest paths between all pairs of nodes in a graph.
     * @param graph the adjacency list representation of the graph
     * @return a 2-dimensional array containing the lengths of the shortest paths.
     * If the length is INF, the graph is not connected.
     */
    public static int[][] floydWarshall(List<Edge>[] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];
        for (int[] a : dist)
            Arrays.fill(a, INF);

        for (int i = 0; i < graph.length; ++i) {
            dist[i][i] = 0;
            for (Edge edge : graph[i]) {
                dist[i][edge.dest] = edge.weight;
            }
        }

        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        return dist;
    }
}
