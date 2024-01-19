package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.MenuEnum;
import de.fuseki.coursemangement.exchange.Storage;

public class MainMenu extends Menu {
    private final String thisPathName = "\\main";
    private final String thisPath = pathUntilHere + thisPathName;
    public MainMenu(Storage storage, String pathUntilHere) {
        super(storage, pathUntilHere);

    }

    public void menu() {
        MenuEnum menuEnum = MenuEnum.MAIN_MENU;
        while (menuEnum != MenuEnum.EXIT_MAIN_MENU) {
            switch (menuEnum) {
                case MAIN_MENU:
                    System.out.println(thisPath);
                    menuEnum = this.selectedOption();
                    break;
                case MANAGE_STUDENTS:
                case MANAGE_LECTURERS:
                    ManagePersonMenu managePersonMenu = new ManagePersonMenu(storage,thisPath, menuEnum);
                    managePersonMenu.menu();
                    menuEnum = MenuEnum.MAIN_MENU;
                    break;
                case MANAGE_COURSES:
                    ManageCourseMenu manageCourseMenu = new ManageCourseMenu(storage,thisPath);
                    manageCourseMenu.menu();
                    menuEnum = MenuEnum.MAIN_MENU;
                    break;
            }
        }
    }

    protected MenuEnum selectedOption() {
        String thisPath = pathUntilHere + "\\main";
        String options = "Main Menu\n" +
                "Type the Number of the following tools.\n" +
                "1. Manage Students\n" +
                "2. Manage Lecturers\n" +
                "3. Manage Courses\n" +
                "4. Save an Quit";

        int input = commandLine.readInt(options, 1, 4);
        MenuEnum menuEnum = MenuEnum.MAIN_MENU;
        switch (input) {
            case 1:
                menuEnum = MenuEnum.MANAGE_STUDENTS;
                break;
            case 2:
                menuEnum = MenuEnum.MANAGE_LECTURERS;
                break;
            case 3:
                menuEnum = MenuEnum.MANAGE_COURSES;
                break;
            case 4:
                menuEnum = MenuEnum.EXIT_MAIN_MENU;
                break;
        }
        return menuEnum;
    }
}
