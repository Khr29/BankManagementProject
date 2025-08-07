import java.io.*;
import java.util.ArrayList;

public class DataHandler {

    private static final String FILE_NAME ="accounts.txt";

    public static void saveAccount(ArrayList<Account> accounts){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Account acc : accounts){
                writer.write(acc.toFileString());
                writer.newLine();
            }
            System.out.println("Accounts Saved To File.");
        } catch(IOException e){
            System.out.println("Error saving accounts: "+ e.getMessage());
        }
    }

    public static ArrayList<Account>loadAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        File file = new File(FILE_NAME);
        if(!file.exists());
        return accounts;
    }
    

}