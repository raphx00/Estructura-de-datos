package Cuentos;

import java.util.*;

public class JohnsonReino {
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
        grafo.get(CASA).add(new Edge(A2, 2)); grafo.get(A2).add(new Edge(CASA, 2));
        grafo.get(A2).add(new Edge(A1, 5)); grafo.get(A1).add(new Edge(A2, 5));
        grafo.get(A2).add(new Edge(P1, 3)); grafo.get(P1).add(new Edge(A2, 3));
        grafo.get(A2).add(new Edge(A3, 4)); grafo.get(A3).add(new Edge(A2, 4));
        grafo.get(A3).add(new Edge(A4, 2)); grafo.get(A4).add(new Edge(A3, 2));
        grafo.get(CASA).add(new Edge(A6, 4)); grafo.get(A6).add(new Edge(CASA, 4));
        grafo.get(A6).add(new Edge(A3, 1)); grafo.get(A3).add(new Edge(A6, 1));
        grafo.get(A6).add(new Edge(P3, 6)); grafo.get(P3).add(new Edge(A6, 6));
        grafo.get(P3).add(new Edge(P2, 4)); grafo.get(P2).add(new Edge(P3, 4));
        grafo.get(P3).add(new Edge(BOMBEROS, 3)); grafo.get(BOMBEROS).add(new Edge(P3, 3));
        grafo.get(A4).add(new Edge(BOMBEROS, -2)); grafo.get(BOMBEROS).add(new Edge(A4, -2));
    }

    static int[] bellmanFord(int inicio) {
        int[] distancias = new int[N];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[inicio] = 0;

        // Relajamos las aristas N-1 veces
        for (int i = 1; i < N; i++) {
            for (int u = 0; u < N; u++) {
                for (Edge e : grafo.get(u)) {
                    if (distancias[u] != Integer.MAX_VALUE && distancias[u] + e.peso < distancias[e.destino]) {
                        distancias[e.destino] = distancias[u] + e.peso;
                    }
                }
            }
        }

        return distancias;
    }

    static int[] dijkstra(int inicio) {
        int[] distancias = new int[N];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[inicio] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.peso));
        pq.add(new Edge(inicio, 0));

        while (!pq.isEmpty()) {
            Edge nodo = pq.poll();
            for (Edge vecino : grafo.get(nodo.destino)) {
                int nuevaDistancia = distancias[nodo.destino] + vecino.peso;
                if (nuevaDistancia < distancias[vecino.destino]) {
                    distancias[vecino.destino] = nuevaDistancia;
                    pq.add(new Edge(vecino.destino, nuevaDistancia));
                }
            }
        }
        return distancias;
    }

    public static void main(String[] args) {
        System.out.println("Combinación de Bellman-Ford y Dijkstra:");
        // Bellman-Ford
        int[] distanciasBellmanFord = bellmanFord(CASA);
        System.out.println("Distancias usando Bellman-Ford desde CASA:");
        for (int i = 0; i < N; i++) {
            System.out.println("Distancia a " + i + ": " + distanciasBellmanFord[i]);
        }

        // Dijkstra
        int[] distanciasDijkstra = dijkstra(CASA);
        System.out.println("Distancias usando Dijkstra desde CASA:");
        for (int i = 0; i < N; i++) {
            System.out.println("Distancia a " + i + ": " + distanciasDijkstra[i]);
        }
    }
}
