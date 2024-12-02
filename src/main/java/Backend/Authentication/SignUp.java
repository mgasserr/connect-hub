package Backend.Authentication;

import Backend.Account;
import static Backend.Database.Validations.*;
import java.time.LocalDate;
/**
 *
 * @author LEGION
 */

import Backend.Database.SaveAccount;

public class SignUp {

    public boolean SignUp(String userId, String Email, String Username, String Password, LocalDate DOB, String Status) {
        if (!(isValidEmail(Email)) || !(isValidDate(DOB))) {
            return false;
        }
        //username
        Account A = new Account(userId, Email, Username, Password, DOB, Status);
        SaveAccount.saveAccount(A);
        return true;
    }
}
