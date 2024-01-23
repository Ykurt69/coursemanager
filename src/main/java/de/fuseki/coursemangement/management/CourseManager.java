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
        int id = commandLine.readInt("ID: ");
        String title = commandLine.readString("title: ");
        String description = commandLine.readString("description: ");
        Lecturer lecturer = commandLine.findLecturer(scanner, storage);
        Address address = commandLine.readAddress(scanner);
        LocalDate dateBegin = commandLine.readDate("Begin date", scanner);
        LocalDate dateEnd = commandLine.readDate("End date", scanner);

        storage.add(new Course(id, title, description, lecturer.getEmployeeId(), address, dateBegin, dateEnd));
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
    public void listCourses(List<Course> courses) {
        if (!courses.isEmpty()) {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println((i + 1) + ". " + courses.get(i).toString());
            }
        }
    }


    public Course chooseCourseByFilteredList() {
        int courseId = chooseCourseIdByFilteredList();
        Course choosenCourse = null;
        if (courseId != -1) {
            choosenCourse = storage.searchCourse(courseId);
        }
        return choosenCourse;
    }

    private int chooseCourseIdByFilteredList() {
        List<Course> filteredCourseList = getFilteredList();
        listCourses(filteredCourseList);

        int chosenId = 0;
        boolean idFound = false;
        if (!filteredCourseList.isEmpty()) {
            while (!idFound && chosenId != -1) {
                chosenId = commandLine.readInt("Type the Id of the Course.", "Abort");
                if (chosenId != -1) {
                    idFound = checkIfCourseIdExists(chosenId);
                    if (!idFound) System.err.println("Wrong id pls retry!");
                }
            }
        }
        return chosenId;
    }

    private boolean checkIfCourseIdExists(int chosenId) {
        for (Course course : storage.getAllCourses()) {
            if (course.getId() == chosenId) {
                return true;
            }
        }
        return false;
    }

    private List<Course> getFilteredList() {
        String filter = commandLine.readString("Enter the filter. All courses which match this filter will be listed.");
        List<Course> filteredList = new ArrayList<>();
        for (Course course : storage.getAllCourses()) {
            if (course.toString().contains(filter)) {
                filteredList.add(course);
            }
        }
        if (filteredList.isEmpty()) System.err.println("No Courses with this filter!");
        return filteredList;
    }

    public void removeChosenCourseWithFilteredList() {
        Course course = chooseCourseByFilteredList();
        if (course != null) {
            storage.remove(course);
        }
    }
}
