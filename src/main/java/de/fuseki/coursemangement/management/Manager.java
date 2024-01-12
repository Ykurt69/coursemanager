package de.fuseki.coursemangement.management;

import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.ui.CommandLine;

import java.util.Scanner;

public abstract class Manager {
    protected CommandLine commandLine;
    protected Scanner scanner;
    protected Storage storage;

    public Manager(CommandLine commandLine, Scanner scanner, Storage storage) {
        this.commandLine = commandLine;
        this.scanner = scanner;
        this.storage = storage;
    }
}
