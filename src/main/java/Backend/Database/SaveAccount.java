package Backend.Database;

import Backend.Account;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;


public class SaveAccount {

    public static void saveAccount(Account account) {
            JSONObject j = new JSONObject();
            j.put("accountid", account.getUserId());
            j.put("email", account.getEmail());
            j.put("dob", account.getDob());
            j.put("password", account.getPassword());
        
        try {
            FileWriter file = new FileWriter("users.json");
            file.write(j.toString(4));
            file.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
