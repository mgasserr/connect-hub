package Backend.Database;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author hp
 */
public class Validations {

    public static boolean isValidName(String Name) {
        for (char c : Name.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDate(LocalDate Date) {
        Period P = Period.between(Date, LocalDate.now());
        return P.getDays() >= (13 * 365);
    }

    public static boolean isValidPhone(String phoneNumber) {
        return phoneNumber != null && phoneNumber.length() == 11 && phoneNumber.matches("[0-9]+");
    }

    public static boolean isValidEmail(String Email) {
        if (Email == null || !Email.contains("@") || !Email.contains(".")) {
            return false;
        }
        String[] parts = Email.split("@");
        return !(parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty() || !parts[1].contains("."));
    }
}
