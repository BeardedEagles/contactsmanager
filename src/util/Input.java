package util;
import java.util.Scanner;

public class Input {
    Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public static String Input() {
        String userInput = getString();
        return userInput;
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String input = scanner.next();
        return input;
    }

    public static String getString(String prompt) {
        System.out.println(prompt);
        return getString();
    }

    public boolean yesNo() {
        String input = getString();
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
    }

    public boolean yesNo(String prompt) {
        System.out.println(prompt);
        return yesNo();
    }

    public int getInt(int min, int max) {
        String userInput = scanner.next();
        int num = 0;
        try{
            num = Integer.valueOf(userInput);
        } catch (NumberFormatException ex){
            System.out.println(ex);
        }
        if (num >= min && num <= max) {
            return num;
        } else {
            System.out.println("The number must be between" + min + " and " + max);
            return getInt(min, max);
        }
    }

    public int getInt() {
        System.out.println("Enter a number: ");
        String input;

        try{
            input = scanner.next();
            return Integer.valueOf(input);
        } catch (NumberFormatException ex){
            System.out.println("Enter a valid integer!");
            return getInt();
        }
    }

    public int getInt(String prompt) {
        System.out.println(prompt);
        return getInt();
    }

    public double getDouble(double min, double max) {
        double userDouble = getDouble();
        if (userDouble >= min && userDouble <= max) {
            return userDouble;
        } else {
            System.out.println("The number must be between " + min + " and " + max);
            return getDouble(min, max);
        }
    }

    public double getDouble() {
        String input;
        try{
            input = scanner.next();
            return Double.valueOf(input);
        } catch (NumberFormatException ex){
            System.out.println("Enter a valid integer!");
            return getDouble();
        }
    }

    public double getDouble(String prompt) {
        System.out.println(prompt);
        return getDouble();
    }

    public double getDouble(double min, double max, String prompt) {
        System.out.println(prompt);
        return getDouble(min, max);
    }
}

