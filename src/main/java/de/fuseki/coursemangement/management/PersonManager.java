package de.fuseki.coursemangement.management;

import de.fuseki.coursemangement.enums.MenuEnum;
import de.fuseki.coursemangement.exchange.Storage;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.pojos.Person;
import de.fuseki.coursemangement.pojos.Student;
import de.fuseki.coursemangement.ui.CommandLine;

import java.time.LocalDate;
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
        String name = commandLine.readString("name: ", scanner);
        String surname = commandLine.readString("surname: ", scanner);
        String emailAddress = commandLine.readString("email: ", scanner);
        Address address = commandLine.readAddress(scanner);
        LocalDate birthdate = commandLine.readDate("Birthdate", scanner);

        // Collect Subclass specific parameters and return an instance
        if (personTypeOfMenu == MANAGE_LECTURERS) {
            int employeeId = commandLine.readInt("EmployeeId: ", scanner);
            System.out.println(name + "added");
            storage.add(new Lecturer(name, surname, emailAddress, address, birthdate, employeeId));
        } else if (personTypeOfMenu == MANAGE_STUDENTS) {
            int matriculationNumber = commandLine.readInt("Matriculation number:", scanner);
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
     * Lists the needed Person List and returns the index of the chosen Person.
     *
     * @param personTypeOfMenu the enum to separate Lecturers from Students.
     * @return the index of the Person.
     */
    public int choosePerson(MenuEnum personTypeOfMenu) {
        int sizeOfList = this.listPersons(personTypeOfMenu);
        int input;

        if (sizeOfList < 1) {
            System.out.println("there are no Persons");
            return -1;
        } else {
            input = commandLine.readInt("Which Person do you want to choose?", 1, sizeOfList);
        }
        return input - 1;
    }

    /**
     * Asks in the Console for the Person and gives the Person as a Person type.
     *
     * @param personTypeOfMenu to know if it is a student or a Lecturer.
     * @return the Person which has been chosen.
     */
    public Person choosePersonAsPerson(MenuEnum personTypeOfMenu) {
        int indexOfPerson = choosePerson(personTypeOfMenu);
        Person choosenPerson = null;
        if (indexOfPerson != -1) {
            if (personTypeOfMenu == MANAGE_STUDENTS) {
                choosenPerson = storage.getStudentByIndex(indexOfPerson);
            } else if (personTypeOfMenu == MANAGE_LECTURERS) {
                choosenPerson = storage.getLecturerByIndex(indexOfPerson);
            }
        }
        return choosenPerson;
    }

    /**
     * Lists all Students or Lecturers and removes it.
     *
     * @param menuEnum to find if it is a Student or Lecturer.
     */
    public void removePerson(MenuEnum menuEnum) {
        int indexOfPerson = choosePerson(menuEnum);
        if (menuEnum == MANAGE_STUDENTS) {
            storage.remove(storage.getStudentByIndex(indexOfPerson));
        } else if (menuEnum == MANAGE_LECTURERS) {
            storage.remove(storage.getLecturerByIndex(indexOfPerson));
        }

    }
}
