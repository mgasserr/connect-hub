package Backend.Authentication;

import Backend.Account.Account;
import Backend.Databases.Database;
import Backend.Account.Activity;
import java.security.NoSuchAlgorithmException;

public class Log {

    public Account login(String UserName, String Password) {
        String Hashpass = null;
        try {
            Hashpass = PasswordHash.hashPassword(Password); //Hashing the entered password
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error in login method");
        }
        if (Database.loginCheck(UserName, Hashpass)) {
            return Database.getAccount(UserName);
        }
        return null;
    }

    public void logout(Account acc) {
        acc.setStatus(Activity.Status.OFFLINE);
        Database.saveAll();
    }
}
