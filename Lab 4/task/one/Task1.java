package task.one;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Task1 {
    public static void main(String[] args) throws IOException {
        String file;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");

        file = input.next();
        input.close();

        try (FileInputStream istream = new FileInputStream(file)) {
            int value;

            int[] upper = new int[26];
            int[] lower = new int[26];

            while ((value = istream.read()) != -1) {
                if(value >= 'A' && value <= 'Z') {
                    upper[value-'A']++;
                }
                if(value >= 'a' && value <= 'z') {
                    lower[value-'a']++;
                }
            }

            for(int i = 0; i < upper.length; i++) {
                System.out.println((char)(i+'A') + ": " + upper[i]);
                System.out.println((char)(i+'a')+ ": " + lower[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
