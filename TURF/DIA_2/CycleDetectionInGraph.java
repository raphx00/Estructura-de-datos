package TURF.DIA_2;

import java.util.*;

public class CycleDetectionInGraph {

    public boolean hasCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, adj, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int node, List<List<Integer>> adj, boolean[] visited, int parent) {
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, adj, visited, node)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true; // Encontramos un ciclo
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CycleDetectionInGraph solution = new CycleDetectionInGraph();

        int V1 = 6;
        List<List<Integer>> adj1 = new ArrayList<>();
        adj1.add(Arrays.asList(1, 3));
        adj1.add(Arrays.asList(0, 2, 4));
        adj1.add(Arrays.asList(1, 5));
        adj1.add(Arrays.asList(0, 4));
        adj1.add(Arrays.asList(1, 3, 5));
        adj1.add(Arrays.asList(2, 4));
        System.out.println(solution.hasCycle(V1, adj1));  // Output: true

        int V2 = 4;
        List<List<Integer>> adj2 = new ArrayList<>();
        adj2.add(Arrays.asList(1, 2));
        adj2.add(Arrays.asList(0, 2));
        adj2.add(Arrays.asList(0, 1, 3));
        adj2.add(Arrays.asList(2));
        System.out.println(solution.hasCycle(V2, adj2));  // Output: false
    }
}
