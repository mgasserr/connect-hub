package Backend.Authentication;

import Backend.Account.Account;
import Backend.Account.Database;
import static Backend.Account.Database.saveAllAccounts;
import Backend.Account.Activity;
import java.security.NoSuchAlgorithmException;

public class Log {

    public static boolean login(String UserName, String Password) throws NoSuchAlgorithmException {
        String Hashpass = PasswordHash.hashPassword(Password); //Hashing the entered password
        Database.readFromFile();
        return Database.loginCheck(UserName, Hashpass);

    }

    public static void logout(Account acc) {
        acc.setStatus(Activity.Status.OFFLINE);
        saveAllAccounts();
    }
}
