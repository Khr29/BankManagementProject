// DataHandlerSQL.java
import java.sql.*;
import java.util.ArrayList;

public class DataHandlerSQL {

    private static final String URL = "jdbc:mysql://localhost:3306/bankdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Load all accounts from the database
    public static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, account_number, balance, password FROM accounts")) {

            while (rs.next()) {
                String name = rs.getString("name");
                int accountNumber = rs.getInt("account_number");
                double balance = rs.getDouble("balance");
                String password = rs.getString("password"); // new column
                accounts.add(new Account(name, accountNumber, balance, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Save all accounts to the database (replace old data)
    public static void saveAccounts(ArrayList<Account> accounts) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Clear table before inserting
            stmt.executeUpdate("DELETE FROM accounts");

            // Insert new records with password
            String sql = "INSERT INTO accounts (account_number, name, balance, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (Account account : accounts) {
                    pstmt.setInt(1, account.getAccountNumber());
                    pstmt.setString(2, account.getName());
                    pstmt.setDouble(3, account.getBalance());
                    pstmt.setString(4, account.getPassword()); // new field
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
