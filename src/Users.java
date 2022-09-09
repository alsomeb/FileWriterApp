import java.util.HashMap;

public class Users {
    private static final HashMap<String, String> users = new HashMap<>(); // Kan va final pga man kan ändra i objektet med HashMap metoder men ej OVERWRITE till en ny hashMap



    public static void addUser(User user) {
        String userName = user.getUserName();
        String email = user.getEmail();
        users.put(userName,email);
    }


    public static void printAllUsers() {
        if(users.isEmpty()) {
            System.out.println("Inga kontakter ännu, lägg till!");
            return; // avbryter metoden
        }
        users.forEach( (name,email) -> System.out.println(name + " " + email) );
    }



    public static String searchByName(String name) {
        if(users.containsKey(name)) {
            return users.get(name);
        }

        return "Fanns inte";
    }
}
