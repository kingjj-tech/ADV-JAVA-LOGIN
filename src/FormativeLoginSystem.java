import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class FormativeLoginSystem {
    public static ArrayList<UserAccount> userAccountList = new ArrayList<UserAccount>();

    // Method to check if the username contains a pound (#) and does not exceed a specified limit
    public static boolean isUsernameValid(String username) {
        if(!username.contains("#")){
            JOptionPane.showMessageDialog(null, "Username must include a Pound#", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(username.length() >= 8){
            System.out.println(username + " is longer than 8 characters");
            JOptionPane.showMessageDialog(null, username + " is longer than 8 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Method to check if the password meets the complexity requirements
    public static boolean meetPasswordComplexity(String password) {
        System.out.println(password);
        // Password must be at least eight characters long
        if (password.length() < 8) {
            System.out.println("Error in Password | Not 8 characters ");
            JOptionPane.showMessageDialog(null, "Error in Password | Not 8 characters ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Password must contain at least one number
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            System.out.println("Error in Password | No Number");
            JOptionPane.showMessageDialog(null, "Error in Password | No Number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Password at least one capital letter
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            System.out.println("Error in Password | No Capital Letter");
            JOptionPane.showMessageDialog(null, "Error in Password | No Capital Letter", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }



        //  contain at least one special character
        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) {
            System.out.println("Error in Password | Doesn't Contain a special character");
            JOptionPane.showMessageDialog(null, "Error in Password | Doesn't Contain a special character", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    static boolean login(String username, String password){
        for (UserAccount account : userAccountList) {
            if (Objects.equals(account.username, username)) {
                return Objects.equals(account.password, password);
            }
        }
        return false;
    }


    public static boolean regUser(String username, String password, String firstName, String lastName) {
        if(isUsernameValid(username)){
            if(meetPasswordComplexity(password)){
                UserAccount newAccount  = new UserAccount(username, firstName, lastName, password);
                userAccountList.add(newAccount);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Password not Accepted, please\n" +
                    "check that you have met all the\n" +
                    "criteria required", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }



    static UserAccount returnLogin(String username){
        for (UserAccount account : userAccountList) {
            if (Objects.equals(account.username, username)) {
                JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                return account;
            }
        }
        return null;
    }
}