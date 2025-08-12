import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// GUI class for the Bank Account Manager
public class AccountGUI extends JFrame {
    private AccountManager manager;
    private JTextArea displayArea;
    
    public AccountGUI(){
        //DATA SETUP
        manager = new AccountManager();
        manager.getAccounts().addAll(DataHandler.loadAccounts());

        //WINDOW SETUP
        setTitle("Bank Account Manager");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //MAIN DISPLAY AREA
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // BUTTONS & PANEL
        JButton addButton = new JButton("Add Account");
        JButton saveButton = new JButton("Save Accounts");
        JButton showButton = new JButton("Show All Accounts");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(showButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add a new account when button is clicked
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String name = JOptionPane.showInputDialog("Enter name:");
                String accNumStr = JOptionPane.showInputDialog("Enter account number:");
                String balanceStr = JOptionPane.showInputDialog("Enter balance:");
                if(name != null && accNumStr != null && balanceStr != null){
                    try {
                        int accountNumber = Integer.parseInt(accNumStr);
                        double balance = Double.parseDouble(balanceStr);
                        Account acc = new Account(name, accountNumber, balance);
                        manager.addAccount(acc);
                        JOptionPane.showMessageDialog(null,"Account added!");
                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Invalid number format!");
                    }
                }
            }
        });

        // Save the account when button is clicked
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                DataHandler.saveAccounts(manager.getAccounts());
                JOptionPane.showMessageDialog(null, "Accounts saved!");
            }
        });

        // Show all account when button is clicked
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                displayArea.setText("");
                for(Account acc : manager.getAccounts()){
                    displayArea.append("Name:" + acc.getName() + ", Account:" + acc.getAccountNumber() + ", Balance:" + acc.getBalance() + "\n");
                }
            }
        });
        // Deposit button action
depositButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String accNumStr = JOptionPane.showInputDialog("Enter account number:");
        String amountStr = JOptionPane.showInputDialog("Enter deposit amount:");
        try {
            int accountNumber = Integer.parseInt(accNumStr);
            double amount = Double.parseDouble(amountStr);
            Account acc = manager.findAccount(accountNumber); // You'll need a findAccount method in AccountManager
            if (acc != null) {
                acc.deposit(amount);
                JOptionPane.showMessageDialog(null, "Deposit successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Account not found!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input!");
        }
    }
});

        // Withdraw button action
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accNumStr = JOptionPane.showInputDialog("Enter account number:");
                String amountStr = JOptionPane.showInputDialog("Enter withdraw amount:");
                try {
                    int accountNumber = Integer.parseInt(accNumStr);
                    double amount = Double.parseDouble(amountStr);
                    Account acc = manager.findAccount(accountNumber); // Same here
                    if (acc != null) {
                        if (amount > acc.getBalance()) {
                            JOptionPane.showMessageDialog(null, "Insufficient balance!");
                        } else {
                            acc.withdraw(amount);
                            JOptionPane.showMessageDialog(null, "Withdrawal successful!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Account not found!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });
        
    }
    //Program Entery Point
    public static void main(String[] args) {
        new AccountGUI().setVisible(true);
    }
}