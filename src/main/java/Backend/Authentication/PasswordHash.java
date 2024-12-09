package Backend.Authentication;

import Backend.Account.Account;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHash {

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest MD = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = MD.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);

    }

    public static String changePassword(Account user, String oldpassword, String newpassword, String confirmpassword) {
        String hashpass;
        try {

            hashpass = hashPassword(oldpassword);
            if (!user.getPassword().equals(hashpass)) {
                return "INVALIDOLDPASS";
            }
            if (!newpassword.equals(confirmpassword)) {
                return "INVALIDCONFIRMPASS";
            }
            String newhashpass = hashPassword(newpassword);
            user.setPassword(newhashpass);

            System.out.println(newhashpass);
            return "PASSWORDCHANGED";

        } catch (NoSuchAlgorithmException ex) {

        }
        return null;
    }
}
