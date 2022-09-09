import java.io.*;
import java.util.Scanner;

public class FileHandler {

    static void saveUserToFile(User newUser) {
        String fileName = "contacts.txt";
        try {
            PrintWriter ut = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true))); // append == lägger till i filen, ej overwrite

            ut.println(newUser.getUserName() + " " + newUser.getEmail()); // Nice att println skriver med newline
            ut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void loadUsers() {
        String fileName = "contacts.txt";
        try {
            Scanner in = new Scanner(new File(fileName));

            while (in.hasNextLine()) {
                String[] currentLine = in.nextLine().split(" "); // array som har 2 pos, namn och email
                String userName = currentLine[0];
                String email = currentLine[1];

                User newUser = new User(userName, email); // Laddar in dem Users som fanns sedan innan i filen, skapar objekt av dem.
                Users.addUser(newUser); // Lägger in dem i Users HashMap
            }

        } catch (FileNotFoundException e) {
            System.out.println("Textfilen kommer att läggas till automagiskt");
        }
    }
}
