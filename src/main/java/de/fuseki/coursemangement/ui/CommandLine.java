package de.fuseki.coursemangement.ui;

import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Lecturer;


import java.time.LocalDate;
import java.util.Scanner;

public class CommandLine {
    Scanner scanner;


    public CommandLine(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prints a description of the inout and returns it.
     *
     * @param message the descriptions of the input.
     * @param scanner to read the console.
     * @return the id in int.
     */
    public int readInt(String message, Scanner scanner) {
        String input;
        do {
            System.out.println(message);

            input = scanner.next();
            if (!input.matches("\\d+")) {
                System.err.println("Error: pls just use digits!");
            }
        } while (!input.matches("\\d+"));
        return Integer.parseInt(input);
    }

    /**
     * Prints a description of the inout and returns it.
     * If min and max are set, it will ask till the user enters a number between and inclusive min and max.
     *
     * @param message the descriptions of the input.
     * @param min     the min value of the read int.
     * @param max     the max value of the read int
     * @return the id in int.
     */
    public int readInt(String message, int min, int max) {
        String input;
        int parsedInput = 0;
        do {
            System.out.println(message);

            input = scanner.next();
            if (!input.matches("\\d+")) {
                System.err.println("Error: pls just use digits!");
            } else {
                parsedInput = Integer.parseInt(input);
                if (parsedInput < min || parsedInput > max) {
                    System.err.println("Type a number between " + min + " and " + max);

                }
            }
        } while (!input.matches("\\d+") || parsedInput < min || parsedInput > max);
        return parsedInput;
    }

    /**
     * Prints a description of the inout and returns it.
     *
     * @param message the descriptions of the input.
     * @param scanner to read the console.
     * @return the id in int.
     */
    public int readInt(String message, Scanner scanner, String oldValue) {
        String input;
        do {
            System.out.println(oldValue + "\n" + message);

            input = scanner.next();
            if (!input.matches("\\d+")) {
                System.err.println("Error: pls just use digits!");
            }
        } while (!input.matches("\\d+"));
        return Integer.parseInt(input);
    }


    /**
     * Gets Input and searches in the storage if there is a Lecturer with the same id.
     *
     * @param sc      Scanner to read the console
     * @param storage in which the method is searching
     * @return the Lecturer found.
     */
    public Lecturer findLecturer(Scanner sc, Storage storage) {
        Lecturer searched;
        do {
            int input = readInt("ID of the Lecturer; ", sc);
            searched = storage.searchLecturer(input);
            if (searched == null) {
                System.err.println("Cant find the Lecturer!");
            }
        } while (searched == null);
        return searched;
    }


    /**
     * Asks for the Date in the console and parses it into a LocalDate.
     *
     * @param scanner     to read the console.
     * @param description description of the Date.
     * @return the birthdate in LocalDate.
     */
    public LocalDate readDate(String description, Scanner scanner, String oldValue) {
        String[] strings;
        boolean rightInput = false;
        do {
            System.out.println(oldValue + "\n" + description + "([YYYY]-[MM]-[DD]: ");
            strings = scanner.next().split("-");
            if (validDateCheck(strings)) {
                rightInput = true;
            } else System.err.println("Wrong input pls retry!");
        } while (!rightInput);
        return LocalDate.of(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
    }

    /**
     * Asks for the Date in the console and parses it into a LocalDate.
     *
     * @param scanner     to read the console.
     * @param description description of the Date.
     * @return the birthdate in LocalDate.
     */
    public LocalDate readDate(String description, Scanner scanner) {
        String[] strings;

        boolean rightInput = false;
        do {
            System.out.println(description + "([YYYY]-[MM]-[DD]: ");
            strings = scanner.next().split("-");
            if (validDateCheck(strings)) {
                rightInput = true;
            } else System.err.println("Wrong input pls retry!");
        } while (!rightInput);
        return LocalDate.of(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
    }

    /**
     * Checks if the input is compatible.
     *
     * @param strings Array of the splitter input.
     * @return true if compatible.
     */
    private boolean validDateCheck(String[] strings) {
        return strings.length == 3 && Integer.parseInt(strings[1]) > 0 && Integer.parseInt(strings[1]) < 13 && Integer.parseInt(strings[2]) > 0 && Integer.parseInt(strings[2]) < 32;
    }

    /**
     * Makes an Address object out of console input.
     *
     * @param scanner to read the console.
     * @return the Address in Address.
     */
    public Address readAddress(Scanner scanner) {
        String street = readString("Street: ", scanner);
        String houseNumber = readString("House Number: ", scanner);
        String city = readString("City: ", scanner);
        String postalCode = readString("postal code: ", scanner);
        return new Address(street, houseNumber, city, postalCode);
    }

    /**
     * Makes an Address object out of console input.
     *
     * @param scanner to read the console.
     * @return the Address in Address.
     */
    public Address readAddress(Scanner scanner, String oldAddress) {
        System.out.println(oldAddress);
        String street = readString("Street: ", scanner);
        String houseNumber = readString("House Number: ", scanner);
        String city = readString("City: ", scanner);
        String postalCode = readString("postal code: ", scanner);
        return new Address(street, houseNumber, city, postalCode);
    }


    /**
     * Asks in the console for an input and returns it without formatting or parsing it.
     *
     * @param description of the input wanted.
     * @param scanner     to read out of console.
     * @return the input in String.
     */
    public String readString(String description, Scanner scanner) {
        System.out.println(description);
        return scanner.next();
    }

    /**
     * Asks in the console for an input and returns it without formatting or parsing it.
     *
     * @param description of the input wanted.
     * @param scanner     to read out of console.
     * @return the input in String.
     */
    public String readString(String description, Scanner scanner, String oldValue) {
        System.out.println(oldValue + "\n" + description);
        return scanner.next();
    }

}
