import javax.swing.*;
import java.awt.*;

public class UserDashboardGUI extends JFrame {
    private AccountManager manager;
    private Account userAcc;
    private JLabel balLabel;

    public UserDashboardGUI(AccountManager m, Account current) {
        this.manager = m;
        this.userAcc = current;

        setTitle("Welcome "+userAcc.getName());
        setSize(400,300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,1,5,5));

        JLabel welcome = new JLabel("Account: "+userAcc.getAccountNumber(), JLabel.CENTER);
        welcome.setForeground(Color.WHITE);
        add(welcome);

        balLabel = new JLabel("Balance: "+userAcc.getBalance(), JLabel.CENTER);
        balLabel.setForeground(Color.WHITE);
        add(balLabel);

        JButton dep = new JButton("Deposit");
        JButton with = new JButton("Withdraw");
        JButton logout = new JButton("Logout");
        JButton exit = new JButton("Save & Exit");

        add(dep);
        add(with);
        add(logout);
        add(exit);

        dep.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(this,"Amount to deposit:");
            try{
                double amt = Double.parseDouble(s);
                userAcc.deposit(amt);
                refresh();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,"Invalid Amount");
            }
        });

        with.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(this,"Amount to withdraw:");
            try{
                double amt = Double.parseDouble(s);
                if (userAcc.withdraw(amt)){
                    refresh();
                }else{
                    JOptionPane.showMessageDialog(this,"Insufficient Balance");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,"Invalid Amount");
            }
        });

        logout.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        exit.addActionListener(e -> {
            DataHandler.saveAccounts(manager.getAllAccounts());
            System.exit(0);
        });

        // dark styling
        getContentPane().setBackground(new Color(45,45,45));
        setForeground(Color.WHITE);
        setVisible(true);
    }

    private void refresh(){
        balLabel.setText("Balance: "+userAcc.getBalance());
    }
}
