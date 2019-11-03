package Task.One;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

    private int id;
    private String fname;
    private String lname;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;
    private int pin;

    public Account() {
        id = 0;
        fname = "";
        lname = "";
        balance = 0;
        annualInterestRate = 0;
        dateCreated = new Date();
        pin = 0;
    }

    public Account(int enteredId, double enteredBal) {
        id = enteredId;
        fname = "";
        lname = "";
        balance = enteredBal;
        annualInterestRate = 0;
        dateCreated = new Date();
        pin = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate/12;
    }

    public double getMonthlyInterest() {
        return balance*getMonthlyInterestRate();
    }

    public void withdraw(double withdrawAmt) {
        balance -= withdrawAmt;
    }

    public void deposit(double depositAmt) {
        balance += depositAmt;
    }
}
