package core.graphs;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra's single source shortest path problem.
 * @author Hieu Le
 * @version 4/16/17
 */
public class DijkstraShortestPath {
    private static final int INF = (int) 1e9;

    private static class Pair implements Comparable<Pair> {
        private int node;
        private int weight;

        private Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(weight, other.weight);
        }
    }

    /**
     * Returns the lengths of the shortest paths from a starting node to all other nodes in a graph.
     * @param graph adjacency list representation of a graph
     * @param start the starting node
     * @return an array where the ith element denotes the length of the shortest path from start to
     * i
     */
    public static int[] dijkstra(List<Edge>[] graph, int start) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, INF);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Pair(start, dist[start]));

        while (!pq.isEmpty()) {
            Pair head = pq.poll();
            if (dist[head.node] != head.weight)
                continue;

            for (Edge edge : graph[head.node]) {
                if (dist[edge.dest] > dist[head.node] + edge.weight) {
                    dist[edge.dest] = dist[head.node] + edge.weight;
                    pq.add(new Pair(edge.dest, dist[edge.dest]));
                }
            }
        }

        return dist;
    }
}
