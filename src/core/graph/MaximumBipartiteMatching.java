package core.graph;

import java.util.Arrays;

/**
 * Maximum bipartite matching.
 * Running time: O(|E||V|).
 * @author Hieu Le
 * @version 10/31/16
 */
public class MaximumBipartiteMatching {

    /**
     * Finds the maximum bipartite matching for an input graph and fills in the
     * corresponding neighbor for each node in that maximum matching.
     * @param graph the input graph
     * @param matchRow mappings from row nodes to column nodes
     * @param matchCol mappings from column nodes to row nodes
     * @return the maximum number of matches in the bipartite graph
     */
    public static int maximizeBipartiteMatch(boolean[][] graph, int[] matchRow, int[] matchCol) {
        Arrays.fill(matchRow, -1);
        Arrays.fill(matchCol, -1);

        int result = 0;
        for (int i = 0; i < graph.length; ++i) {
            boolean[] seen = new boolean[matchCol.length];
            if (findMatch(i, graph, matchRow, matchCol, seen))
                result++;
        }

        return result;
    }

    /**
     * Finds match for a given row node.
     * @param node the input row node
     * @param graph the input bipartite graph
     * @param matchRow mappings from row nodes to column nodes
     * @param matchCol mappings from column nodes to row nodes
     * @param seen keeps track of which column nodes have been considered
     * @return true if the graph can be further augmented to match this node
     */
    private static boolean findMatch(int node, boolean[][] graph, int[] matchRow, int[] matchCol,
                              boolean[] seen) {
        for (int i = 0; i < graph[node].length; ++i) {
            if (graph[node][i] && !seen[i]) {
                seen[i] = true;
                if (matchCol[i] < 0 || findMatch(matchCol[i], graph, matchRow, matchCol, seen)) {
                    matchRow[node] = i;
                    matchCol[i] = node;
                    return true;
                }
            }
        }
        return false;
    }
}
