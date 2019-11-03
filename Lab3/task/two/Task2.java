package task.two;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        int num=0, limit=0;
        Scanner input = new Scanner(System.in);

        System.out.print("Number of banks? ");
        while(num < 1) {
            try {
                num = input.nextInt();
                if (num < 1) {
                    input.nextLine();
                    System.out.print("Must be a positive number: ");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Enter integer for number of banks: ");
            }
        }

        System.out.print("Minimum asset limit: ");
        while(limit < 1) {
            try {
                limit = input.nextInt();
                if (limit < 1) {
                    input.nextLine();
                    System.out.print("Must be a positive number: ");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Enter integer for minimum limit: ");
            }
        }

        Bank[] bank = new Bank[num];
        int counter = 0;

        while (counter < num) {
            try {
                for (int i = 0; i < num; i++) {
                    int balance = 0, loaned = 0;

                    System.out.print("Bank #" + i + "\nBalance: ");
                    balance = input.nextInt();

                    System.out.print("Number of Banks Loaned: ");
                    loaned = input.nextInt();
                    bank[i] = new Bank(i, balance, num);

                    System.out.println("\n**Loan Information**");
                    for (int l = 0; l < loaned; l++) {
                        int loanid = 0;
                        double amount = 0;

                        System.out.print("Loaned to bank #: ");
                        loanid = input.nextInt();
                        System.out.print("Amount loaned: ");
                        amount = input.nextDouble();

                        bank[i].addLoan(loanid, amount);
                    }
                    counter++;
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Retry, you had an error " + e );
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Retry, you had an error " + e);
            }
        }


        /**

        bank[0] = new Bank(0, 25,2 );
        bank[0].addLoan(1,100.5);
        bank[0].addLoan(4,320.5);

        bank[1] = new Bank(1, 125, 2);
        bank[1].addLoan(2,40);
        bank[1].addLoan(3,85);

        bank[2] = new Bank(2,175, 2);
        bank[2].addLoan(0,125);
        bank[2].addLoan(3,75);

        bank[3] = new Bank(3,75, 1);
        bank[3].addLoan(0,125);

        bank[4] = new Bank(4,181, 1);
        bank[4].addLoan(2,125);

         */

        for (int i = 0; i < num; i++) {
            if(!bank[i].isSafe(limit)) {
                for (int b = 0; b < num; b++) {
                    bank[b].minusUnsafe(i);
                }
            }
        }

        for (int i = 0; i<num; i++) {
            if (!bank[i].isSafe(limit)) {
                System.out.println("Bank " + i + " is unsafe.");
            }
        }
    }
}
