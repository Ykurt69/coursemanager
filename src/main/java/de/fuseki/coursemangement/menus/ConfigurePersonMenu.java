package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.MenuEnum;
import de.fuseki.coursemangement.enums.PersonChangeEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.management.PersonManager;
import de.fuseki.coursemangement.pojos.Person;

public class ConfigurePersonMenu extends Menu {
    private final MenuEnum MENU_ENUM;
    private final PersonManager PERSON_MANAGER;
    public ConfigurePersonMenu(Storage storage, MenuEnum menuEnum, PersonManager personManager) {
        super(storage);
        this.MENU_ENUM = menuEnum;
        this.PERSON_MANAGER = personManager;
    }

    public void menu() {

        Person person = PERSON_MANAGER.choosePersonAsPerson(MENU_ENUM);
        PersonChangeEnum personChangeEnum = person == null ? PersonChangeEnum.PERSON_CHANGE_ENUM_EXIT : PersonChangeEnum.PERSON_CHANGE_ENUM;
        while (personChangeEnum != PersonChangeEnum.PERSON_CHANGE_ENUM_EXIT) {
            switch (personChangeEnum) {
                case PERSON_CHANGE_ENUM:
                    personChangeEnum = selectedOption();

                    break;
                case CHANGE_NAME:
                    commandLine.readString("Type new name: ", scanner, "Old name: " + person.getName());
                    personChangeEnum = PersonChangeEnum.PERSON_CHANGE_ENUM;
                    break;
                case CHANGE_SURNAME:
                    commandLine.readString("Type new surname: ", scanner, "Old surname: " + person.getSurname());
                    personChangeEnum = PersonChangeEnum.PERSON_CHANGE_ENUM;
                    break;
                case CHANGE_EMAIL:
                    commandLine.readString("Type new Email: ", scanner, "Old Email: " + person.getEmailAddress());
                    personChangeEnum = PersonChangeEnum.PERSON_CHANGE_ENUM;
                    break;
                case CHANGE_ADDRESS:
                    commandLine.readAddress(scanner, "Old address: " + person.getAddress().toString());
                    personChangeEnum = PersonChangeEnum.PERSON_CHANGE_ENUM;
                    break;
                case CHANGE_BIRTHDATE:
                    commandLine.readDate("Type new Date: ", scanner, "Old Date: " + person.getBirthdate().toString());
                    personChangeEnum = PersonChangeEnum.PERSON_CHANGE_ENUM;
                    break;
                case CONFIGURE_COURSES:
                    ConfigureCoursesOfPersonMenu configureCoursesOfPersonMenu = new ConfigureCoursesOfPersonMenu(storage,person);
                    configureCoursesOfPersonMenu.menu();
                    personChangeEnum = PersonChangeEnum.PERSON_CHANGE_ENUM;
                    break;
            }
        }
    }

    protected PersonChangeEnum selectedOption() {
        String personPossibilities = "Choose one of the selectedOption to change.\n" +
                "The Matriculation Number, the Employee ID and the Course Id can´t be changed.\nIf you want to change them you need to delete the whole person/course and make a new one.\n" +
                "1. Change Name\n" +
                "2. Change Surname\n" +
                "3. Change E-Mail address\n" +
                "4. Change Address\n" +
                "5. Change Birthdate\n" +
                "6. Configure Courses\n" +
                "7. Back";
        int input = commandLine.readInt(personPossibilities, 1, 7);

        PersonChangeEnum chosenAttribute = PersonChangeEnum.PERSON_CHANGE_ENUM;
        switch (input) {
            case 1:
                chosenAttribute = PersonChangeEnum.CHANGE_NAME;
                break;
            case 2:
                chosenAttribute = PersonChangeEnum.CHANGE_SURNAME;
                break;
            case 3:
                chosenAttribute = PersonChangeEnum.CHANGE_EMAIL;
                break;
            case 4:
                chosenAttribute = PersonChangeEnum.CHANGE_ADDRESS;
                break;
            case 5:
                chosenAttribute = PersonChangeEnum.CHANGE_BIRTHDATE;
                break;
            case 6:
                chosenAttribute = PersonChangeEnum.CONFIGURE_COURSES;
                break;
            case 7:
                chosenAttribute = PersonChangeEnum.PERSON_CHANGE_ENUM_EXIT;
                break;
        }
        return chosenAttribute;
    }
}
