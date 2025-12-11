package TURF.DIA_2;

import java.util.*;

public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor != newColor) {
            dfs(image, sr, sc, originalColor, newColor);
        }
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int originalColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) {
            return; // Fuera de los límites
        }
        if (image[sr][sc] != originalColor) {
            return; // Si el color actual no coincide con el color original
        }

        // Cambiar el color del píxel
        image[sr][sc] = newColor;

        // Llamadas recursivas para los 4 píxeles adyacentes
        dfs(image, sr + 1, sc, originalColor, newColor); // Abajo
        dfs(image, sr - 1, sc, originalColor, newColor); // Arriba
        dfs(image, sr, sc + 1, originalColor, newColor); // Derecha
        dfs(image, sr, sc - 1, originalColor, newColor); // Izquierda
    }

    public static void main(String[] args) {
        FloodFill solution = new FloodFill();

        int[][] image1 = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr1 = 1, sc1 = 1, newColor1 = 2;
        int[][] result1 = solution.floodFill(image1, sr1, sc1, newColor1);
        System.out.println(Arrays.deepToString(result1));

        int[][] image2 = {
            {0, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        int sr2 = 2, sc2 = 2, newColor2 = 3;
        int[][] result2 = solution.floodFill(image2, sr2, sc2, newColor2);
        System.out.println(Arrays.deepToString(result2));
    }
}
