package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.MenuEnum;
import de.fuseki.coursemangement.enums.MenuPersonEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.management.PersonManager;

import static de.fuseki.coursemangement.enums.MenuEnum.MANAGE_STUDENTS;
import static de.fuseki.coursemangement.enums.MenuPersonEnum.*;

public class ManagePersonMenu extends Menu {
    private final MenuEnum MENU_ENUM;
    private final String thisPathName;
    private final String thisPath;
    public ManagePersonMenu(Storage storage,String pathUntilHere, MenuEnum menuEnum) {
        super(storage, pathUntilHere);
        this.MENU_ENUM = menuEnum;
        thisPathName = "\\manage_" + getPersontypeInString();
        thisPath = getThisPath();
    }
    private String getThisPath() {
        return pathUntilHere + thisPathName;
    }

    private String getPersontypeInString() {
        String personType;
        if (MENU_ENUM == MANAGE_STUDENTS){
            personType = "students";
        }
        else personType = "lecturer";
        return  personType;
    }

    public void menu() {
        MenuPersonEnum menuPersonEnum = MENU_PERSON;
        PersonManager personManager = new PersonManager(commandLine, scanner, storage);
        while (menuPersonEnum != EXIT_MENU_PERSON) {
            switch (menuPersonEnum) {
                case MENU_PERSON:
                    System.out.println(thisPath);
                    menuPersonEnum = this.selectedOption();
                    break;
                case CREATE:
                    personManager.createPerson(MENU_ENUM);
                    menuPersonEnum = MENU_PERSON;
                    break;
                case DELETE:
                    personManager.removePerson(MENU_ENUM);
                    menuPersonEnum = MENU_PERSON;
                    break;
                case CHOOSE:
                    ConfigurePersonMenu configurePersonMenu = new ConfigurePersonMenu(storage,thisPath, MENU_ENUM, personManager);
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
                "3. Update " + getPersontypeInString() +"\n" +
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
