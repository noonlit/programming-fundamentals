package practice;

public class PascalTriangle
{
    public static void main(String[] args)
    {
        int n = 4;
        int[][] triangle = buildTriangle(n);
        printMatrix(triangle, n);
    }

    /**
     * Builds Pascal's triangle with n lines.
     * 
     * @param int n
     * @return int[][]
     */
    private static int[][] buildTriangle(int n)
    {
        int[][] result = new int[n][n];

        for (int line = 0; line < n; line++) {
            for (int i = 0; i <= line; i++) {
                /*
                 * First and last values in every line are 1
                 */
                if (line == i || i == 0) {
                    result[line][i] = 1;
                    continue;
                }

                /*
                 * Other values are sum of values left of above and just above 
                 */
                result[line][i] = result[line - 1][i - 1] + result[line - 1][i];
            }
        }

        return result;
    }
    
    /**
     * Prints given matrix.
     * 
     * @param matrix
     * @param n
     */
    private static void printMatrix(int[][] matrix, int n)
    {
        for (int line = 0; line < n; line++) {
            for (int i = 0; i <= line; i++) {
                System.out.print(matrix[line][i] + " ") ;
            }
            System.out.println("");
        }
    }
}
