package TURF.DIA_2;

import java.util.*;

public class RottenOranges {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        int minutes = 0;
        
     
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});  
                } else if (grid[i][j] == 1) {
                    freshOranges++;  // Contar naranjas frescas
                }
            }
        }

        if (freshOranges == 0) {
            return 0;
        }

       
        int[] dirs = {-1, 0, 1, 0, -1, 0, 0, 1};

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean hasRotten = false;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int d = 0; d < dirs.length; d += 2) {
                    int newRow = curr[0] + dirs[d];
                    int newCol = curr[1] + dirs[d + 1];
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Pudrir la naranja fresca
                        queue.offer(new int[]{newRow, newCol}); 
                        freshOranges--; 
                        hasRotten = true;
                    }
                }
            }
            if (hasRotten) {
                minutes++; 
            }
        }

       
        return freshOranges == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        RottenOranges solution = new RottenOranges();

        int[][] grid1 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println(solution.orangesRotting(grid1));  // Salida: -1

        int[][] grid2 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 2}
        };
        System.out.println(solution.orangesRotting(grid2));  // Salida: 4
    }
}
