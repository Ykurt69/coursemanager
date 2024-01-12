package de.fuseki.coursemangement.pojos;

import java.time.LocalDate;
import java.util.Objects;

public class Lecturer extends Person {
    private final int employeeId;

    /**
     * Initializes a Lecturer which has an ArrayList of Courses
     *
     * @param name         of the lecturer.
     * @param surname      of the lecturer.
     * @param emailAddress of the lecturer.
     * @param address      of the lecturer.
     * @param birthdate    of the lecturer.
     * @param employeeId   of the lecturer.
     */
    public Lecturer(String name, String surname, String emailAddress, Address address, LocalDate birthdate, int employeeId) {
        super(name, surname, emailAddress, address, birthdate);
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lecturer lecturer = (Lecturer) o;
        return employeeId == lecturer.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId);
    }

    @Override
    public String toString() {
        return "Employee ID: " + this.employeeId + ", " + super.toString();
    }

    /**
     * Getter for the employeeId.
     *
     * @return the value of the employeeId.
     */
    public int getEmployeeId() {
        return employeeId;
    }
}
