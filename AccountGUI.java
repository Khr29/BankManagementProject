import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// GUI class for the Bank Account Manager
public class AccountGUI extends JFrame {
    private AccountManager manager;
    private JTextArea displayArea;
    private final String MANAGER_PASSWORD = "admin123"; // manager password

    public AccountGUI() {
        // DATA SETUP
        manager = new AccountManager();
        manager.getAccounts().addAll(DataHandlerSQL.loadAccounts());

        // WINDOW SETUP
        setTitle("Bank Account Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // MAIN DISPLAY AREA
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // BUTTONS & PANEL
        JButton addButton = new JButton("Add Account");
        JButton saveButton = new JButton("Save Accounts");
        JButton showButton = new JButton("Show All Accounts");
        JButton depositButton = new JButton("Deposit / Transfer");
        JButton withdrawButton = new JButton("Withdraw");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(showButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add a new account with password
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name:");
            String accNumStr = JOptionPane.showInputDialog("Enter account number:");
            String balanceStr = JOptionPane.showInputDialog("Enter balance:");
            String password = JOptionPane.showInputDialog("Set account password:");
            if (name != null && accNumStr != null && balanceStr != null && password != null) {
                try {
                    int accountNumber = Integer.parseInt(accNumStr);
                    double balance = Double.parseDouble(balanceStr);
                    Account acc = new Account(name, accountNumber, balance, password);
                    manager.addAccount(acc);
                    JOptionPane.showMessageDialog(null, "Account added!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format!");
                }
            }
        });

        // Save accounts
        saveButton.addActionListener(e -> {
            DataHandlerSQL.saveAccounts(manager.getAccounts());
            JOptionPane.showMessageDialog(null, "Accounts saved!");
        });

        // Show all accounts (manager only)
        showButton.addActionListener(e -> {
            String inputPass = JOptionPane.showInputDialog("Enter manager password:");
            if (MANAGER_PASSWORD.equals(inputPass)) {
                manager.getAccounts().clear();
                manager.getAccounts().addAll(DataHandlerSQL.loadAccounts());
                StringBuilder sb = new StringBuilder();
                for (Account acc : manager.getAccounts()) {
                    sb.append("Name: ").append(acc.getName())
                      .append(", Account: ").append(acc.getAccountNumber())
                      .append(", Balance: ").append(acc.getBalance())
                      .append("\n");
                }
                displayArea.setText(sb.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Wrong manager password!");
            }
        });

        // Deposit / Transfer
        depositButton.addActionListener(e -> {
            String fromAccStr = JOptionPane.showInputDialog("Enter your account number:");
            String password = JOptionPane.showInputDialog("Enter your password:");
            String amountStr = JOptionPane.showInputDialog("Enter amount to deposit or transfer:");
            String toAccStr = JOptionPane.showInputDialog("Enter target account number (same as yours if deposit only):");

            try {
                int fromAccNum = Integer.parseInt(fromAccStr);
                int toAccNum = Integer.parseInt(toAccStr);
                double amount = Double.parseDouble(amountStr);

                Account fromAcc = manager.findAccount(fromAccNum);
                Account toAcc = manager.findAccount(toAccNum);

                if (fromAcc != null && toAcc != null) {
                    if (Account.transfer(fromAcc, toAcc, amount, password)) {
                        JOptionPane.showMessageDialog(null, "Transaction successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong password or insufficient funds!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        });

        // Withdraw with password
        withdrawButton.addActionListener(e -> {
            String accNumStr = JOptionPane.showInputDialog("Enter account number:");
            String password = JOptionPane.showInputDialog("Enter your password:");
            String amountStr = JOptionPane.showInputDialog("Enter withdraw amount:");
            try {
                int accountNumber = Integer.parseInt(accNumStr);
                double amount = Double.parseDouble(amountStr);
                Account acc = manager.findAccount(accountNumber);
                if (acc != null) {
                    if (acc.withdraw(amount, password)) {
                        JOptionPane.showMessageDialog(null, "Withdrawal successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong password or insufficient balance!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        });
    }

    // Program Entry Point
    public static void main(String[] args) {
        new AccountGUI().setVisible(true);
    }
}
