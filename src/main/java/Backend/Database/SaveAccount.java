package Backend.Database;

import Backend.Account;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;


public class SaveAccount {

    public static void saveAccount(Account account) {
            JSONObject js = new JSONObject();
            js.put("accountid", account.getUserId());
            js.put("email", account.getEmail());
            js.put("dob", account.getDob());
            js.put("password", account.getPassword());
        
        try {
            FileWriter file = new FileWriter("users.json");
            file.write(js.toString(4));
            file.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
