package core.others;

/**
 * The Union-Find Disjoint Set is a data structure that models a collection of disjoint sets with
 * the ability to efficiently determine which set an item belongs to and to unite two disjoint sets
 * into one larger set.
 * @author Hieu Le
 * @version 10/27/16
 */
public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; ++i)
            parent[i] = i;
    }

    // Finds the representative of the set containing node.
    protected int findSet(int node) {
        if (parent[node] == node)
            return node;
        return parent[node] = findSet(parent[node]);  // Path compression.
    }

    // Checks if two elements a and b belong to the same set.
    public boolean isSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }

    // Union the sets containing two elements a and b.
    public void unionSet(int a, int b) {
        if (!isSameSet(a, b)) {
            int pa = parent[a];
            int pb = parent[b];
            // Compare ranks to keep the tree low.
            if (rank[pa] > rank[pb]) {
                parent[pb] = pa;
            } else {
                parent[pa] = pb;
                if (rank[pa] == rank[pb])
                    rank[pb]++;
            }
        }
    }
}
