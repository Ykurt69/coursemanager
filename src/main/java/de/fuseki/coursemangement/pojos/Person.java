package de.fuseki.coursemangement.pojos;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Person {

    protected ArrayList<Integer> courses = new ArrayList<>();
    private String name;
    private String surname;
    private String emailAddress;
    private Address address;
    private LocalDate birthdate;


    /**
     * Person is an abstract pojo class with the following Attributes and an toString method.
     *
     * @param name         of the Person.
     * @param surname      of the Person.
     * @param emailAddress of the Person.
     * @param address      of the Person.
     * @param birthdate    of the Person.
     */
    public Person(String name, String surname, String emailAddress, Address address, LocalDate birthdate) {
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.address = address;
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(birthdate, person.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthdate);
    }

    /**
     * Converts the attributes of the object in to a String.
     *
     * @return String which contains all attributes of the object.
     */
    @Override
    public String toString() {
        return "name: " + this.name +
                ", surname: " + this.surname +
                ", birthdate: " + this.birthdate.getYear() + "-" + this.birthdate.getMonth() + "-" + this.birthdate.getDayOfMonth() +
                ", address: " + this.address.toString() +
                ", email-address: " + this.emailAddress;
    }

    /**
     * Getter for the attribute name.
     *
     * @return the Attribute name in String.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for attribute name
     *
     * @param name initialize the attribute name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the attribute surname
     *
     * @return the attribute surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for the attribute surname.
     *
     * @param surname is assigning the attribute
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for the attribute email.
     *
     * @return the email in String.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter for the attribute Email
     *
     * @param emailAddress is assigning the attribute emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter for the attribute Address.
     *
     * @return the address in Address type.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for the attribute address.
     *
     * @param address assigning the attribute address.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Getter for the attribute birthdate.
     *
     * @return the attribute date in LocalDate type.
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Setter for the attribute birthdate.
     *
     * @param birthdate assigning the attribute birthdate in Date type.
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return List of all courses in which the participant visits
     */
    public List<Integer> getCourses() {
        return courses;
    }

    /**
     * Sets the ArrayList of the courses.
     *
     * @param courses which the student visits.
     */
    public void setCourseIds(List<Integer> courses) {
        this.courses = (ArrayList<Integer>) courses;
    }

    /**
     * @param course adds a courseId in to the ArrayList of the Object
     */
    public void addCourse(Course course) {
        if (!this.courses.contains(course.getId())) {
            this.courses.add(course.getId());
        }
    }

    /**
     * @param courseId adds a courseId in to the ArrayList of the Object
     */
    public void addCourse(int courseId) {
        if (!this.courses.contains(courseId)) {
            this.courses.add(courseId);
        }
    }

    /**
     * @param course removes a course out of the ArrayList
     */
    public void removeCourse(Course course) {
        this.courses.remove(new Integer(course.getId()));
    }
}
