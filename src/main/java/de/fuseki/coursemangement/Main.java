package de.fuseki.coursemangement;

import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.exchange.StorageJsoner;
import de.fuseki.coursemangement.menus.MainMenu;

public class Main {
        private static final String path = "src/main/resources/storage.json";
    public static void main(String[] args) {
        StorageJsoner storageJsoner = new StorageJsoner();
        Storage storage = storageJsoner.updateStorage(path);
        MainMenu mainMenu = new MainMenu(storage);
        mainMenu.menu();
        int b = 2;
        int c = b;
        storageJsoner.exportStorage(path, storage);
    }
}
