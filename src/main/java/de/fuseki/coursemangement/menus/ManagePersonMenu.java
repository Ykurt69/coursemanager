package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.MenuEnum;
import de.fuseki.coursemangement.enums.MenuPersonEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.management.PersonManager;

import static de.fuseki.coursemangement.enums.MenuPersonEnum.*;

public class ManagePersonMenu extends Menu {
    private final MenuEnum MENUENUM;

    public ManagePersonMenu(Storage storage, MenuEnum menuEnum) {
        super(storage);
        this.MENUENUM = menuEnum;
    }

    public void menu() {
        MenuPersonEnum menuPersonEnum = MENU_PERSON;
        PersonManager personManager = new PersonManager(commandLine, scanner, storage);
        while (menuPersonEnum != EXIT_MENU_PERSON) {
            switch (menuPersonEnum) {
                case MENU_PERSON:
                    menuPersonEnum = this.selectedOption();
                    break;
                case CREATE:
                    personManager.createPerson(MENUENUM);
                    menuPersonEnum = MENU_PERSON;
                    break;
                case DELETE:
                    personManager.removePerson(MENUENUM);
                    menuPersonEnum = MENU_PERSON;
                    break;
                case CHOOSE:
                    ConfigurePersonMenu configurePersonMenu = new ConfigurePersonMenu(storage, MENUENUM, personManager);
                    configurePersonMenu.menu();
                    menuPersonEnum = MENU_PERSON;
                    break;
            }
        }
    }

    protected MenuPersonEnum selectedOption() {
        String options = "Choose one option and the the number.\n" +
                "1. Create\n" +
                "2. Delete\n" +
                "3. Choose one to Adjust values\n" +
                "4. Go Back";
        int input = commandLine.readInt(options, 1, 4);

        MenuPersonEnum chosen = MENU_PERSON;
        switch (input) {
            case 1:
                chosen = MenuPersonEnum.CREATE;
                break;
            case 2:
                chosen = MenuPersonEnum.DELETE;
                break;
            case 3:
                chosen = MenuPersonEnum.CHOOSE;
                break;
            case 4:
                chosen = MenuPersonEnum.EXIT_MENU_PERSON;
        }
        return chosen;
    }
}
