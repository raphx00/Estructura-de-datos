package TURF.DIA_2;

import java.util.*;

public class NearestCellWithOne {

    public int[][] nearestCellWithOne(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distance = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        
        // Inicializar la distancia con un valor grande para celdas no procesadas
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    distance[i][j] = 0;  // La distancia a sí misma es 0
                } else {
                    distance[i][j] = Integer.MAX_VALUE;  // Celdas no visitadas
                }
            }
        }
        
        // Direcciones de los 4 vecinos (arriba, abajo, izquierda, derecha)
        int[] dirs = {-1, 0, 1, 0, 0, -1, 0, 1};
        
        // BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            
            for (int i = 0; i < 4; i++) {
                int newRow = r + dirs[i];
                int newCol = c + dirs[i + 4];
                
                // Verificar si la nueva posición es válida y si la distancia es menor
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (distance[newRow][newCol] > distance[r][c] + 1) {
                        distance[newRow][newCol] = distance[r][c] + 1;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        NearestCellWithOne solution = new NearestCellWithOne();

        int[][] grid1 = {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
        };
        int[][] result1 = solution.nearestCellWithOne(grid1);
        System.out.println(Arrays.deepToString(result1));

        int[][] grid2 = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        int[][] result2 = solution.nearestCellWithOne(grid2);
        System.out.println(Arrays.deepToString(result2));
    }
}

