package core.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * An algorithm to find the articulation points in an undirected graph.
 * Articulation points are defined as nodes whose removal would increase the number
 * of connected components in the graph.
 * @author Hieu Le
 * @version 11/3/16
 */
public class ArticulationPoints {

    // The adjacency list representation of the graph.
    private List<List<Integer>> graph;
    // The global discovery timer.
    private int time;
    // If parent[i] == UNDEFINED, node i does not have a parent in the DFS spanning tree yet.
    private static final int UNDEFINED = -1;

    public List<Integer> findArticulationPoints(List<List<Integer>> graph) {
        int v = graph.size();
        this.graph = graph;
        // visited[i] indicates if node i has been visited.
        boolean[] visited = new boolean[v];
        // disc[i] stores the discovery time of node[i].
        int[] disc = new int[v];
        // low[i] stores the minimum discovery time of a node reachable from i
        // via a back edge.
        int[] low = new int[v];
        // parent[i] stores the parent vertices of node i in the DFS spanning tree.
        int[] parent = new int[v];
        // ap[i] indicates if node i is an articulation point.
        boolean[] ap = new boolean[v];

        for (int i = 0; i < v; ++i) {
            parent[i] = UNDEFINED;
            visited[i] = ap[i] = false;
        }

        // Call the recursive helper function to find articulation points in the DFS spanning
        // tree rooted at node i.
        time = 0;  // Reset the global time counter to 0.
        for (int i = 0; i < v; ++i) {
            if (!visited[i]) {
                findArticulationPointsImpl(i, visited, disc, low, parent, ap);
            }
        }

        // Now ap[] contains the articulation points.
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            if (ap[i])
                result.add(i);
        }
        return result;
    }

    private void findArticulationPointsImpl(int u, boolean[] visited, int[] disc,
                                            int[] low, int[] parent, boolean[] ap) {
        // Count the number of children of u in the DFS tree.
        int children = 0;
        // Mark the current node as visited.
        visited[u] = true;
        // Initialize discovery time and low value.
        disc[u] = low[u] = ++time;

        // Examine all neighbors of u.
        for (int v : graph.get(u)) {
            // If v is not visited yet, then make it a child of u in the DFS tree
            // and recursively explore the subtree rooted at v.
            if (!visited[v]) {
                ++children;
                parent[v] = u;
                findArticulationPointsImpl(v, visited, disc, low, parent, ap);

                // Check if the subtree rooted has a back edge to an ancestor of u.
                low[u] = Math.min(low[u], low[v]);

                // u is an articulation point in one of the following cases.
                // (1) u is the root of the DFS tree and has two or more children.
                if (parent[u] == UNDEFINED && children > 1)
                    ap[u] = true;
                // (2) If u is not the root of the current DFS tree and the low value of one of its
                // children is greater than the discovery time of u.
                if (parent[u] != UNDEFINED && low[v] >= disc[u])
                    ap[u] = true;
            } else if (v != parent[u]) {  // Back edge !!
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }


}
