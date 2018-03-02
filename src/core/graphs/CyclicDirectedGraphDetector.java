package core.graphs;

/**
 * DFS-based algorithm to detect if a given directed graph contains a cycle
 * @author Hieu Le
 * @version 3/1/18
 */
public class CyclicDirectedGraphDetector {

    /**
     * Checks if a given directed graph is cyclic
     * @param graph the adjacency list representation of a directed graph
     * @return true if graph is cyclic; false otherwise
     */
    public static boolean isCyclic(AdjacencyList graph) {
        boolean[] visited = new boolean[graph.getSize()];
        boolean[] recursionStack = new boolean[graph.getSize()];

        for (int node = 0; node < graph.getSize(); ++node)
            if (isCyclicUtil(node, graph, visited, recursionStack))
                return true;

        return false;
    }

    private static boolean isCyclicUtil(int node, AdjacencyList graph,
                                        boolean[] visited, boolean[] recursionStack) {
        if (!visited[node]) {
            visited[node] = recursionStack[node] = true;
            for (Edge edge : graph.getNeighbors(node)) {
                int neighbor = edge.dest;
                if ((!visited[neighbor] && isCyclicUtil(neighbor, graph, visited, recursionStack))
                        || recursionStack[neighbor])
                    return true;
            }
        }

        recursionStack[node] = false;
        return false;
    }
}
