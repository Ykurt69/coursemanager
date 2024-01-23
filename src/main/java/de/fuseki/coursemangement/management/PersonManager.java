package de.fuseki.coursemangement.management;

import de.fuseki.coursemangement.enums.MenuEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.pojos.Person;
import de.fuseki.coursemangement.pojos.Student;
import de.fuseki.coursemangement.ui.CommandLine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static de.fuseki.coursemangement.enums.MenuEnum.MANAGE_LECTURERS;
import static de.fuseki.coursemangement.enums.MenuEnum.MANAGE_STUDENTS;

public class PersonManager extends Manager {

    public PersonManager(CommandLine commandLine, Scanner sc, Storage storage) {
        super(commandLine, sc, storage);
    }

    /**
     * Instances a lecturer or Student out of console and adds into the Storage.
     */
    public void createPerson(MenuEnum personTypeOfMenu) {
        // Collect all Parameters
        String name = commandLine.readString("name: ");
        String surname = commandLine.readString("surname: ");
        String emailAddress = commandLine.readString("email: ");
        Address address = commandLine.readAddress(scanner);
        LocalDate birthdate = commandLine.readDate("Birthdate", scanner);

        // Collect Subclass specific parameters and return an instance
        if (personTypeOfMenu == MANAGE_LECTURERS) {
            int employeeId = commandLine.readInt("EmployeeId: ");
            System.out.println(name + "added");
            storage.add(new Lecturer(name, surname, emailAddress, address, birthdate, employeeId));
        } else if (personTypeOfMenu == MANAGE_STUDENTS) {
            int matriculationNumber = commandLine.readInt("Matriculation number:");
            System.out.println(name + " added\n");
            storage.add(new Student(name, surname, emailAddress, address, birthdate, matriculationNumber));
        } else throw new IllegalArgumentException();
    }

    /**
     * Lists every Student or Lecturer in The console and returns the number of Persons in the List.
     *
     * @param personTypeOfMenu decides if it is a student or a Person;
     */
    public int listPersons(MenuEnum personTypeOfMenu) {
        if (personTypeOfMenu == MANAGE_STUDENTS) {
            List<Student> studentList = storage.getStudents();
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println(i + 1 + ". " + studentList.get(i));
            }
            return studentList.size();
        } else if (personTypeOfMenu == MANAGE_LECTURERS) {
            List<Lecturer> lecturerList = storage.getLecturers();
            for (int i = 0; i < lecturerList.size(); i++) {
                System.out.println(i + 1 + ". " + lecturerList.get(i));
            }
            return lecturerList.size();
        } else return -1;
    }

    /**
     * Lists all persons that match the filter.
     *
     * @param personTypeOfMenu needed to know if a Student or a Lecturer is mean.
     * @param filter           compares with the actual list from the storage and prints the matching Persons.
     * @return if there was any person.
     */
    public boolean listPersons(MenuEnum personTypeOfMenu, String filter) {
        if (personTypeOfMenu == MANAGE_STUDENTS) {
            List<Student> filteredstudentList = filterStudentList(filter);
            for (int i = 0; i < filteredstudentList.size(); i++) {
                System.out.println(i + 1 + ". " + filteredstudentList.get(i));
            }
            return true;
        } else if (personTypeOfMenu == MANAGE_LECTURERS) {
            List<Lecturer> lecturerList = filterLecturerList(filter);
            for (int i = 0; i < lecturerList.size(); i++) {
                System.out.println(i + 1 + ". " + lecturerList.get(i));
            }
            return true;
        } else return false;
    }

    private List<Student> filterStudentList(String filter) {
        List<Student> unfilteredStudentList = storage.getStudents();
        List<Student> filteredStudentList = new ArrayList<>();
        for (Student student : unfilteredStudentList) {
            if (student.toString().contains(filter)) {
                filteredStudentList.add(student);
            }
        }
        return filteredStudentList;
    }

    private List<Lecturer> filterLecturerList(String filter) {
        List<Lecturer> unfilteredLecturerList = storage.getLecturers();
        List<Lecturer> filteredLecturerList = new ArrayList<>();
        for (Lecturer lecturer : unfilteredLecturerList) {
            if (lecturer.toString().contains(filter)) {
                filteredLecturerList.add(lecturer);
            }
        }
        return filteredLecturerList;
    }


    /**
     * Lists the needed Person List and returns the index of the chosen Person.
     *
     * @param personTypeOfMenu the enum to separate Lecturers from Students.
     * @return the index of the Person.
     */
    public int choosePersonIdWithFilter(MenuEnum personTypeOfMenu) {
        String filter = commandLine.readString("Type in the filter. (All matches with the filter will be shown.)");
        boolean anyPerson = this.listPersons(personTypeOfMenu, filter);
        int input = 0;
        boolean personExists = false;
        while (!personExists) {
            if (anyPerson) {
                if (personTypeOfMenu == MANAGE_STUDENTS) {
                    input = commandLine.readInt("Type in the Matriculation number of the Student.");
                } else {
                    input = commandLine.readInt("Type in the ID of the Lecturer.");
                }
            } else {
                System.out.println("there are no Persons");
                return -1;
            }
            personExists = checkPersonExists(personTypeOfMenu, input);
            if (!personExists) {
                System.err.println("Not existing please retry!");
            }
        }
        return input;
    }

    /**
     * Checks if the input matches id or a matriculation number.
     *
     * @param personTypeOfMenu needed to know if it is a matriculation number or an id.
     * @param input            the id or matriculation number which has to be checked.
     * @return if the id or matriculation number exists.
     */
    private boolean checkPersonExists(MenuEnum personTypeOfMenu, int input) {
        Person person = null;
        switch (personTypeOfMenu) {
            case MANAGE_STUDENTS:
                person = storage.searchStudentByMatNumber(input);
                break;
            case MANAGE_LECTURERS:
                person = storage.searchLecturer(input);
        }
        return person != null;
    }

    /**
     * Asks in the Console for the Person and gives the Person as a Person type.
     *
     * @param personTypeOfMenu to know if it is a student or a Lecturer.
     * @return the Person which has been chosen.
     */
    public Person choosePersonAsPerson(MenuEnum personTypeOfMenu) {
        int ID = choosePersonIdWithFilter(personTypeOfMenu);
        Person choosenPerson = null;
        if (ID != -1) {
            if (personTypeOfMenu == MANAGE_STUDENTS) {
                choosenPerson = storage.searchStudentByMatNumber(ID);
            } else if (personTypeOfMenu == MANAGE_LECTURERS) {
                choosenPerson = storage.searchLecturer(ID);
            }
        } else {
            System.err.println("Not found.");
            return null;
        }
        return choosenPerson;
    }

    /**
     * Lists all Students or Lecturers and removes it.
     *
     * @param menuEnum to find if it is a Student or Lecturer.
     */
    public void removePerson(MenuEnum menuEnum) {
        int id = choosePersonIdWithFilter(menuEnum);
        if (menuEnum == MANAGE_STUDENTS) {
            storage.remove(storage.searchStudentByMatNumber(id));
        } else if (menuEnum == MANAGE_LECTURERS) {
            storage.remove(storage.getLecturerByIndex(id));
        }
    }
}
