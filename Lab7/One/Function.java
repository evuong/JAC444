package One;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Function {
    private static String line;
    private static Scanner scanner;
    private static int number;
    private static String regex;

    public static int findName(int year, char gender, String name) {
        openFile(year);

        if(gender == 'M') {
            regex = "[0-9]+\\s*" + name + "[^a-z].*";
        }
        else {
            regex = "^[0-9]+\\s*[A-Za-z]+.*" + name + "[^a-z].*";
        }

        number = 0;
        while (scanner.hasNextLine()) {
            number++;
            line = scanner.nextLine();
            if(line.matches(regex)) {
                System.out.println(line);
                return number;
            }
        }
        return 0;
    }

    public static void openFile(int year) {
        try {
            scanner = new Scanner(new File("babynamesranking2001to2010/babynamesranking" + year + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
