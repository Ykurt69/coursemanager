package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.ConfigureCoursesEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.management.CourseManager;
import de.fuseki.coursemangement.pojos.Person;

public class ConfigureCoursesOfPersonMenu extends Menu { // TODO Package private?
    private final Person PERSON;
    private final CourseManager COURSE_MANAGER = new CourseManager(commandLine, scanner, storage);

    public ConfigureCoursesOfPersonMenu(Storage storage, Person person) {
        super(storage);
        this.PERSON = person;
    }

    public void menu() {
        ConfigureCoursesEnum configureCoursesEnum = ConfigureCoursesEnum.COURSE_MAIN;
        while (configureCoursesEnum != ConfigureCoursesEnum.EXIT_COURSE) {
            switch (configureCoursesEnum) {
                case COURSE_MAIN:
                    configureCoursesEnum = selectedOption();
                    break;
                case ADD_COURSE:
                    COURSE_MANAGER.addChosenCourse(PERSON, storage.convertCoursesToIds(storage.getAllCourses()));
                    configureCoursesEnum = ConfigureCoursesEnum.COURSE_MAIN;
                    break;
                case DELETE_COURSE:
                    COURSE_MANAGER.removeChosenCourseWithIntegerList(PERSON, PERSON.getCourses());
                    configureCoursesEnum = ConfigureCoursesEnum.COURSE_MAIN;
                    break;
            }
        }
    }

    protected ConfigureCoursesEnum selectedOption() {
        String coursePossibilities = "Choose one of the selectedOption.\n" +
                "1. Add Course\n" +
                "2. Delete Course\n" +
                "3. Go Back";

        int input = this.commandLine.readInt(coursePossibilities, 1, 3);

        ConfigureCoursesEnum chosen = ConfigureCoursesEnum.COURSE_MAIN;
        switch (input) {
            case 1:
                chosen = ConfigureCoursesEnum.ADD_COURSE;
                break;
            case 2:
                chosen = ConfigureCoursesEnum.DELETE_COURSE;
                break;
            case 3:
                chosen = ConfigureCoursesEnum.EXIT_COURSE;
                break;
        }
        return chosen;
    }
}
