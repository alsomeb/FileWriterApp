import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // En app som användaren skriver in sitt NAMN och GILTIG MAIL (MENY: 1 lägga till, 2 Visa alla Kontakter) - KLAR
        // Giltig mail menas innehåller @ och slutar på .com eller .se - KLAR
        // Du får använda vad du vill inom array/arrayList/HashMap med loopar etc. - KLAR
        // Skriv resultatet till en textfil som heter contacts.txt - Använd PrintWriter, 25/26 i PP Lektion 5 - KLAR
        // Om ej filen finns skall den skapas, MEN SKALL EJ TÖMMAS FÖR VARJE GÅNG, append (parameter) - KLAR
        // format i textfilen är namn mellanslag email - 1 person per rad!! - KLAR

        // Extra Uppgift: Skapa en sök metod som kan läsa i filen efter givet namn -> return email. (som en fake DB) - KLAR
        // Om filen inte finns så catch --> finns ingen historik, spara ner nya kontakter ! - KLAR
        // Använd Scanner (lek 5 sida 22 i PP) - KLAR

        // TODO - KOLLA SÅ INTE DUBBLETTER PÅ NAMN FINNS, SAMT DELTE!

        Scanner scan = new Scanner(System.in);


        while (true) {
            if (Users.checkIfNoUsers()) {
                FileHandler.loadUsers(); // Laddar ner Users från contacts.txt
            }

            System.out.println("\n");
            printMenu();
            System.out.print("Val: ");
            String val = scan.next();

            switch (val) {
                case "0" -> System.exit(0); // Avslutar Appen

                case "1" -> getUserInfo(scan);

                case "2" -> Users.printAllUsers();

                case "3" -> search(scan);

                case "4" -> update(scan);

                default -> System.out.println("Välj rätt");

            }
        }
    }


    // LITE METODER I MAIN
    public static void printMenu() {
        System.out.println("1. Lägg Till\n2. Visa Alla\n3. Sök\n4. Uppdatera mail\n5. Ta bort\n0. Avsluta");
    }

    public static void getUserInfo(Scanner scan) { // Tar in scanner objektet, reUse
        while (true) {
            System.out.print("\nAnge username: ");
            String userName = scan.next().trim().toLowerCase(); // TODO Namnet måste va minst 2 bokstäver kanske?

            scan.nextLine(); // radbrytning

            System.out.print("\nAnge Email: ");
            String email = scan.next().trim().toLowerCase();

            if (!isEmail(email)) {
                System.out.println("Innehåller ej .com / .se");
            } else {
                System.out.println("User " + userName + " added!");
                createUser(userName, email);
                break;
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


    public static void search(Scanner scan) {
        System.out.print("\nSök username: ");
        String userName = scan.next().trim().toLowerCase();

        try {
            User foundUser = Users.searchByName(userName);
            System.out.println("Resultat: " + foundUser.getUserName() + " har email " + foundUser.getEmail());
        } catch (NullPointerException e) {
            System.out.println("Fanns ej!");
        }

    }

    public static void update(Scanner scan) {
        while (true) {
            if(Users.checkIfNoUsers()) {
                break;
            }

            Users.printAllUsers();
            System.out.print("\nDessa finns i registret, vem vill du uppdatera ?: ");
            String userName = scan.next().trim().toLowerCase();

            if (Users.isInRecord(userName)) {
                User currentUser = Users.searchByName(userName);
                String oldEmail = currentUser.getEmail();

                System.out.print("Ange ny mail för " + currentUser.getUserName() + ": ");
                currentUser.setEmail(scan.next().trim().toLowerCase());
                System.out.println("Mail ändrad till: " + currentUser.getEmail());

                String newEmail = currentUser.getEmail();
                FileHandler.saveUpdatedUserEmailToFile(oldEmail, newEmail);
                break;
            } else {
                System.out.println("Finns ej i våra register, stavfel ?\n");
            }
        }
    }


    public static void createUser(String userName, String email) {
        User newUser = new User(userName, email); // Skapar objekt User
        Users.addUserToList(newUser); // Lägger in newUser i Listan
        FileHandler.saveUserToFile(newUser); // Sparar User objekt till fil
    }
}