import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Arrays;

public class ContactsManager {
    static Input input = new Input();
    static String directory = "src";
    static String filename = "contacts.txt";

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("Welcome to the Contacts Manager!");
        menu();
    }

    public static void menu() {

        System.out.println("Please select an option");
        System.out.println("1 - View contacts");
        System.out.println("2 - Add a new contact");
        System.out.println("3 - Search a contact by name");
        System.out.println("4 - Delete an existing contact");
        System.out.println("5 - Exit");
        int selection = input.getInt(1, 5);

        if (selection == 1) {

            try {
                showItems();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (selection == 2) {

            try {
                addNewContact();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (selection == 3) {

            try {
                searchContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("Cool, thanks!");
            System.exit(0);
        }

    }

    public static void showItems() throws IOException {
        // We use Paths.get to get a Java Path object
        Path allContacts = Paths.get("src", "contacts.txt");

        // .readAllLines returns a List type
        List<String> contactsList = Files.readAllLines(allContacts);

        System.out.println("Bearded Eagles Contact List:");

        for (String item : contactsList) {
            System.out.println(item);
        }
        System.out.println();

        menu();
    }

    public static void searchContacts() throws IOException {
        boolean keepGoing=false;
        do {
            Path contactsPath = Paths.get(directory, filename);
            List<String> lines = Files.readAllLines(Paths.get(directory, filename));
            String search = Input.getString("\nWhat contact would you like more information on?\n");
//            System.out.println(lines);
            boolean found = false;
            for (String str : lines) {
                if (str.trim().contains(search)) {
                    System.out.println(str + "\n");
                    found = true;
                }
            }
                if (found == false) {
                    System.out.println("Sorry. There is no contact named " + search + ".\n");
                    keepGoing = Input.yesNo("\nWould you like to search for another contact?");
                }

            if (found == true){
                keepGoing = Input.yesNo("\nWould you like to search for another contact?");
            }
        } while (keepGoing);
        menu();
    }


    public static void addNewContact() throws IOException {

        // open the file and add a new item to the file
        Path contactsPath = Paths.get(directory, filename);

        // if the file does not exist, then create it
        if(Files.notExists(contactsPath)) {
            Files.createFile(contactsPath); // similar to "touch" on the cli
        }

//         assigns lines to hold all of the strings already in the file
        List<String> lines = Files.readAllLines(Paths.get(directory, filename));

        String name = input.getString("What is the new contact's name?");
        String number = input.getString("What is the new contact's number?");

        String newContact = name + " |  " + number;
        lines.add(newContact);


        Files.write(contactsPath, lines);

        menu();
    }
}
