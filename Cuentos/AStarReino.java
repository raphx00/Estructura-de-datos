package Cuentos;

import java.util.*;

public class AStarReino {
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
    
    static final int[][] coords = {
        {0, 0}, {0, 4}, {2, 0}, {4, -1}, {6, -1}, {0, -2}, {4, 1}, {8, -3}, {2, -4}, {8, -1}
    };

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

    static long heuristica(int nodo) {
        int xB = coords[BOMBEROS][0], yB = coords[BOMBEROS][1];
        int x = coords[nodo][0], y = coords[nodo][1];
        return Math.abs(x - xB) + Math.abs(y - yB);
    }

    static long aStar(int inicio) {
        long[] gCost = new long[N];
        Arrays.fill(gCost, Long.MAX_VALUE);
        gCost[inicio] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.f));
        pq.add(new Node(inicio, gCost[inicio] + heuristica(inicio)));

        boolean[] closed = new boolean[N];
        while (!pq.isEmpty()) {
            Node nodo = pq.poll();
            int u = nodo.v;
            if (closed[u]) continue;
            closed[u] = true;

            if (u == BOMBEROS) return gCost[u];

            for (Edge e : grafo.get(u)) {
                int v = e.destino;
                long tentativeG = gCost[u] + e.peso;
                if (tentativeG < gCost[v]) {
                    gCost[v] = tentativeG;
                    long f = tentativeG + heuristica(v);
                    pq.add(new Node(v, f));
                }
            }
        }
        return Long.MAX_VALUE;
    }

    static class Node {
        int v;
        long f;
        Node(int v, long f) { this.v = v; this.f = f; }
    }

    public static void main(String[] args) {
        System.out.println("Costo estimado con A* desde CASA hasta BOMBEROS: " + aStar(CASA));
    }
}
