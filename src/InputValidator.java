import java.util.Scanner;

public class InputValidator {
    // Syftet med denna klass är att validera inputs

    public static boolean isValidUserName(String name) {
        return name.length() < 3;
    }

    public static String searchByNameValidator(Scanner scan) {
        while (true) {
            System.out.print("\nAnge username: ");
            String userName = scan.next().trim().toLowerCase();
            if (InputValidator.isValidUserName(userName)) {
                System.out.println("Namnet för kort (Minst 2 bokstäver)");
            } else {
                return userName;
            }
        }
    }

    public static String newUserInputValidator(Scanner scan) {
        while (true) {
            System.out.print("\nAnge username: ");
            String userName = scan.next().trim().toLowerCase();
            if (InputValidator.isValidUserName(userName)) {
                System.out.println("Namnet för kort (Minst 2 bokstäver)");
            } else if (Users.checkIfNameAlreadyExist(userName)){
                System.out.println("Namnet finns redan i databasen");
            } else {
                return userName;
            }
        }
    }

    public static boolean isEmail(String email) {
        if (!email.contains(".")) {
            return false;
        }

        int lastIndexOfDot = email.lastIndexOf(".");
        boolean hasAlpha = email.contains("@");
        String lastBit = email.substring(lastIndexOfDot);

        return hasAlpha && lastBit.equals(".com") || lastBit.equals(".se"); // Har @ och .COM ELLER .SE
    }

}
