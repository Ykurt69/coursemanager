package de.fuseki.coursemangement.menus;

import de.fuseki.coursemangement.enums.CourseChangeEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.management.CourseManager;
import de.fuseki.coursemangement.pojos.Course;

public class ConfigureCoursesMenu extends Menu {
    private final CourseManager COURSEMANAGER;
    private final String thisPathName = "\\update_course";
    private final String thisPath = pathUntilHere + thisPathName;

    public ConfigureCoursesMenu(Storage storage, String pathUntilHere, CourseManager courseManager) {
        super(storage, pathUntilHere);
        this.COURSEMANAGER = courseManager;
    }

    public void menu() {
        {
            // Get course and the path.
            System.out.println(thisPath);
            CourseChangeEnum courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
            Course choosenCourse = storage.searchCourse(COURSEMANAGER.choseCourseIdFromIntegerList(storage.convertCoursesToIds(storage.getAllCourses())));


            while (courseChangeEnum != CourseChangeEnum.COURSE_CHANGE_ENUM_EXIT) {
                switch (courseChangeEnum) {
                    case COURSE_CHANGE_ENUM:
                        System.out.println(thisPath + "\\" + choosenCourse.getTitle());
                        courseChangeEnum = this.selectedOption();
                        break;
                    case COURSE_CHANGE_LIST_STUDENTS:
                        COURSEMANAGER.printStudentsFromCourse(choosenCourse);
                        courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
                        break;
                    case COURSE_CHANGE_TITLE:
                        System.out.println(thisPath + "\\change_title");
                        choosenCourse.setTitle(commandLine.readString("Type the new title: ", scanner, "Old title: " + choosenCourse.getTitle()));
                        courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
                        break;
                    case COURSE_CHANGE_DESCRIPTION:
                        System.out.println(thisPath + "\\change_description");
                        choosenCourse.setDescription(commandLine.readString("Type the new description: ", scanner, "Old description: " + choosenCourse.getDescription()));
                        courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
                        break;
                    case COURSE_CHANGE_LECTURERID:
                        System.out.println(thisPath + "\\change_lecturerid");
                        choosenCourse.setLecturerId(commandLine.readInt("Type new id: ", scanner, "Old id: " + choosenCourse.getLecturerId()));
                        courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
                        break;
                    case COURSE_CHANGE_ADDRESS:
                        System.out.println(thisPath + "\\change_address");
                        choosenCourse.setAddress(commandLine.readAddress(scanner, "Old Address: " + choosenCourse.getAddress()));
                        courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
                        break;
                    case COURSE_CHANGE_DATEBEGIN:
                        System.out.println(thisPath + "\\change_date_begin");
                        choosenCourse.setDateBegin(commandLine.readDate("Type new begin date: ", scanner, "Old begin date: " + choosenCourse.getDateBegin()));
                        courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
                        break;
                    case COURSE_CHANGE_DATEEND:
                        System.out.println(thisPath + "\\change_date_end");
                        choosenCourse.setDateEnd(commandLine.readDate("Type new end date: ", scanner, "Old end date: " + choosenCourse.getDateEnd()));
                        courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
                        break;
                }
            }
        }
    }

    protected CourseChangeEnum selectedOption() {
        String options = "Configure Courses of the Storage.\n" +
                "Type the Number of the following tools.\n" +
                "1. Change title\n" +
                "2. Change description\n" +
                "3. Change lecturer id\n" +
                "4. Change address\n" +
                "5. Change date of begin\n" +
                "6. Change date of end\n" +
                "7. List all students of this course\n" +
                "8. Go Back";

        int input = commandLine.readInt(options, 1, 8);

        CourseChangeEnum courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM;
        switch (input) {
            case 1:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_TITLE;
                break;
            case 2:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_DESCRIPTION;
                break;
            case 3:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_LECTURERID;
                break;
            case 4:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ADDRESS;
                break;
            case 5:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_DATEBEGIN;
                break;
            case 6:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_DATEEND;
                break;
            case 7:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_LIST_STUDENTS;
                break;
            case 8:
                courseChangeEnum = CourseChangeEnum.COURSE_CHANGE_ENUM_EXIT;
                break;
        }
        return courseChangeEnum;
    }
}