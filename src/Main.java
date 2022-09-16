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

        // KOLLA SÅ INTE DUBBLETTER PÅ NAMN (I USERS KLASSEN / INPUT VALIDATOR ) - KLAR
        // TODO - DELETE!!


        FileHandler.loadUsers(); // Laddar ner Users från contacts.txt // Skall va utanför loop så den inte laddar om och blir dubletter
        Scanner scan = new Scanner(System.in);


        while (true) {

            System.out.println("\n");
            MenuPrinter.printMenu();
            System.out.print("Val: ");
            String val = scan.next();

            switch (val) {
                case "0" -> System.exit(0); // Avslutar Appen

                case "1" -> Users.collectUserInfo(scan);

                case "2" -> Users.printAllUsers();

                case "3" -> Users.search(scan);

                case "4" -> Users.updateUserMenu(scan);

                default -> System.out.println("Välj rätt");

            }
        }
    }
}