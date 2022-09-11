import java.io.*;
import java.util.Scanner;

public class FileHandler {
    static String fileName = "contacts.txt";

    public static void saveUserToFile(User newUser) {
        try {
            PrintWriter ut = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true))); // append == lägger till i filen, ej overwrite

            ut.println(newUser.getUserName() + " " + newUser.getEmail()); // Nice att println skriver med newline
            ut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveUpdatedUserEmailToFile(String oldEmail, String newEmail){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder oldContentOfFile = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {

                oldContentOfFile.append(line).append("--"); // måste ha ett tecken till som jag kan split på då det är space normalt i filen för writeNewContent() metod
                line = reader.readLine(); // läs nästa
            }

            String newContent = oldContentOfFile.toString().replaceAll(oldEmail, newEmail);
            writeNewContent(newContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void writeNewContent(String content) {
        try {
            PrintWriter ut = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            String[] arr = content.split("--");
            for(String line : arr) {
                ut.println(line);
            }
            ut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void loadUsers() {
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
            System.out.println("No record found, add contacts");
        }
    }
}
