package Cuentos;

import java.util.*;

public class BellmanFordReino {
    static final int CASA = 0;
    static final int A1 = 1;
    static final int A2 = 2;
    static final int A3 = 3;
    static final int A4 = 4;
    static final int A6 = 5;
    static final int P1 = 6;
    static final int P2 = 7;
    static final int P3 = 8;
    static final int BOMBEROS = 9;
    static final int N = 10;

    static List<List<Edge>> grafo = new ArrayList<>();

    static class Edge {
        int destino, peso;
        Edge(int destino, int peso) { this.destino = destino; this.peso = peso; }
    }

    static {
        for (int i = 0; i < N; i++) grafo.add(new ArrayList<>());
        grafo.get(CASA).add(new Edge(A2, 2));
        grafo.get(A2).add(new Edge(CASA, 2));
        grafo.get(A2).add(new Edge(A1, 5));
        grafo.get(A1).add(new Edge(A2, 5));
        grafo.get(A2).add(new Edge(P1, 3));
        grafo.get(P1).add(new Edge(A2, 3));
        grafo.get(A2).add(new Edge(A3, 4));
        grafo.get(A3).add(new Edge(A2, 4));
        grafo.get(A3).add(new Edge(A4, 2));
        grafo.get(A4).add(new Edge(A3, 2));
        grafo.get(CASA).add(new Edge(A6, 4));
        grafo.get(A6).add(new Edge(CASA, 4));
        grafo.get(A6).add(new Edge(A3, 1));
        grafo.get(A3).add(new Edge(A6, 1));
        grafo.get(A6).add(new Edge(P3, 6));
        grafo.get(P3).add(new Edge(A6, 6));
        grafo.get(P3).add(new Edge(P2, 4));
        grafo.get(P2).add(new Edge(P3, 4));
        grafo.get(P3).add(new Edge(BOMBEROS, 3));
        grafo.get(BOMBEROS).add(new Edge(P3, 3));
        grafo.get(A4).add(new Edge(BOMBEROS, -2));
        grafo.get(BOMBEROS).add(new Edge(A4, -2));
    }

    static int[] bellmanFord() {
        int[] distancias = new int[N];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[CASA] = 0;

        // Relajar las aristas N-1 veces
        for (int i = 1; i < N; i++) {
            for (int u = 0; u < N; u++) {
                for (Edge e : grafo.get(u)) {
                    if (distancias[u] != Integer.MAX_VALUE && distancias[u] + e.peso < distancias[e.destino]) {
                        distancias[e.destino] = distancias[u] + e.peso;
                    }
                }
            }
        }

        // Detectar ciclos negativos
        for (int u = 0; u < N; u++) {
            for (Edge e : grafo.get(u)) {
                if (distancias[u] != Integer.MAX_VALUE && distancias[u] + e.peso < distancias[e.destino]) {
                    System.out.println("Ciclo negativo detectado");
                }
            }
        }

        return distancias;
    }

    public static void main(String[] args) {
        System.out.println("Bellman-Ford desde CASA:");
        int[] distancias = bellmanFord();
        for (int i = 0; i < N; i++) {
            System.out.println("Distancia a " + i + ": " + distancias[i]);
        }
    }
}
