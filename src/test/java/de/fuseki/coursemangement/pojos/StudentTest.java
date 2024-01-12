package de.fuseki.coursemangement.pojos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void addCourseTestAdd1Course() {
        // Given
        Address addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        Address fachbereichInformatik = new Address("Emil-Figge Str.", "42", "Dortmund", "44227");
        Address addressNeusta = new Address("Schürrmannstraße", "32", "Essen", "45136");

        LocalDate periodBegin = LocalDate.of(2023, 9, 1);
        LocalDate periodEnd = LocalDate.of(2024, 2, 29);
        LocalDate birthLenze = LocalDate.of(1989, 4, 14);
        LocalDate birthYusuf = LocalDate.of(2003, 12, 28);

        Lecturer lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);

        Course einfuehrungInDieProgrammierung = new Course(55555, "Einführung in die Programmierung", "grundelemente von Java", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);

        Student yusufTest = new Student("Yusuf", "Kurt", "ykurt@neusta-sd-west.de", addressNeusta, birthYusuf, 7218115);

        // Method
        yusufTest.addCourse(einfuehrungInDieProgrammierung);

        Integer[] testCourse = {einfuehrungInDieProgrammierung.getId()};
        // Assert
        assertArrayEquals(testCourse, yusufTest.getCourses().toArray());
    }

    @Test
    void addCourseTestAdd1Course2Times() {
        // Given
        Address addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        Address fachbereichInformatik = new Address("Emil-Figge Str.", "42", "Dortmund", "44227");
        Address addressNeusta = new Address("Schürrmannstraße", "32", "Essen", "45136");

        LocalDate periodBegin = LocalDate.of(2023, 9, 1);
        LocalDate periodEnd = LocalDate.of(2024, 2, 29);
        LocalDate birthLenze = LocalDate.of(1989, 4, 14);
        LocalDate birthYusuf = LocalDate.of(2003, 12, 28);

        Lecturer lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);

        Course einfuehrungInDieProgrammierung = new Course(55555, "Einführung in die Programmierung", "grundelemente von Java", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);

        Student yusufTest = new Student("Yusuf", "Kurt", "ykurt@neusta-sd-west.de", addressNeusta, birthYusuf, 7218115);

        // Method
        yusufTest.addCourse(einfuehrungInDieProgrammierung);
        yusufTest.addCourse(einfuehrungInDieProgrammierung);
        Integer[] testCourse = {einfuehrungInDieProgrammierung.getId()};

        // Assert
        assertArrayEquals(testCourse, yusufTest.getCourses().toArray());
    }

    @Test
    void removeCourse() {
        // Given
        Address addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        Address fachbereichInformatik = new Address("Emil-Figge Str.", "42", "Dortmund", "44227");
        Address addressNeusta = new Address("Schürrmannstraße", "32", "Essen", "45136");

        LocalDate periodBegin = LocalDate.of(2023, 9, 1);
        LocalDate periodEnd = LocalDate.of(2024, 2, 29);
        LocalDate birthLenze = LocalDate.of(1989, 4, 14);
        LocalDate birthYusuf = LocalDate.of(2003, 12, 28);

        Lecturer lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);

        Course einfuehrungInDieProgrammierung = new Course(55555, "Einführung in die Programmierung", "grundelemente von Java", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);

        Student yusufTest = new Student("Yusuf", "Kurt", "ykurt@neusta-sd-west.de", addressNeusta, birthYusuf, 7218115);
        yusufTest.addCourse(einfuehrungInDieProgrammierung);

        // Method
        yusufTest.removeCourse(einfuehrungInDieProgrammierung);

        // Assert
        assertEquals(0, yusufTest.getCourses().size());
    }

}