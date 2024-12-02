/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

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
        if (phoneNumber != null && phoneNumber.length() == 11 && phoneNumber.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String Email) {
        if (Email == null || !Email.contains("@") || !Email.contains(".")) {
            return false;
        }
        String[] parts = Email.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty() || !parts[1].contains(".")) {
            return false;
        }
        return true;
    }
}
