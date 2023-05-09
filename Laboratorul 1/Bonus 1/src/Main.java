import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Cycle Graph: *********************************************************/n");
        int n = 5; // number of vertices
        int[][] adjMatrix = new int[n][n]; // initialize adjacency matrix

        // create cycle graph adjacency matrix
        for (int i = 0; i < n; i++) {
            adjMatrix[i][(i + 1) % n] = 1;
            adjMatrix[(i + 1) % n][i] = 1;
        }

        // print adjacency matrix
        System.out.println("Adjacency matrix:");
        for (int[] row : adjMatrix) {
            System.out.println(Arrays.toString(row));
        }

        // compute powers A2, A3, ..., An
        int[][] powers = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                powers[i][j] = adjMatrix[i][j];
            }
        }
        for (int p = 2; p <= n; p++) {
            powers = multiplyMatrices(powers, adjMatrix);
            System.out.println("A" + p + ":");
            for (int[] row : powers) {
                System.out.println(Arrays.toString(row));
            }
        }
        System.out.println("Regular Graph: *********************************************************/n");
        int degree = 2; // vertex degree
        int[][] adjMatrix2 = new int[n][n]; // initialize adjacency matrix2

        // create regular graph adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n && adjMatrix2[i][j] < degree; j++) {
                adjMatrix2[i][j] = 1;
                adjMatrix2[j][i] = 1;
            }
        }

        // print adjacency matrix
        System.out.println("Adjacency matrix:");
        for (int[] row : adjMatrix2) {
            System.out.println(Arrays.toString(row));
        }
    }
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        // Check if matrices can be multiplied
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Matrices cannot be multiplied: invalid dimensions");
        }

        int[][] result = new int[rowsA][colsB];

        // Compute matrix multiplication
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

}