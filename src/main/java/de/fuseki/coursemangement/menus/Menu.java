package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.MenuMarker;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.ui.CommandLine;

import java.util.Scanner;

public abstract class Menu {
    CommandLine commandLine;
    protected Storage storage;
    protected Scanner scanner;
    protected String pathUntilHere;

    public Menu(Storage storage, String pathUntilHere) {
        this.scanner = new Scanner(System.in);
        this.commandLine = new CommandLine(scanner);
        this.storage = storage;
        this.pathUntilHere = pathUntilHere;
    }

    /**
     * Asks what the user wants to do and does it.
     */
    public abstract void menu();

    /**
     * Asks what the user wants to do and returns it in Enum.
     *
     * @return the selected enum.
     */
    protected abstract MenuMarker selectedOption();
}
