package core.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Tarjan's algorithm to find all strongly connected components in a directed graph.
 * @author Hieu Le
 * @version 3/13/18
 */
public final class TarjanSCC {
    private static boolean[] visited;
    private static Stack<Integer> stack;
    private static int time;
    private static int[] lowlink;
    private static List<List<Integer>> components;

    private TarjanSCC() {
        // Hide class constructor
    }

    public static List<List<Integer>> getSCC(List<Integer>[] graph) {
        int n = graph.length;
        visited = new boolean[n];
        stack = new Stack<>();
        time = 0;
        lowlink = new int[n];
        components = new ArrayList<>();

        for (int u = 0; u < n; u++)
            if (!visited[u])
                dfs(u, graph);

        return components;
    }

    private static void dfs(int u, List<Integer>[] graph) {
        lowlink[u] = time++;
        visited[u] = true;
        stack.add(u);
        boolean isComponentRoot = true;

        for (int v : graph[u]) {
            if (!visited[v])
                dfs(v, graph);
            if (lowlink[u] > lowlink[v]) {
                lowlink[u] = lowlink[v];
                isComponentRoot = false;
            }
        }

        if (isComponentRoot) {
            List<Integer> component = new ArrayList<>();
            while (true) {
                int x = stack.pop();
                component.add(x);
                lowlink[x] = Integer.MAX_VALUE;
                if (x == u)
                    break;
            }
            components.add(component);
        }
    }
}
