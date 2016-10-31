package core.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * The Adjacency List representation of a graph.
 * @author Hieu Le
 * @version 10/31/16
 */
public class AdjacencyList {

    // neighbors[i] stores a list of edges adjacent to node i.
    private List<List<Edge>> neighbors;

    /**
     * Constructs an adjacency list for a graph of given size.
     * @param graphSize the number of nodes in the graph
     */
    public AdjacencyList(int graphSize) {
        neighbors = new ArrayList<>(graphSize);
        for (int i = 0; i < graphSize; ++i)
            neighbors.add(new ArrayList<>());
    }

    /**
     * Adds an edge emanating from origin to the graph.
     * @param origin the starting node of this edge
     * @param edge the edge to be added
     */
    public void addEdge(int origin, Edge edge) {
        neighbors.get(origin).add(edge);
    }

    /**
     * Forms a directed weighted edge coming from a node to another. For an
     * undirected graph, addEdge(u, v) and addEdge(v, u) have to be invoked.
     * @param origin the origin of this edge
     * @param destination the destination of this edge
     * @param weight the weight of this edge
     */
    public void addEdge(int origin, int destination, int weight) {
        addEdge(origin, new Edge(destination, weight));
    }

    /**
     * Adds an unweighted edge from origin to destination.
     * @param origin the origin of this edge
     * @param destination the destination of this edge
     */
    public void addEdge(int origin, int destination) {
        addEdge(origin, destination, 0);
    }

    /**
     * Checks if an edge that begins at origin and ends at destination exists.
     * Worst-case runtime complexity: O(size of graph)
     * @param origin the edge origin
     * @param destination the edge destination
     * @return true if an edge from origin to destination exists;
     * false otherwise
     */
    public boolean hasEdge(int origin, int destination) {
        for (Edge edge : getNeighbors(origin)) {
            if (edge.dest == destination)
                return true;
        }
        return false;
    }

    /**
     * Returns a list of neighbors for a specified node.
     * @param node the node whose neighbors need to be returned
     * @return the list of neighbors to node
     */
    public List<Edge> getNeighbors(int node) {
        return neighbors.get(node);
    }
}
