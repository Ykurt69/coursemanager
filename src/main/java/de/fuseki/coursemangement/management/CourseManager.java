package de.fuseki.coursemangement.management;

import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.pojos.*;
import de.fuseki.coursemangement.ui.CommandLine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseManager extends Manager {

    public CourseManager(CommandLine commandLine, Scanner sc, Storage storage) {
        super(commandLine, sc, storage);
    }

    /**
     * Instances a Course out of the console and adds it in to the Storage.
     */
    public void createCourse() {
        int id = commandLine.readInt("ID: ", scanner);
        String title = commandLine.readString("title: ", scanner);
        String description = commandLine.readString("description: ", scanner);
        Lecturer lecturer = commandLine.findLecturer(scanner, storage);
        Address address = commandLine.readAddress(scanner);
        LocalDate dateBegin = commandLine.readDate("Begin date", scanner);
        LocalDate dateEnd = commandLine.readDate("End date", scanner);

        storage.add(new Course(id, title, description, lecturer.getEmployeeId(), address, dateBegin, dateEnd));
    }

    /**
     * Lists the Courses from the integerList and adds the chosen one into the Storage.
     *
     * @param person      person which the course should be added.
     * @param integerList of Course, Ids.
     */
    public void addChosenCourse(Person person, List<Integer> integerList) {
        int courseId = choseCourseIdFromIntegerList(integerList);
        if (courseId != -1) {
            person.addCourse(courseId);
        }
    }

    /**
     * lists all students of the course.
     *
     * @param course which do have the students.
     * @return the List of Student in the Course.
     */
    public List<Student> getListStudentsFromCourse(Course course) {
        int courseId = course.getId();
        List<Student> studentsInCourse = new ArrayList<>();
        for (Student student : storage.getStudents()) {
            for (int courseIdOfStudent : student.getCourses()) {
                if (courseId == courseIdOfStudent) {
                    studentsInCourse.add(student);
                }
            }
        }
        return studentsInCourse;
    }


    /**
     * Prints all Students in the Course.
     *
     * @param course which student have to be printed.
     */
    public void printStudentsFromCourse(Course course) {
        List<Student> studentsFromCourse = getListStudentsFromCourse(course);
        for (Student student : studentsFromCourse) {
            System.out.println("\nStudents in the Course: ");
            System.out.println(student.toString() + "\n");
        }
    }

    /**
     * Lists all courses of the person into the console with the number they have.
     * The List which has been printed.
     */
    public void listCoursesFromIds(List<Integer> integerList) {
        List<Course> courseList = storage.convertIdsToCourses(integerList);
        if (!courseList.isEmpty()) {
            for (int i = 0; i < courseList.size(); i++) {
                System.out.println((i + 1) + ". " + courseList.get(i).toString());
            }
        }
    }

    /**
     * Lists all courses and waits for the user to choose one.
     *
     * @return the id of the course. -1 if there is no Person.
     */
    public int choseCourseIdFromIntegerList(List<Integer> integerList) {
        List<Course> courseList = storage.convertIdsToCourses(integerList);
        listCoursesFromIds(integerList);
        int input;
        int idOfTheCourse = -1;
        if (courseList != null && !courseList.isEmpty()) {
            input = commandLine.readInt("Choose one Course.", 1, courseList.size());
            idOfTheCourse = courseList.get(input - 1).getId();
        } else System.out.println("No Courses available");
        return idOfTheCourse;
    }

    /**
     * lists all courses and deletes the chosen one.
     *
     * @param person      the person which has the courses.
     * @param integerList List of the containing Courses.
     */
    public void removeChosenCourseWithIntegerList(Person person, List<Integer> integerList) {
        int courseId = choseCourseIdFromIntegerList(integerList);
        if (courseId == -1) {
            System.out.println("There are no courses!");
        } else {
            person.removeCourse(storage.searchCourse(courseId));
        }
    }

    /**
     * lists all courses and deletes the chosen one.
     *
     * @param integerList List of the containing Courses.
     */
    public void removeChosenCourseWithIntegerList(List<Integer> integerList) {
        int courseId = choseCourseIdFromIntegerList(integerList);
        if (courseId == -1) {
            System.out.println("There are no courses!");
        } else {
            storage.remove(storage.searchCourse(courseId));
        }
    }

}
