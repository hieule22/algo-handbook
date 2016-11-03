package core.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO(hieule): Add unit tests.
 * Algorithm to find the lowest common ancestor of two nodes in a rooted tree.
 * @author Hieu Le
 * @version 11/1/16
 */
public class LowestCommonAncestor {
    // The root of the tree.
    private int root;
    // The number of nodes in this tree.
    private int nodeCount;
    // The adjacency list representation of the tree.
    private List<List<Integer>> graph;
    // The parents of each node in the tree. The root's parent is -1.
    private int[] parents;
    // The depths of each node in the tree. The root's depth is 1.
    private int[] depths;
    // The height of this tree, i.e. the depth of the lowest node.
    private int height;
    // ancestors[i][j] stores the ancestor located 2^j depths above node i.
    private int[][] ancestors;

    // If ancestors[i][j] == UNDEFINED, node i does not have an ancestor
    // located 2^j nodes above.
    private static final int UNDEFINED = -1;

    public LowestCommonAncestor(List<List<Integer>> graph, int root) {
        this.root = root;
        this.graph = graph;
        nodeCount = graph.size();
        parents = new int[nodeCount];
        depths = new int[nodeCount];
        Arrays.fill(parents, -1);
        bfs();
        prepare();
    }

    /**
     * Returns the lowest common ancestor of two specified nodes in the tree.
     * @param u the first node
     * @param v the second node
     * @return the lowest node that is ancestor to both u and v
     */
    public int getLowestCommonAncestor(int u, int v) {
        if (depths[u] < depths[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Percolate u to the same depth as v.
        int distance = depths[u] - depths[v];
        while (distance > 0) {
            int increase = (int)(Math.log(distance) / Math.log(2));
            u = ancestors[u][increase];
            distance -= (1 << increase);
        }

        if (u == v) {
            return u;
        }

        // Simultaneously percolate both nodes until they share the same parent.
        for (int i = ancestors[0].length - 1; i >= 0; --i) {
            if (ancestors[u][i] != UNDEFINED && ancestors[u][i] != ancestors[v][i]) {
                u = ancestors[u][i];
                v = ancestors[v][i];
            }
        }

        return parents[u];
    }

    // Fill the ancestors[][] matrix.
    private void prepare() {
        int limit = (int) Math.ceil(Math.log(height) / Math.log(2));
        ancestors = new int[nodeCount][limit + 1];
        for (int i = 0; i < nodeCount; ++i) {
            Arrays.fill(ancestors[i], UNDEFINED);
            ancestors[i][0] = parents[i];
        }


        for (int h = 1; h < limit + 1; ++h) {
            for (int i = 0; i < nodeCount; ++i) {
                int ancestor = ancestors[i][h - 1];
                if (ancestor != UNDEFINED) {
                    ancestors[i][h] = ancestors[ancestor][h - 1];
                }
            }
        }
    }

    // Breadth first search to determine the parent of each node.
    private void bfs() {
        boolean[] visited = new boolean[nodeCount];
        Queue<Integer> frontier = new LinkedList<>();
        frontier.add(root);
        height = 0;
        while (!frontier.isEmpty()) {
            int iterations = frontier.size();
            for (int i = 0; i < iterations; ++i) {
                int node = frontier.poll();
                visited[node] = true;
                depths[node] = height;
                for (int child : graph.get(node)) {
                    if (visited[child]) { // Parent node
                        parents[node] = child;
                    } else {
                        frontier.add(child);
                    }
                }
            }
            ++height;
        }
    }
}
