package de.fuseki.coursemangement.exchange;

import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Course;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.pojos.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    Address addressLenze;
    Address adressmueller;
    Address fachbereichInformatik;
    Address addressNeusta;
    LocalDate periodBegin;
    LocalDate periodEnd;
    LocalDate birthLenze;
    LocalDate birthMueller;
    LocalDate birthYusuf;

    Lecturer lenze;
    Lecturer mueller;

    Course einfuehrungInDieProgrammierung;
    Course matheFuerInformatik;

    Student yusuf;

    Storage storage;
    /*




     */
    @BeforeEach
    public void setUp() {
        addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        adressmueller = new Address("Friedenrikenstraße", "22a", "Essen", "45130");
        fachbereichInformatik = new Address("Emil-Figge Str.", "42", "Dortmund", "44227");
        addressNeusta = new Address("Schürrmannstraße", "32", "Essen", "45136");

        periodBegin = LocalDate.of(2023, 9, 1);
        periodEnd = LocalDate.of(2024, 2, 29);
        birthLenze = LocalDate.of(1989, 4, 14);
        birthMueller = LocalDate.of(1980, 11, 8);
        birthYusuf = LocalDate.of(2003, 12, 28);

        lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 614268);
        mueller = new Lecturer("Peter", "Mueller", "Mueller@fh-dortmund.de", adressmueller, birthMueller, 625343);

        einfuehrungInDieProgrammierung = new Course(55555, "Einführung in die Programmierung", "grundelemente von Java", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);
        matheFuerInformatik = new Course(50001, "Mathe für Unformatik", "Grundlagen der Mathematik", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);

        yusuf = new Student("Yusuf", "Kurt", "ykurt@neusta-sd-west.de", addressNeusta, birthYusuf, 7218115);
        storage = new Storage();
    }

    @Test
    public void testSearchLecturerWithTheLecturerInStorage() {
        // Given
        Storage storage = new Storage();
        storage.add(lenze);

        // Method
        Lecturer result = storage.searchLecturer(lenze.getEmployeeId());

        // Assert
        assertEquals(lenze, result);
    }

    @Test
    public void testSearchStudentWithTheStudentInStorage() {
        // Given
        storage.add(yusuf);

        // Method
        Student result = storage.searchStudentByMatNumber(yusuf.getMatriculationNumber());

        // Assert
        assertEquals(yusuf, result);
    }

    @Test
    public void testSearchCourseWithTheCourseInStorage() {
        // Given
        storage.add(matheFuerInformatik);

        // Method
        Course result = storage.searchCourse(matheFuerInformatik.getId());

        // Assert
        assertEquals(matheFuerInformatik, result);
    }

    @Test
    public void testSearchLecturorWithoutTheLecturorInStorage() {
        // Method
        Lecturer result = storage.searchLecturer(lenze.getEmployeeId());

        // Assert
        assertNull(result);
    }

    @Test
    public void testSearchStudentWithoutTheStudentInStorage() {
        // Method
        Student result = storage.searchStudentByMatNumber(yusuf.getMatriculationNumber());

        // Assert
        assertNull(result);
    }

    @Test
    public void testSearchCourseWithoutTheCourseInStorage() {
        // Method
        Course result = storage.searchCourse(matheFuerInformatik.getId());

        // Assert
        assertNull(result);
    }

    @Test
    public void testRemoveCourseFromStorage() {
        // Given
        storage.add(einfuehrungInDieProgrammierung);

        // Method
        storage.remove(einfuehrungInDieProgrammierung);

        // assert
        assertEquals(0, storage.getAllCourses().size());
    }

    @Test
    public void testRemoveLecturerFromStorage() {
        // Given
        storage.add(lenze);
        storage.add(mueller);

        // Method
        storage.remove(mueller);

        // Assert
        assertEquals(1, storage.getLecturers().size());
    }

    @Test
    public void testRemoveStudentFromStorage() {
        storage.add(yusuf);

        // Method
        storage.remove(yusuf);

        // Assert
        assertEquals(0, storage.getStudents().size());
    }

    @Test
    public void testConvertIdsToCoursesWithCoursesWhichHasTheSameId(){
        // Given
        List<Integer> testIntegerList = new ArrayList<>();
        testIntegerList.add(50001);
        testIntegerList.add(55555);

        storage.add(matheFuerInformatik);
        storage.add(einfuehrungInDieProgrammierung);
        //Assert
        assertEquals(storage.getAllCourses(), storage.convertIdsToCourses(testIntegerList));
    }

    @Test
    public void testConvertIdsToCoursesWithoutTheSameId(){
        // Given
        List<Integer> testIntegerList = new ArrayList<>();
        testIntegerList.add(1);
        testIntegerList.add(55555);

        storage.add(matheFuerInformatik);
        storage.add(einfuehrungInDieProgrammierung);

        List<Course> expected = new ArrayList<>();
        expected.add(null);
        expected.add(einfuehrungInDieProgrammierung);
        //Assert
        assertEquals(expected, storage.convertIdsToCourses(testIntegerList));
    }

    @Test
    public void testConvertCoursesToIdsWhithCoursesIsEqualToIds(){
        // Given
        List<Integer> testIntegerList = new ArrayList<>();
        testIntegerList.add(50001);
        testIntegerList.add(55555);

        storage.add(matheFuerInformatik);
        storage.add(einfuehrungInDieProgrammierung);

        assertEquals(testIntegerList, storage.convertCoursesToIds(storage.getAllCourses()));
    }
}