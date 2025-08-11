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
        buttJPanel.save(saveButton);


    }
}