import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    private AccountManager manager;

    private JTextField accNumField;
    private JPasswordField pwField;

    public LoginGUI() {
        // dark simple theme
        UIManager.put("Panel.background", new Color(45,45,45));
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Button.background", new Color(60,60,60));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("TextField.background", new Color(70,70,70));
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("PasswordField.background", new Color(70,70,70));
        UIManager.put("PasswordField.foreground", Color.WHITE);

        manager = new AccountManager();

        // load existing accounts from file:
        for (Account acc : DataHandler.loadAccounts()) {
            manager.addAccount(acc.getAccountNumber(), acc.getName(), acc.getPassword(), acc.getBalance());
        }

        setTitle("Bank Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,2,10,10));

        add(new JLabel("Account Number:"));
        accNumField = new JTextField();
        add(accNumField);

        add(new JLabel("Password:"));
        pwField = new JPasswordField();
        add(pwField);

        JButton loginBtn = new JButton("Login");
        JButton exit = new JButton("Exit");
        add(loginBtn);
        add(exit);

        loginBtn.addActionListener(e -> {
            try {
                int num = Integer.parseInt(accNumField.getText());
                String pass = String.valueOf(pwField.getPassword());
                Account acc = manager.findAccount(num);

                if (acc != null && acc.getPassword().equals(pass)){
                    dispose(); // close login window
                    new UserDashboardGUI(manager, acc);  // open next screen
                } else {
                    JOptionPane.showMessageDialog(this,"Wrong details!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Invalid input.");
            }
        });

        exit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}
