package task.two;

public class Bank {
    private int id;
    double balance;
    private double[][] loan;

    private int count = 0;

    public Bank(int b_id, double b_balance, int num_loaned) {
        id = b_id;
        balance = b_balance;
        loan = new double[num_loaned][2];
    }

    public void addLoan(int loanid, double amount) {
        loan[count][0] = loanid;
        loan[count][1] = amount;
        balance += amount;
        count++;
    }

    public boolean isSafe(int limit) {
        if (balance > limit) { return true; }
        else return false;
    }

    public void minusUnsafe(int loanid) {
        for(int i = 0; i < loan.length; i++) {
            if (loan[i][0] == loanid) {
                balance -= loan[i][1];
            }
        }
    }
}
