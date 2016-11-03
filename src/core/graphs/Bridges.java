package core.graphs;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm to find bridges in a given directed graph.
 * @author Hieu Le
 * @version 11/3/16
 */
public class Bridges {
    // The adjacency list representation of the graph.
    private List<List<Integer>> graph;
    // The global discovery timer.
    private int time;

    // If parent[i] == UNDEFINED, node i does not have a parent in a DFS spanning tree yet.
    private static final int UNDEFINED = -1;

    /**
     * Gets the bridges for a given graph.
     * @param graph the adjacency matrix representation of the graph
     * @return a list of integer pairs denoting the ends of all bridges
     */
    public List<Pair<Integer, Integer>> findBridges(List<List<Integer>> graph) {
        this.graph = graph;
        final int V = graph.size();
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];

        // Initialize parent[i] to UNDEFINED and visited[i] to false.
        for (int i = 0; i < V; ++i) {
            parent[i] = UNDEFINED;
            visited[i] = false;
        }

        time = 0;
        List<Pair<Integer, Integer>> bridges = new ArrayList<>();
        // Recursively perform depth first search on any unvisited node.
        for (int i = 0; i < V; ++i) {
            if (!visited[i])
                dfs(i, visited, disc, low, parent, bridges);
        }

        return bridges;
    }

    private void dfs(int u, boolean[] visited, int[] disc, int[] low, int[] parent,
                     List<Pair<Integer, Integer>> bridges) {
        // Mark the current node as visited.
        visited[u] = true;

        // Initialize discovery time and low value of u.
        disc[u] = low[u] = ++time;

        // Examine all the neighbors of u.
        for (int v : graph.get(u)) {
            // If v is not visited yet, then make it a child of u in the current DFS spanning
            // tree and recursively explore the subtree rooted at v.
            if (!visited[v]) {
                parent[v] = u;
                dfs(v, visited, disc, low, parent, bridges);

                // Check if the subtree rooted at v has a back edge to an ancestor of u.
                low[u] = Math.min(low[u], low[v]);

                // If the lowest vertex reachable from subtree rooted at v is not an ancestor of
                // u in the DFS tree, then u-v is a bridge.
                if (low[v] > disc[u])
                    bridges.add(new Pair<>(u, v));
            } else if (v != parent[u]) {  // Back edge !!
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
