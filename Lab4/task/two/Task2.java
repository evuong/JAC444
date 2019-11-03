package task.two;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class Task2 {
    public static void main(String[] args) {


        Validate v = new Validate();
        String phone = String.valueOf(v.getNum());

        //String phone = "45";
        char converter [][] = {{'A','B','C'}, {'D','E','F'}, {'G','H','I'}, {'J','K','L'}, {'M','N','O'},
                {'P','R','S'}, {'T','U','V'}, {'W','X','Y'}};

        StringBuilder str = new StringBuilder();

        convert(phone, 0, converter, str);
    }

    static void convert(String phone, int i, char[][] map, StringBuilder str) {

        if (i == phone.length()) {
            try (FileOutputStream output = new FileOutputStream(new File("results.txt"),true)) {
                String result = str + "\n";
                byte[] b = result.getBytes();
                output.write(b);
            } catch (Exception e) {
                System.out.println("Error.");
            }
            return;
        }

        char[] s = map[Character.getNumericValue(phone.charAt(i))-2];
        for (int j = 0; j < s.length; j++) {
            str.append(s[j]);
            convert(phone, i+1, map, str);
            str.deleteCharAt(str.length()-1);
        }
    }
}
