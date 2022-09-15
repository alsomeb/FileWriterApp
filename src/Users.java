import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Users {
    private static final ArrayList<User> usersList = new ArrayList<>(); // Lista med Users


    public static void addUserToList(User user) {
        usersList.add(user);
    }


    public static void printAllUsers() {
        usersList.forEach((user) -> System.out.println(user.getUserName() + " " + user.getEmail()));
    }

    public static boolean checkIfNoUsers() {
        return usersList.isEmpty();
    }

    public static User searchByName(String name) {
        return usersList.stream()
                .filter(user -> user.getUserName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());

//       for (User user : usersList) {
//           if (user.getUserName().equals(name)) {
//               return user;
//           }
//       }
//       throw new NoSuchElementException();
    }


    public static boolean isInRecord(String name) {
        for (User user : usersList) {
            if (user.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void updateUserMenu(Scanner scan) {
        if (Users.checkIfNoUsers()) {
            System.out.println("No users found. Nothing to update");
        } else {
            showUserAlternatives();
            handleUpdateUser(scan);
        }
    }

    public static void handleUpdateUser(Scanner scan) {
        User currentUser = findUserToUpdate(scan);
        if (currentUser == null) {
            System.out.println("Användaren finns inte");
            return;
        }
        String newEmail = fetchNewEmailFromUser(currentUser, scan);
        update(currentUser, newEmail);
    }

    private static String fetchNewEmailFromUser(User currentUser, Scanner scan) {
        while (true) {
            System.out.print("Ange ny mail för " + currentUser.getUserName() + ": ");
            String newEmail = scan.next().trim().toLowerCase();

            if (!InputValidator.isEmail(newEmail)) {
                System.out.println("Ogiltig email");
                continue;
            }
            return newEmail;
        }
    }

    public static void showUserAlternatives() {
        System.out.println("Dessa finns i registret:");
        Users.printAllUsers();
    }

    public static void update(User currentUser, String newEmail) {
        String oldEmail = currentUser.getEmail();
        currentUser.setEmail(newEmail);
        System.out.println("\nMail ändrad till: " + currentUser.getEmail());
        FileHandler.saveUpdatedUserEmailToFile(oldEmail, newEmail);
    }

    public static User findUserToUpdate(Scanner scan) {
        System.out.println("\nSkriv in användarnamn på användaren du vill uppdatera");
        String userName = scan.next().trim().toLowerCase();
        User currentUser = null;
        if (Users.isInRecord(userName)) {
            currentUser = Users.searchByName(userName);
        }
        return currentUser;
    }


    public static void collectUserInfo(Scanner scan) { // Tar in scanner objektet, reUse
        String userName = getUserName(scan);
        String email = getUserEmail(scan);
        createUser(userName, email);
        System.out.println("Added user: " + userName);
    }

    public static void createUser(String userName, String email) {  // TODO VA I USER?
        User newUser = new User(userName, email); // Skapar objekt User
        Users.addUserToList(newUser); // Lägger in newUser i Listan
        FileHandler.saveUserToFile(newUser); // Sparar User objekt till fil
    }

    public static String getUserEmail(Scanner scan) {
        while (true) {
            System.out.print("\nAnge Email: ");
            String email = scan.next().trim().toLowerCase();
            if (InputValidator.isEmail(email)) {
                return email;
            } else {
                System.out.println("Ej giltig Email.");
            }
        }
    }

    public static String getUserName(Scanner scan) {
        while (true) {
            System.out.print("\nAnge username: ");
            String userName = scan.next().trim().toLowerCase();
            if (InputValidator.isValidUserName(userName)) {
                return userName;
            } else {
                System.out.println("Namnet måste minst va 2 tecken");
            }
        }
    }


    public static void search(Scanner scan) {
        if (Users.checkIfNoUsers()) {
            System.out.println("Kan ej söka för finns inga users");
            return;
        }
        String userName = getUserName(scan); // Re-use

        try {
            User foundUser = Users.searchByName(userName);
            System.out.println("Resultat: " + foundUser.getUserName() + " har email " + foundUser.getEmail());
        } catch (NoSuchElementException e) {
            System.out.println("Fanns ej!");
        }
    }


}
