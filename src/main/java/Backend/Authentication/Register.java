package Backend.Authentication;

import Backend.Account.Account;
import Backend.Databases.Database;
import Backend.Account.Activity;
import static Backend.Authentication.Validations.isValidDate;
import static Backend.Authentication.Validations.isValidEmail;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class Register {

    private static Register registerSingleton = null;

    private Register() {
    }

    public static Register getInstance() {
        if (registerSingleton == null) {
            registerSingleton = new Register();
        }
        return registerSingleton;
    }

    public String signUp(String Email, String Username, String Password, LocalDate DOB) throws NoSuchAlgorithmException {
        String Hashpass = PasswordHash.hashPassword(Password);
        if (!(isValidEmail(Email))) {
            return "INVALIDEMAIL";
            //INVALID EMAIL ---> FRONTEND
        }
        if (!(isValidDate(DOB))) {
            return "INVALIDDOB";
            //BELOW 13 YEARS OLD ---> FRONTEND
        }
        if (Database.containsEmail(Email)) {
            return "EMAILUSED";
            //ALREADY USED EMAIL ---> FRONTEND
        }
        if (Database.containsUsername(Username)) {
            return "USERUSED";
            //ALREADY USED USERNAME ---> FRONTEND
        }

        Account A = new Account(Email, Username, Hashpass, DOB);
        Database.addNewAccount(A);
        return "SIGNUPDONE";
    }

    public Account signIn(String UserName, String Password) {
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
