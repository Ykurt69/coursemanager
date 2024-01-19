package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.MenuCourseEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.management.CourseManager;

public class ManageCourseMenu extends Menu {
    public ManageCourseMenu(Storage storage) {
        super(storage);
    }

    public void menu() {
        MenuCourseEnum menuCourseEnum = MenuCourseEnum.MENU_COURSE_ENUM;
        CourseManager courseManager = new CourseManager(commandLine, scanner, storage);
        while (menuCourseEnum != MenuCourseEnum.EXIT_MENU_COURSE_ENUM) {
            switch (menuCourseEnum) {
                case MENU_COURSE_ENUM:
                    menuCourseEnum = selectedOption();
                    break;
                case COURSE_CREATE:
                    courseManager.createCourse();
                    menuCourseEnum = MenuCourseEnum.MENU_COURSE_ENUM;
                    break;
                case COURSE_DELETE:
                    courseManager.removeChosenCourseWithIntegerList(storage.convertCoursesToIds(storage.getAllCourses()));
                    menuCourseEnum = MenuCourseEnum.MENU_COURSE_ENUM;
                    break;
                case COURSE_CHOOSE:
                    ConfigureCoursesMenu configureCoursesMenu = new ConfigureCoursesMenu(storage, courseManager);
                    configureCoursesMenu.menu();
                    menuCourseEnum = MenuCourseEnum.MENU_COURSE_ENUM;
                    break;
            }
        }

    }


    protected MenuCourseEnum selectedOption() {
        String options = "Manage Courses\n" +
                "Type the Number of the following tools.\n" +
                "1. Create Course\n" +
                "2. Delete Course\n" +
                "3. Choose Course\n" +
                "4. Go Back";
        int input = commandLine.readInt(options, 1, 4);

        MenuCourseEnum menuCourseEnum = MenuCourseEnum.MENU_COURSE_ENUM;
        switch (input) {
            case 1:
                menuCourseEnum = MenuCourseEnum.COURSE_CREATE;
                break;
            case 2:
                menuCourseEnum = MenuCourseEnum.COURSE_DELETE;
                break;
            case 3:
                menuCourseEnum = MenuCourseEnum.COURSE_CHOOSE;
                break;
            case 4:
                menuCourseEnum = MenuCourseEnum.EXIT_MENU_COURSE_ENUM;
        }
        return menuCourseEnum;
    }
}
