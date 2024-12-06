package Backend.Databases;

import Backend.Account.Account;
import Backend.Account.Activity;
import Backend.Authentication.PasswordHash;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public abstract class Database {

    protected static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Database> childreninstances = new ArrayList<>();

    protected Database() {
        registerInstance(this);
    }

    private static void registerInstance(Database instance) {
        childreninstances.add(instance);
    }

    public static void readAll() {
        for (Database d : childreninstances) {
            d.read();
        }
    }

    public static void saveAll() {
        for (Database d : childreninstances) {
            d.save();
        }
    }

    public static void refreshDatabase() {
        for (Database d : childreninstances) {
            d.save();
        }
        for (Database d : childreninstances) {
            d.read();
        }
    }

    protected abstract void read();

    protected abstract void save();

    public static ArrayList<Account> getAllAccounts() {
        return accounts;
    }

    public static Account getAccount(String username) {
        for (Account acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }

    public static Account getAccountbyID(String userid) {
        for (Account acc : accounts) {
            if (acc.getUserId().equals(userid)) {
                return acc;
            }
        }
        return null;
    }

    public static void addNewAccount(Account account) {        //gets called when a user signs up
        accounts.add(account);

    }

    //VALIDATION TO CHECK IF THE USERNAME ALREADY USED 
    public static boolean containsUsername(String string) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    //VALIDATION TO CHECK IF THE EMAIL ALREADY USED 
    public static boolean containsEmail(String string) {
        for (Account account : accounts) {
            if (account.getEmail().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    //VALIDATION TO CHECK IF THE ENTERED USERNAME AND PASSWORD ARE CORRECT
    public static boolean loginCheck(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                if (account.getPassword().equals(password)) {
                    account.setStatus(Activity.Status.ONLINE);
                    return true;
                }
            }
        }
        return false;
    }

    public static String changePassword(Account user, String oldpassword, String newpassword, String confirmpassword) {
        String hashpass;
        try {

            hashpass = PasswordHash.hashPassword(oldpassword);
            if (!user.getPassword().equals(hashpass)) {
                return "INVALIDOLDPASS";
            }
            if (!newpassword.equals(confirmpassword)) {
                return "INVALIDCONFIRMPASS";
            }
            String newhashpass = PasswordHash.hashPassword(newpassword);
            user.setPassword(newhashpass);

            System.out.println(newhashpass);
            return "PASSWORDCHANGED";

        } catch (NoSuchAlgorithmException ex) {

        }
        return null;
    }

}
