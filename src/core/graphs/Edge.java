package core.graphs;

/**
 * Representation of an edge. The edge origin is not tracked.
 * For use in adjacency list representation of (un)weighted graph.
 * @author Hieu Le
 * @version 10/31/16
 */
public class Edge {
    // The destination of this edge.
    public int dest;
    // The weight of this edge.
    public int weight;

    /**
     * Constructs an edge with specified destination and weight.
     * @param dest the edge destination
     * @param weight the edge weight
     */
    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Constructs an unweighted edge with specified destination.
     * @param dest the edge destination
     */
    public Edge(int dest) {
        this(dest, 0);
    }
}
