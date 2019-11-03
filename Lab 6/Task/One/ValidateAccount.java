package Task.One;

import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class ValidateAccount {

    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    private static ArrayList<Account> accounts = new ArrayList<>();

    public static void openFile() {
        try {
            output = new ObjectOutputStream(new FileOutputStream("account.dat"));
        } catch (IOException e) {
            System.out.println("Unable to open file");
        }
    }

    public static int validate(TextField acc, TextField pin) {
        readFile();
        boolean found = false;
        boolean valid = false;

        for (Account a : accounts) {
            if(a.getId() == Integer.parseInt(acc.getText())) {
                System.out.println("Match is found.");
                found = true;

                if (a.getPin() == 0) {
                    return 4;
                }
                else if(a.getPin() == Integer.parseInt(pin.getText())) {
                    System.out.println("PIN matched");
                    valid = true;
                }
            }
        }

        if(!found) { return 1; }
        else if(!valid) { return 2; }
        else { return 3; }

    }

    public static void createRecord() {

        for (int i = 0; i < 10; i++) {
            accounts.add(new Account(1000+i, 100));
        }

        writeFile();
    }

    public static void readFile() {
        if (new File("account.dat").exists() && new File("account.dat").length() > 0) {
            try {
                input = new ObjectInputStream(new FileInputStream("account.dat"));
                accounts.clear();
                try {
                    while(true) {
                        accounts.add((Account)input.readObject());
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found");
                } catch (IOException e) {
                    System.out.println("End of file.");
                }
            } catch (IOException e) {
                System.out.println("File not found.");
            }
        }
        else {
            createRecord();
            System.out.println("No previous file found, default records created.");
        }
    }

    public static void setNewPin(int pin, int account) {
        for (Account a : accounts) {
            if (a.getId() == account) {
                a.setPin(pin);
                System.out.println("Account pin changed.");
                writeFile();
            }
        }
    }

    public static void writeFile() {
        openFile();

        for (Account a : accounts) {
            try {
                output.writeObject(a);
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
        }
    }

    public static void withdraw(int account, double wd) {
        for (Account a : accounts) {
            if(a.getId() == account) {
                a.withdraw(wd);
                System.out.println("Amount withdrawn.");
                writeFile();
            }
        }
    }

    public static void deposit(int account, double d) {
        for (Account a : accounts) {
            if(a.getId() == account) {
                a.deposit(d);
                System.out.println("Amount deposited.");
                writeFile();
            }
        }
    }
}
