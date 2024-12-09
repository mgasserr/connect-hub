package Backend.Authentication;

import Backend.Account.Account;
import Backend.Account.Activity;
import Backend.Account.ProfileManagement;
import static Backend.Authentication.Validations.isValidDate;
import static Backend.Authentication.Validations.isValidEmail;
import Backend.Databases.Databases;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class Register {

    private static Register registerSingleton = null;
    Databases Database = Databases.getInstance();

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
        Database.read();
        Account A = new Account(Email, Username, Hashpass, DOB);
        A.setProfile(new ProfileManagement(A, null, null, null));
        Database.addNewAccount(A);
        Database.save();
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
}
