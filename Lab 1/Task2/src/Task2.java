public class Task2 {
    public static void main(String[] args) {
        //int[] students = new int[100];            never used, dont need
        boolean[] lockers = new boolean[100];

        //initializing all lockers to be closed (false)
        for (int i = 0; i < 100; i++) {
            lockers[i] = false;
        }

        /**for each student(outer loop), it loops every locker(inner loop)
         * increment inner loop by outer loop
         * lockers bool becomes !lockers, switching its state to the opposite
         */

        for (int i = 1; i <= 100; i++) {
            for (int j = i-1; j < 100; j += i) {
                lockers[j] = !lockers[j];
            }
        }

        /**displays the lockers that are open
         * i+1 to not start at 0
         */

        for (int i = 0; i < 100; i++) {
            if (lockers[i]) {
                System.out.print(i+1 + " ");
            }
        }
    }
}
