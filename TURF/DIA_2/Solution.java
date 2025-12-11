package TURF.DIA_2;
// Surrounded Regions (dfs)
public class Solution {
    public char[][] fill(char[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return mat;
        }

        int rows = mat.length;
        int cols = mat[0].length;

        for (int i = 0; i < rows; i++) {
            if (mat[i][0] == 'O') dfs(mat, i, 0, rows, cols);
            if (mat[i][cols - 1] == 'O') dfs(mat, i, cols - 1, rows, cols);
        }

        for (int j = 0; j < cols; j++) {
            if (mat[0][j] == 'O') dfs(mat, 0, j, rows, cols);
            if (mat[rows - 1][j] == 'O') dfs(mat, rows - 1, j, rows, cols);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 'O') {
                    mat[i][j] = 'X';
                }
                if (mat[i][j] == 'T') {
                    mat[i][j] = 'O';
                }
            }
        }

        return mat;
    }

    private void dfs(char[][] mat, int i, int j, int rows, int cols) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || mat[i][j] != 'O') {
            return;
        }

        mat[i][j] = 'T';

        dfs(mat, i + 1, j, rows, cols);
        dfs(mat, i - 1, j, rows, cols);
        dfs(mat, i, j + 1, rows, cols);
        dfs(mat, i, j - 1, rows, cols);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        char[][] mat1 = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };

        solution.fill(mat1);
        System.out.println("Resulting board:");
        for (char[] row : mat1) {
            System.out.println(row);
        }

        char[][] mat2 = {
            {'X', 'O', 'X'},
            {'X', 'X', 'O'},
            {'X', 'O', 'X'}
        };
        solution.fill(mat2);
        System.out.println("Resulting board:");
        for (char[] row : mat2) {
            System.out.println(row);
        }
    }
}
