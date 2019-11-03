package Task.One;


public class Parallel implements Runnable {
    private double[][] matrix,matrix2;
    private static double[][] combined;
    private int s, e;

    public Parallel(double[][] m1, double[][] m2, int start, int end) {
        matrix = m1;
        matrix2 = m2;
        s = start;
        e = end;
    }

    public static double[][] parallelAddMatrix(double[][] a, double[][] b) {
        int size = a.length;
        combined = new double[size][size];

        Parallel p1 = new Parallel(a,b,0, size/4);
        Parallel p2 = new Parallel(a,b,size/4+1, size/2);
        Parallel p3 = new Parallel(a,b,size/2+1,size/4*3);
        Parallel p4 = new Parallel(a,b,size/4*3+1, size);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        long end = System.currentTimeMillis();

        System.out.println("Parallel Add Time: " + (end-start) + " ms");

        return combined;
    }

    @Override
    public void run() {
        for(int i = s; i < e; i++) {
            for(int j = 0; j < matrix.length; j++) {
                combined[i][j] = matrix[i][j] + matrix2[i][j];
            }
        }
    }
}
