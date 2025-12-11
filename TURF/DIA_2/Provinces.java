package TURF.DIA_2;

public class Provinces {

    public int findNumberOfProvinces(int V, int[][] adj) {
        boolean[] visited = new boolean[V];
        int provinces = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(i, adj, visited, V);
            }
        }

        return provinces;
    }

    private void dfs(int node, int[][] adj, boolean[] visited, int V) {
        visited[node] = true;
        for (int i = 0; i < V; i++) {
            if (adj[node][i] == 1 && !visited[i]) {
                dfs(i, adj, visited, V);
            }
        }
    }

    public static void main(String[] args) {
        Provinces solution = new Provinces();

        int[][] adj1 = {
            {1, 0, 0, 1},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 1}
        };
        System.out.println("Number of Provinces: " + solution.findNumberOfProvinces(4, adj1));

        int[][] adj2 = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        System.out.println("Number of Provinces: " + solution.findNumberOfProvinces(3, adj2));
    }
}

