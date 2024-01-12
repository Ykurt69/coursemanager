package de.fuseki.coursemangement.pojos;

import java.time.LocalDate;

public class Course {
    private final int id;
    private String title;
    private String description;
    private int lecturerId;
    private Address address;
    private LocalDate dateBegin;
    private LocalDate dateEnd;


    /**
     * Initializes an object.
     *
     * @param id          can only set once. Represents the id, of Course. The id has to be unique.
     * @param title       sets the title of the object.
     * @param description sets the description of the course.
     * @param lecturerId    sets the lecturer of the course.
     * @param address     sets the address of the course.
     * @param dateBegin   sets the date at the beginning of the course.
     * @param dateEnd     sets the date at the end of the course.
     */
    public Course(int id, String title, String description, int lecturerId, Address address, LocalDate dateBegin, LocalDate dateEnd) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lecturerId = lecturerId;
        this.address = address;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lecturerId=" + lecturerId +
                ", address=" + address +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                '}';
    }

    /**
     * Getter for the attribute id.
     *
     * @return the attribute id in String
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the attribute title.
     *
     * @return the attribute title in String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the attribute title
     *
     * @param title initialize the attribute title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the attribute description
     *
     * @return the attribute description in String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the attribute description.
     *
     * @param description initializes the attribute description in string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the attribute lecturer.
     *
     * @return the lecturer.
     */
    public int getLecturerId() {
        return lecturerId;
    }

    /**
     * Sets the Lecturer id.
     * @param id of the lecturer.
     */
    public void setLecturerId(int id){
        this.lecturerId = id;
    }

    /**
     * Getter for the attribute address.
     *
     * @return the attribute address in Address type.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for the attribute address.
     *
     * @param address initializes the attribute address.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Getter for the attribute dateBegin.
     *
     * @return the Date of dateBegin.
     */
    public LocalDate getDateBegin() {
        return dateBegin;
    }

    /**
     * Setter for the attribute dateEnd.
     *
     * @param dateBegin initializes the attribute dateBegin.
     */
    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    /**
     * Getter for the attribute dateEnd.
     *
     * @return the attribute dateEnd.
     */
    public LocalDate getDateEnd() {
        return dateEnd;
    }

    /**
     * Setter for the attribute dateEnd.
     *
     * @param dateEnd the value for the attribute in Date type.
     */
    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
