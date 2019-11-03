package Task.One;

public class TesterClass {
    private final static int size = 2000;
    private static double[][] matrix = new double[size][size];
    private static double[][] matrix2 = new double[size][size];

    public static void main(String[] args) {
        TesterClass tc = new TesterClass();
        tc.start();
        tc.time();
    }

    public void start() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Math.random() * 49 + 1;
                matrix2[i][j] = Math.random() * 49 + 1;
            }
        }
    }


    public void time() {
        sequentialAddMatrix(matrix, matrix2);
        Parallel.parallelAddMatrix(matrix, matrix2);
    }


    public static double[][] sequentialAddMatrix(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        double[][] combined = new double[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                combined[i][j] = a[i][j] + b[i][j];
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("Sequential Add Time: " + (end - start) + " ms");

        return combined;
    }
}
