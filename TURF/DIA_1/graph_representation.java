package TURF.DIA_1;

import java.util.Scanner;

public class graph_representation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // number of nodes
        int n = sc.nextInt(); 
        // number of edges
        int m = sc.nextInt(); 

        // adjacency matrix
        int[][] adj = new int[n + 1][n + 1]; 

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u][v] = 1;
            // remove this line in case of directed graph
            adj[v][u] = 1; 
        }

        sc.close();
    }
}