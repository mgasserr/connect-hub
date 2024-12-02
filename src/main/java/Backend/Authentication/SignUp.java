package Backend.Authentication;

import Backend.Account;
import Backend.Database.LoadedAccounts;
import static Backend.Database.Validations.*;
import java.time.LocalDate;
/**
 *
 * @author LEGION
 */

import Backend.Database.SaveAccount;

public class SignUp {

    public String SignUp(String userId, String Email, String Username, String Password, LocalDate DOB, String Status) {
        if (!(isValidEmail(Email))) {
            return "INVALID EMAIL";
            //INVALID EMAIL ---> FRONTEND
        }
        if (!(isValidDate(DOB))) {
            return "INVALID DOB";
            //BELOW 13 YEARS OLD ---> FRONTEND
        }
        LoadedAccounts.readFromFile();
        if (LoadedAccounts.containsEmail(Email)) {
            return "EMAIL USED";
            //ALREADY USED EMAIL ---> FRONTEND
        }
        if (LoadedAccounts.containsUsername(Username)) {
            return "USER USED";
            //ALREADY USED USERNAME ---> FRONTEND
        }

        Account A = new Account(userId, Email, Username, Password, DOB);
        SaveAccount.saveAccount(A);
        return "SIGN UP DONE";
    }
}
