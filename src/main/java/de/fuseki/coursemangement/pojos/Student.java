package de.fuseki.coursemangement.pojos;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {
    private final int matriculationNumber;


    /**
     * @param name                attribute of the Student
     * @param surname             attribute of the Student
     * @param emailAddress         attribute of the Student
     * @param address             attribute of the Student
     * @param birthdate           attribute of the Student
     * @param matriculationNumber cannot
     */
    public Student(String name, String surname, String emailAddress, Address address, LocalDate birthdate, int matriculationNumber) {
        super(name, surname, emailAddress, address, birthdate);
        this.matriculationNumber = matriculationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student that = (Student) o;
        return matriculationNumber == that.matriculationNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), matriculationNumber);
    }

    /**
     * @return the attributes of the instance in String.
     */
    @Override
    public String toString() {
        return
                "matriculationNumber : " + matriculationNumber +
                ", " + super.toString();
    }

    /**
     * @return the value of the attribute matriculationNumber.
     */
    public int getMatriculationNumber() {
        return matriculationNumber;
    }

}
