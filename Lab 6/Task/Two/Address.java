package Task.Two;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Address {

    private static final String FILE = "address.txt";
    private static final int SIZE = 102;

    public static void openFile() {
        try {
            PrintWriter pw = new PrintWriter(FILE);
            pw.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public static byte[] readFile(int pos) {
        try {
            RandomAccessFile raf = new RandomAccessFile(FILE, "r");
            raf.seek(pos);
            byte[] address = new byte[SIZE];
            raf.read(address);
            return address;
        } catch (IOException e) {
            System.out.println("File not found.");
            return null;
        }
    }

    public static void writeAddress(int pos, String address) {
        try {
            RandomAccessFile raf = new RandomAccessFile(FILE, "rw");
            raf.seek(pos);
            System.out.println(pos);
            raf.write(address.getBytes());

        } catch (IOException e) {
            System.out.println("Unable to write to file.");
        }
    }
}
