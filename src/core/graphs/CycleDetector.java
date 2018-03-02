package core.graphs;

/**
 * DFS-based algorithm to detect if a given graph contains a cycle
 * @author Hieu Le
 * @version 3/1/18
 */
public class CycleDetector {

    /**
     * Checks if a given directed graph is cyclic
     * @param graph the adjacency list representation of a directed graph
     * @return true if graph is cyclic; false otherwise
     */
    public static boolean isCyclicDirectedGraph(AdjacencyList graph) {
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

    /**
     * Checks if a given undirected graph is cyclic
     * @param graph the adjacency list representation of an undirected graph
     * @return true if graph is cyclic; false otherwise
     */
    public static boolean isCyclicGraph(AdjacencyList graph) {
        boolean[] visited = new boolean[graph.getSize()];
        for (int node = 0; node < graph.getSize(); ++node)
            if (!visited[node])
                if (isCyclicUtil(node, -1, graph, visited))
                    return true;

        return false;
    }

    private static boolean isCyclicUtil(int node, int parent, AdjacencyList graph, boolean[] visited) {
        visited[node] = true;

        for (Edge edge : graph.getNeighbors(node)) {
            int neighbor = edge.dest;
            if (!visited[neighbor]) {
                if (isCyclicUtil(neighbor, node, graph, visited))
                    return true;
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }
}
