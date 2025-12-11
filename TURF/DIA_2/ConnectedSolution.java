package TURF.DIA_2;

import java.util.*;

public class ConnectedSolution {

    public int findNumberOfComponent(int V, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        int components = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, graph, visited);
            }
        }

        return components;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        ConnectedSolution solution = new ConnectedSolution();
        int V = 7;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0, 1));
        edges.add(Arrays.asList(1, 2));
        edges.add(Arrays.asList(2, 3));
        edges.add(Arrays.asList(4, 5));

        int result = solution.findNumberOfComponent(V, edges);
        System.out.println("Número de componentes conectados: " + result);
    }
}
