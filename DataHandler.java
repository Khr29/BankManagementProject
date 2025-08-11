import java.io.*;
import java.util.ArrayList;

// Handles saving and loading accounts from a file
public class DataHandler {

    private static final String FILE_NAME = "C:\\xampp\\htdocs\\javafiles\\accounts.txt";

    // save all accounts to the file
    public static void saveAccounts(ArrayList<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account account : accounts) {
                writer.write(account.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read accounts from the file
    public static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int accountNumber = Integer.parseInt(parts[1]);
                    double balance = Double.parseDouble(parts[2]);
                    accounts.add(new Account(name, accountNumber, balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
