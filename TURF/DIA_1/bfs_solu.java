package TURF.DIA_1;

import java.util.*;

public class bfs_solu {

    // DFS -------------------------------------------------------------

    private static void dfsUtil(int node,
                                boolean[] visited,
                                ArrayList<ArrayList<Integer>> adj,
                                ArrayList<Integer> result) {
        visited[node] = true;
        result.add(node);

        for (int neigh : adj.get(node)) {
            if (!visited[neigh]) {
                dfsUtil(neigh, visited, adj, result);
            }
        }
    }

    public static ArrayList<Integer> dfsOfGraph(int V,
                     ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];

        dfsUtil(0, visited, adj, result);
        return result;
    }

    // BFS -------------------------------------------------------------

    public static ArrayList<Integer> bfsOfGraph(int V,
                     ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();

        // empezamos desde 0
        visited[0] = true;
        q.offer(0);

        while (!q.isEmpty()) {
            int u = q.poll();
            result.add(u);

            for (int neigh : adj.get(u)) {
                if (!visited[neigh]) {
                    visited[neigh] = true;
                    q.offer(neigh);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // grafo no dirigido
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        ArrayList<Integer> dfs = dfsOfGraph(V, adj);
        ArrayList<Integer> bfs = bfsOfGraph(V, adj);

        System.out.print("DFS: ");
        for (int x : dfs) System.out.print(x + " ");
        System.out.println();

        System.out.print("BFS: ");
        for (int x : bfs) System.out.print(x + " ");
        System.out.println();

        sc.close();
    }
}

