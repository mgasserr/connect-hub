package Backend.Database;

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
