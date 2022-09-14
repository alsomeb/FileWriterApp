import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Users {
    private static final ArrayList<User> usersList = new ArrayList<>(); // Lista med Users


    public static void addUserToList(User user) {
        usersList.add(user);
    }


    public static void printAllUsers() {
        if(usersList.isEmpty()) {
            return; // avbryter metoden har en catch som skriver fel med i FileHandler
        }
        usersList.forEach( (user) -> System.out.println(user.getUserName() + " " + user.getEmail()) );
    }

    public static boolean checkIfNoUsers() {
        return usersList.isEmpty();
    }

    public static User searchByName(String name) {
       for (User user : usersList) {
           if (user.getUserName().equals(name)) {
               return user;
           }
       }
       throw new NoSuchElementException();
    }


    public static boolean isInRecord(String name) {
        for (User user : usersList) {
            if(user.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
