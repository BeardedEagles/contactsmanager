import util.Input;
import util.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

public class ContactsManager {
    static Input input = new Input();
    static String directory = "src";
    static String filename = "contacts.txt";
        public static HashMap<String, Contact> contactList;

    public static void main(String[] args) throws IOException {
        Path contactsPath = Paths.get(directory, filename);
        List<String> lines = Files.readAllLines(Paths.get(directory, filename));
        contactList = buildHashMap(lines);
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

        } else if (selection == 3) {

            try {
                searchContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (selection == 4) {

            try {
                removeContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Cool, thanks!");
            System.exit(0);
        }

    }

    public static void showItems() throws IOException {
        for (String key : contactList.keySet()) {
            System.out.print((key));
            System.out.print("  |  ");
            System.out.print(contactList.get(key).getNumber());
            System.out.println();
        }

        menu();
    } // End of showItems()

    public static void searchContacts() throws IOException {
        boolean keepGoing = false;
        do {
            String search = Input.getString("\nWhat contact would you like more information on?\n");
            if (contactList.containsKey(search)) {
                Contact result = contactList.get(search);
                System.out.println("Contact Name: " + search);
                System.out.println("Phone Number: " + result.getNumber());

                keepGoing = Input.yesNo("\nWould you like to search for another contact?");

            } else {
                System.out.println("Sorry. You don't have a contact named " + search + ".\n");
                keepGoing = Input.yesNo("\nWould you like to search for another contact?");
            }
        }while(keepGoing);
        menu();
    }

     // End of searchContacts()

    public static void removeContacts() throws IOException {
        boolean keepGoing=false;
        do {
            Path contactsPath = Paths.get(directory, filename);
            List<String> lines = Files.readAllLines(Paths.get(directory, filename));
            String search = Input.getString("\nWhat contact would you like to remove?\n");
//            System.out.println(lines);
            boolean found = false;
            for (String str : lines) {
                if (str.trim().contains(search)) {
                    found = true;
                    System.out.println(str + "\n");
                    if(Input.yesNo("Are you sure you want to remove " + str)){
                        lines.remove(str);
                        lines = Files.readAllLines(Paths.get(directory, filename));
                        System.out.println("You have removed " + str);
                    }
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
    } // End of removeContacts()




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

//        String newContact = name + " | " + number;
        Contact newContact = new Contact(name, number);
//        lines.add(newContact);


        Files.write(contactsPath, lines);

        menu();
    } // End of addNewContact()

    public static HashMap buildHashMap(List<String> lines){
        String name="";
        String number="";
        HashMap<String, Contact> contactList = new HashMap<>();
            for(String line : lines) {
                System.out.println(line);
               int pipeIndex = line.indexOf("|");
//                System.out.println(pipeIndex);
                name = line.substring(0,pipeIndex-1);
//                System.out.println(name);
                number = line.substring(pipeIndex+2,line.length());
//                System.out.println(number);
                Contact person = new Contact(name, number);
                contactList.put(name, person);
            }
//        System.out.println(contactList);
            return contactList;

    } // End of buildHashMap()
}
