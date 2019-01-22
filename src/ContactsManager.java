import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

        if(selection == 1) {

            try {
                showItems();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(selection == 2) {

            try {
                addNewContact();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
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

        for(String item : contactsList) {
            System.out.println(item);
        }
        System.out.println();

        menu();

    }

    public static void addNewContact() throws IOException {

        // open the file and add a new item to the file
        Path addContact = Paths.get(directory, filename);

        // if the file does not exist, then create it
        if(Files.notExists(addContact)) {
            Files.createFile(addContact); // similar to "touch" on the cli
        }

//         assigns lines to hold all of the strings already in the file
        List<String> lines = Files.readAllLines(Paths.get(directory, filename));
//
        String name = (String) Input.getString("Please input the name:");
        String number = (String) Input.getString("Please input the phone number: ");

        lines.add(name, number);

        Files.write(, lines);

        menu();
    }
}
