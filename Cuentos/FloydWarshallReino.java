package Cuentos;

import java.util.*;

public class FloydWarshallReino {
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
    static final int INF = 99999;

    static int[][] grafo = new int[N][N];

    static {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grafo[i][j] = INF;
            }
            grafo[i][i] = 0;  // Distancia a sí mismo es 0
        }

        // Inicializamos las aristas con sus respectivos pesos
        grafo[CASA][A2] = 2; grafo[A2][CASA] = 2;
        grafo[A2][A1] = 5; grafo[A1][A2] = 5;
        grafo[A2][P1] = 3; grafo[P1][A2] = 3;
        grafo[A2][A3] = 4; grafo[A3][A2] = 4;
        grafo[A3][A4] = 2; grafo[A4][A3] = 2;
        grafo[CASA][A6] = 4; grafo[A6][CASA] = 4;
        grafo[A6][A3] = 1; grafo[A3][A6] = 1;
        grafo[A6][P3] = 6; grafo[P3][A6] = 6;
        grafo[P3][P2] = 4; grafo[P2][P3] = 4;
        grafo[P3][BOMBEROS] = 3; grafo[BOMBEROS][P3] = 3;
        grafo[A4][BOMBEROS] = -2; grafo[BOMBEROS][A4] = -2;
    }

    static void floydWarshall() {
        int[][] distancias = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distancias[i][j] = grafo[i][j];
            }
        }

        // Aplicamos el algoritmo Floyd-Warshall
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }

        // Imprimimos la tabla de distancias mínimas
        System.out.println("Tabla de costos mínimos entre todos los lugares:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (distancias[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distancias[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        floydWarshall();
    }
}
