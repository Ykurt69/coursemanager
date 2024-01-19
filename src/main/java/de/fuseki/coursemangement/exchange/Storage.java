package de.fuseki.coursemangement.exchange;

import de.fuseki.coursemangement.pojos.Course;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.pojos.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {
    private ArrayList<Course> allCourses = new ArrayList<>();
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Storage storage = (Storage) object;
        return Objects.equals(allCourses.toString(), storage.allCourses.toString()) && Objects.equals(lecturers.toString(), storage.lecturers.toString()) && Objects.equals(students.toString(), storage.students.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(allCourses, lecturers, students);
    }

    /**
     * Searches for a lecturer by the ID.
     *
     * @param id the ID of the searches Lecturer.
     * @return the Lecturer with the ID of sought and null if there's no Lecturer with this ID.
     */
    public Lecturer searchLecturer(int id) {
        Lecturer foundedLecturer = null;
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getEmployeeId() == id) {
                foundedLecturer = lecturer;
            }
        }
        return foundedLecturer;
    }

    /**
     * Searches by the id for a Course in the storage.
     *
     * @param id of the searched Course.
     * @return the found course or null if the Course is not in the Storage.
     */
    public Course searchCourse(int id) {
        Course foundedCourse = null;
        for (Course course : allCourses) {
            if (course.getId() == id) {
                foundedCourse = course;
            }
        }
        return foundedCourse;
    }

    public List<Course> convertIdsToCourses(List<Integer> ids) {
        List<Course> courseList = new ArrayList<>();
        for (Integer courseId : ids) {
            courseList.add(this.searchCourse(courseId));
        }
        return courseList;
    }

    public List<Integer> convertCoursesToIds(List<Course> courses) {
        List<Integer> integerList = new ArrayList<>();
        for (Course course : courses) {
            integerList.add(course.getId());
        }
        return integerList;
    }

    /**
     * Searches a Student by a matriculation number.
     *
     * @param matriculationNumber of the searched student.
     * @return the Student or null if the student is not in the Storage.
     */
    public Student searchStudentByMatNumber(int matriculationNumber) {
        Student foundStudent = null;
        for (Student student : students) {
            if (student.getMatriculationNumber() == matriculationNumber) {
                foundStudent = student;
            }
        }
        return foundStudent;
    }

    public Student getStudentByIndex(int index) {
        return students.get(index);
    }

    public Lecturer getLecturerByIndex(int index) {
        return lecturers.get(index);
    }

    /**
     * Depend on the Argument type adds an object into the Storage.
     *
     * @param lecturer adds a Lecturer in the Storage.
     */
    public void add(Lecturer lecturer) {
        if (!this.lecturers.contains(lecturer)) {
            this.lecturers.add(lecturer);
        }
    }

    /**
     * Depend on the Argument type adds an object into the Storage.
     *
     * @param student adds a Student in the Storage.
     */
    public void add(Student student) {
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    /**
     * Depend on the Argument type adds an object into the Storage.
     *
     * @param course adds a Course in the Storage.
     */
    public void add(Course course) {
        if (!this.allCourses.contains(course)) {
            this.allCourses.add(course);
        }
    }

    /**
     * Removes a Course of the Storage.
     *
     * @param course the course which has to be removed.
     */
    public void remove(Course course) {
        this.allCourses.remove(course);

    }

    /**
     * Remove a Lecturer of the Storage.
     *
     * @param lecturer to be removed.
     */
    public void remove(Lecturer lecturer) {
        lecturers.remove(lecturer);
    }

    /**
     * Removes a Student of the Storage.
     *
     * @param student to be removed.
     */
    public void remove(Student student) {
        students.remove(student);
    }


    public List<Course> getAllCourses() {
        return allCourses;
    }


    public List<Lecturer> getLecturers() {
        return lecturers;
    }


    public List<Student> getStudents() {
        return students;
    }

    /**
     * Setter for the list of courses.
     * Converts the list into an ArrayList.
     *
     * @param allCourses the list of the course.
     */
    public void setAllCourses(List<Course> allCourses) {
        this.allCourses = (ArrayList<Course>) allCourses;
    }

    /**
     * Setter for the list of Lecturers.
     * Converts the list into an ArrayList.
     *
     * @param lecturers the List of Lecturers.
     */
    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = (ArrayList<Lecturer>) lecturers;
    }

    /**
     * Setter for the List of Students.
     * Converts the list into an ArrayList.
     *
     * @param students the List of Students.
     */
    public void setStudents(List<Student> students) {
        this.students = (ArrayList<Student>) students;
    }
}
