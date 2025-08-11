import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountGUI extends JFrame {
    private AccountManager manager;
    private JTextArea displayArea;

    public AccountGUI(){
        manager = new AccountManager();
        manager.getAccounts().addAll(DataHandler.loadAccounts());
        setTitle("Bank Account Manager");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JButton addButton = new JButton("Add Account");
        JButton saveButton = new JButton("Save Accounts");
        JButton showButton = new JButton("Show All Accounts");

        JPanel buttJPanel = new JPanel();
        buttJPanel.add(addButton);
        buttJPanel.add(saveButton);
        buttJPanel.add(showButton);
        add(buttJPanel, BorderLayout.SOUTH);

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


    }
}