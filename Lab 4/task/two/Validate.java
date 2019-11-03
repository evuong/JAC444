package task.two;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {
    private int phone;
    Scanner input = new Scanner(System.in);

    void validatePhone() {
        try {
            phone = input.nextInt();
            String phoneString = String.valueOf(phone);
            if (phoneString.contains("0") || phoneString.contains("1")) {
                System.out.print("Number cannot contain 0 or 1, try again: ");
                phone = 0;
            }
            else if (phoneString.length() != 7) {
                System.out.print("Number must have 7 digits, try again: ");
                phone = 0;
            }
        } catch (InputMismatchException e) {
            input.next();
            System.out.print("Not an integer, try again: ");
        }
    }

    int getNum() {
        System.out.print("Enter in a 7-digit number: ");

        while(phone == 0) {
            validatePhone();
        }
        return phone;
    }
}
