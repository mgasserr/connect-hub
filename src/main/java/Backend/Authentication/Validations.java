package Backend.Authentication;

import java.time.LocalDate;
import java.time.Period;

public class Validations {

    //CHECK THAT THE NAME DOESN'T HAVE ANY NUMBERS OR SPECIAL CHARACTERS
    public static boolean isValidName(String Name) {
        for (char c : Name.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    //CHECK THAT THE USER IS ABOVE 13 YEARS OLD
    public static boolean isValidDate(LocalDate Date) {
        Period P = Period.between(Date, LocalDate.now());
        return P.getYears() >= 13;
    }

    //CHECK THAT THE PHONE NUMBER DOESN'T HAVE ANY CHARACTERS AND IS 11-DIGITS
    public static boolean isValidPhone(String phoneNumber) {
        return phoneNumber != null && phoneNumber.length() == 11 && phoneNumber.matches("[0-9]+");
    }

    //CHECK THAT THE EMAIL IS IN THE CORRECT FORMAT
    public static boolean isValidEmail(String Email) {
        if (Email == null || !Email.contains("@") || !Email.contains(".")) {
            return false;
        }
        String[] parts = Email.split("@");
        return !(parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty() || !parts[1].contains("."));
    }
}
