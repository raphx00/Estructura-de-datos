package TURF.DIA_1;

import java.util.*;

public class GraphTraversal {

    public List<Integer> dfs(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        List<Integer> result = new ArrayList<>();
        dfsUtil(0, adj, visited, result);
        return result;
    }

    private void dfsUtil(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, adj, visited, result);
            }
        }
    }

    public List<Integer> bfs(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        visited[0] = true;
        queue.offer(0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GraphTraversal solution = new GraphTraversal();

        int V1 = 5;
        List<List<Integer>> adj1 = new ArrayList<>();
        adj1.add(Arrays.asList(2, 3, 1));
        adj1.add(Arrays.asList(0, 4));
        adj1.add(Arrays.asList(0, 4));
        adj1.add(Arrays.asList(2, 3, 1));
        adj1.add(Arrays.asList(0, 2));

        List<Integer> dfsResult = solution.dfs(V1, adj1);
        List<Integer> bfsResult = solution.bfs(V1, adj1);

        System.out.println("DFS: " + dfsResult);
        System.out.println("BFS: " + bfsResult);
    }
}
