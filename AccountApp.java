import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AccountApp {
    private JFrame frame;
    private JTextField accNumberField, nameField, passField, balanceField, amountField, verifyPassField;
    private JTextArea outputArea;
    private AccountManager manager = new AccountManager();

    public AccountApp() {
        // Load accounts from file
        ArrayList<Account> loadedAccounts = DataHandler.loadAccounts();
        for (Account acc : loadedAccounts) {
            manager.addAccount(acc.getAccountNumber(), acc.getName(), acc.getPassword(), acc.getBalance());
        }

        frame = new JFrame("Bank Account Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new GridLayout(0, 1));

        accNumberField = new JTextField();
        nameField = new JTextField();
        passField = new JTextField();
        balanceField = new JTextField();
        amountField = new JTextField();
        verifyPassField = new JTextField();
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        JButton createBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton showAllBtn = new JButton("Show All Accounts");
        JButton saveBtn = new JButton("Save All To File");

        frame.add(new JLabel("Account Number:"));
        frame.add(accNumberField);
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Password:"));
        frame.add(passField);
        frame.add(new JLabel("Initial Balance:"));
        frame.add(balanceField);
        frame.add(createBtn);

        frame.add(new JLabel("Deposit/Withdraw Amount:"));
        frame.add(amountField);
        frame.add(new JLabel("Enter Password to Confirm:"));
        frame.add(verifyPassField);

        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(showAllBtn);
        frame.add(saveBtn);
        frame.add(new JScrollPane(outputArea));

        createBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accNumberField.getText());
                String name = nameField.getText();
                String pass = passField.getText();
                double balance = Double.parseDouble(balanceField.getText());

                if (manager.findAccount(accNo) != null) {
                    outputArea.setText("Account number already exists.");
                    return;
                }

                manager.addAccount(accNo, name, pass, balance);
                outputArea.setText("✅ Account created successfully for " + name);
            } catch (NumberFormatException ex) {
                outputArea.setText("⚠ Invalid input. Please enter correct values.");
            }
        });

        depositBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accNumberField.getText());
                double amount = Double.parseDouble(amountField.getText());
                String pass = verifyPassField.getText();

                Account acc = manager.findAccount(accNo);
                if (acc == null) {
                    outputArea.setText("⚠ Account not found.");
                } else if (!acc.getPassword().equals(pass)) {
                    outputArea.setText("❌ Incorrect password. Deposit denied.");
                } else {
                    manager.depositToAccount(accNo, amount);
                    outputArea.setText("✅ Deposit successful.");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("⚠ Invalid input.");
            }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                int accNo = Integer.parseInt(accNumberField.getText());
                double amount = Double.parseDouble(amountField.getText());
                String pass = verifyPassField.getText();

                Account acc = manager.findAccount(accNo);
                if (acc == null) {
                    outputArea.setText("⚠ Account not found.");
                } else if (!acc.getPassword().equals(pass)) {
                    outputArea.setText("❌ Incorrect password. Withdrawal denied.");
                } else {
                    boolean success = manager.withdrawFromAccount(accNo, amount);
                    outputArea.setText(success ? "✅ Withdrawal successful." : "❌ Insufficient balance.");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("⚠ Invalid input.");
            }
        });

        showAllBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Account acc : manager.getAllAccounts()) {
                sb.append("Account #: ").append(acc.getAccountNumber()).append("\n");
                sb.append("Name: ").append(acc.getName()).append("\n");
                sb.append("Balance: $").append(acc.getBalance()).append("\n");
                sb.append("--------------------------\n");
            }
            outputArea.setText(sb.toString());
        });

        saveBtn.addActionListener(e -> {
            DataHandler.saveAccount(manager.getAllAccounts());
            outputArea.setText("💾 All accounts saved to file.");
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AccountApp();
    }
}
