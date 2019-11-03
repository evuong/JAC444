package Task.One;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Tester extends Application {

    private DecimalFormat df = new DecimalFormat("#.00");

    private TextField accNum = new TextField();
    private TextField pinNum = new TextField();
    private TextField amtField = new TextField();

    private Button btnSubmit = new Button("Submit");
    private Button btnCheckBal = new Button("Check Balance");
    private Button btnWithdraw = new Button("Withdraw");
    private Button btnWithdrawAmt = new Button("Withdraw");
    private Button btnDeposit = new Button("Deposit");
    private Button btnDepositAmt = new Button("Deposit");
    private Button btnExit = new Button("Exit the Account");
    private Button btnReturn = new Button("Return");

    private Label msg = new Label();
    private Label msg2 = new Label();
    private Label welcomeAcc = new Label();
    private Label accountLabel = new Label();
    private Label balanceLabel = new Label("Current Balance");
    private Label balanceValue = new Label();
    private Label withdrawLabel = new Label("Withdraw Menu");
    private Label depositLabel = new Label("Deposit Menu");
    private Label empty = new Label("");

    private int accountNum;
    private double balance;

    public void start(Stage primaryStage) {

        //  **********           LOGIN SCREEN GUI                ********** //
        VBox login = new VBox(15);
        login.setAlignment(Pos.CENTER);

        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(new Label("Enter an Account Number:"), accNum);

        HBox pane2 = new HBox(10);
        pane2.setAlignment(Pos.CENTER);
        pane2.getChildren().addAll(new Label("Enter PIN:"), pinNum);

        login.getChildren().addAll(pane, pane2, msg2, btnSubmit);
        Scene loginScene = new Scene(login, 400,200);
        primaryStage.setScene(loginScene);


        // **********           MAIN SCREEN GUI             ********** //
        VBox mainmenu = new VBox(10);
        mainmenu.setAlignment(Pos.CENTER);
        mainmenu.getChildren().addAll(welcomeAcc, empty, new Label("What would you like to do?"), btnCheckBal, btnWithdraw, btnDeposit, btnExit);
        Scene mainscene = new Scene(mainmenu, 300,250);

        accNum.setOnKeyPressed(e -> {
           if (e.getCode() == KeyCode.ENTER) {
               btnSubmit.fire();
           }
        });

        pinNum.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                btnSubmit.fire();
            }
        });

        btnSubmit.setOnAction(actionEvent -> {
            msg2.setText("");
            if(!ValidateInput.validateInt(accNum) || !ValidateInput.validateInt(pinNum)) {
                msg2.setText("Enter valid integer number for both fields.");
            }
            else {
                accountNum = Integer.parseInt(accNum.getText());

                switch (ValidateAccount.validate(accNum,pinNum)) {
                    case 1: msg2.setText("Account not found."); break;
                    case 2: msg2.setText("Account found, pin invalid."); break;
                    case 3:
                        setup();
                        welcomeAcc.setText("Welcome Account " + accountNum);
                        primaryStage.setScene(mainscene);
                        break;
                    case 4:
                        int newPin = (int)(Math.random()*8999+1000);
                        msg2.setText("Previously unassigned, PIN is now set to " + newPin);
                        ValidateAccount.setNewPin(newPin, accountNum);
                }
            }
        });

        btnCheckBal.setOnAction(actionEvent -> {

            VBox balanceVBox = new VBox(10);
            balanceVBox.setAlignment(Pos.CENTER);

            balanceVBox.getChildren().addAll(accountLabel, balanceLabel, balanceValue, empty, btnReturn);


            accountLabel.setText("Account Number " + accountNum);

            if (balance == 0) { balanceValue.setText("$0"); }
            else { balanceValue.setText("$" + df.format(balance)); }

            Scene balanceScene = new Scene(balanceVBox, 200,200);
            primaryStage.setScene(balanceScene);
        });


        btnWithdraw.setOnAction(actionEvent -> {

            VBox withdrawVBox = new VBox(10);
            withdrawVBox.setAlignment(Pos.CENTER);

            HBox withdrawHBox = new HBox(10);
            withdrawHBox.setAlignment(Pos.CENTER);
            withdrawHBox.getChildren().addAll(new Label("Amount to withdraw:"), amtField);

            withdrawVBox.getChildren().addAll(accountLabel, withdrawLabel, empty, balanceValue, withdrawHBox, msg, btnWithdrawAmt, btnReturn);

            accountLabel.setText("Account Number " + accountNum);

            displayBalance();

            Scene withdrawScene = new Scene(withdrawVBox, 400,300);
            primaryStage.setScene(withdrawScene);
        });


        btnWithdrawAmt.setOnAction(actionEvent -> {

            if (!ValidateInput.validateDouble(amtField)) {
                msg.setText("Enter valid double number.");
            }
            else {
                msg.setText("");
                double withdrawAmt = Double.parseDouble(amtField.getText());
                System.out.println(withdrawAmt);

                if(withdrawAmt > balance) {
                    msg.setText("Cannot withdraw more than current balance.");
                }
                else if (withdrawAmt == 0) {
                    msg.setText("Cannot withdraw 0.");
                }
                else {
                    balance -= withdrawAmt;
                    ValidateAccount.withdraw(accountNum, withdrawAmt);
                    amtField.setText(df.format(withdrawAmt));
                    msg.setText("$" + df.format(withdrawAmt) + " has been withdrawn.");

                    displayBalance();
                }
            }
        });

        btnDeposit.setOnAction(actionEvent -> {

            VBox depositVBox = new VBox(10);
            depositVBox.setAlignment(Pos.CENTER);

            HBox deposit = new HBox(10);
            deposit.setAlignment(Pos.CENTER);
            deposit.getChildren().addAll(new Label("Amount to deposit: "), amtField);

            depositVBox.getChildren().addAll(accountLabel, depositLabel, empty, balanceValue, deposit, msg, btnDepositAmt, btnReturn);
            
            accountLabel.setText("Account Number " + accountNum);

            displayBalance();

            Scene depositScene = new Scene(depositVBox, 400,300);
            primaryStage.setScene(depositScene);
        });

        btnDepositAmt.setOnAction(actionEvent -> {
            if(!ValidateInput.validateDouble(amtField)) {
                msg.setText("Enter a valid double.");
            }
            else {
                msg.setText("");
                double depositAmt = Double.parseDouble(amtField.getText());

                if (depositAmt == 0) {
                    msg.setText("Cannot deposit 0.");
                }
                else {
                    balance += depositAmt;
                    ValidateAccount.deposit(accountNum, depositAmt);
                    amtField.setText(df.format(depositAmt));
                    msg.setText("$" + df.format(depositAmt) + " has been deposited.");

                    displayBalance();
                }
            }
        });


        btnReturn.setOnAction(actionEvent -> {
            msg.setText("");
            amtField.setText("");
            primaryStage.setScene(mainscene);
        });

        btnExit.setOnAction(actionEvent -> {
            primaryStage.setScene(loginScene);
            pinNum.setText("");

        });

        primaryStage.setTitle("ATM");
        primaryStage.show();
    }

    public static void main(String[] args) { launch (args); }

    private void setup() {
        for(Account a : ValidateAccount.getAccounts()) {
            if (a.getId() == accountNum) {
                balance = a.getBalance();
            }
        }
    }

    private void displayBalance() {
        if (balance == 0) { balanceValue.setText("Current Balance: $0"); }
        else { balanceValue.setText("Current Balance: $" + df.format(balance)); }
    }
}
