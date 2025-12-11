package Cuentos;

import java.util.*;

public class TopoOrdenPerfecto {
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

    static List<List<Integer>> grafo = new ArrayList<>();

    static {
        for (int i = 0; i < N; i++) grafo.add(new ArrayList<>());
        grafo.get(CASA).add(A2); grafo.get(A2).add(CASA);
        grafo.get(A2).add(A1); grafo.get(A1).add(A2);
        grafo.get(A2).add(P1); grafo.get(P1).add(A2);
        grafo.get(A2).add(A3); grafo.get(A3).add(A2);
        grafo.get(A3).add(A4); grafo.get(A4).add(A3);
        grafo.get(CASA).add(A6); grafo.get(A6).add(CASA);
        grafo.get(A6).add(A3); grafo.get(A3).add(A6);
        grafo.get(A6).add(P3); grafo.get(P3).add(A6);
        grafo.get(P3).add(P2); grafo.get(P2).add(P3);
        grafo.get(P3).add(BOMBEROS); grafo.get(BOMBEROS).add(P3);
        grafo.get(A4).add(BOMBEROS); grafo.get(BOMBEROS).add(A4);
    }

    static void topoOrdenadoUtil(int nodo, boolean[] visitado, Stack<Integer> stack) {
        visitado[nodo] = true;
        for (int vecino : grafo.get(nodo)) {
            if (!visitado[vecino]) {
                topoOrdenadoUtil(vecino, visitado, stack);
            }
        }
        stack.push(nodo);
    }

    static void ordenarTopologico() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visitado = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!visitado[i]) {
                topoOrdenadoUtil(i, visitado, stack);
            }
        }

        System.out.println("Orden topológico:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        ordenarTopologico();
    }
}
