package Task.Two;

public class ReverseThread implements Runnable {
    private static int counter;
    public static void main(String[] args) {

        Thread[] t = new Thread[50];
        for(int i = 49; i >=0; i--) {
            t[i] = new Thread(new ReverseThread());
            counter = i+1;
            t[i].start();

            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        System.out.println("Hello from Thread " + counter);
    }

}
