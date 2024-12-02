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
import java.security.NoSuchAlgorithmException;

public class SignUp {

    public String SignUp(String userId, String Email, String Username, String Password, LocalDate DOB) throws NoSuchAlgorithmException {
        String Hashpass = PasswordHash.hashPassword(Password);
        if (!(isValidEmail(Email))) {
            return "INVALIDEMAIL";
            //INVALID EMAIL ---> FRONTEND
        }
        if (!(isValidDate(DOB))) {
            return "INVALIDDOB";
            //BELOW 13 YEARS OLD ---> FRONTEND
        }
        LoadedAccounts.readFromFile();
        if (LoadedAccounts.containsEmail(Email)) {
            return "EMAILUSED";
            //ALREADY USED EMAIL ---> FRONTEND
        }
        if (LoadedAccounts.containsUsername(Username)) {
            return "USERUSED";
            //ALREADY USED USERNAME ---> FRONTEND
        }

        Account A = new Account(userId, Email, Username, Hashpass, DOB);
        SaveAccount.saveAccount(A);
        return "SIGNUPDONE";
    }
}
