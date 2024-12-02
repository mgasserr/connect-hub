package Backend.Database;

import Backend.Account;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;


public class SaveAccount {

    public static void saveAccount(Account account) {
            JSONObject js = new JSONObject();
            js.put("userId", account.getUserId());
            js.put("email", account.getEmail());
            js.put("username", account.getUsername());
            js.put("password", account.getPassword());
            js.put("dob", account.getDob());
            js.put("status", account.getDob());
        try {
                try (FileWriter file = new FileWriter("accounts.json")) {
                    file.write(js.toString(3));
                }
        } catch (IOException e) {
            System.out.println("Error in saving user to accounts.json");
        }
    }
}
