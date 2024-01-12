package de.fuseki.coursemangement.deserializer.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fuseki.coursemangement.deserializer.CustomCourseDeserializer;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Course;
import de.fuseki.coursemangement.pojos.Lecturer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class testCustomCourseDeserializer {
    @Test
    public void testDeserializeCourse() {
        Course einfuehrungInDieProgrammierung = getCourse();

        File testFile = new File("src/test/java/de/fuseki/coursemangement/deserializer/course/testCustomCourseDeserializer.json");
        ObjectMapper testMapper = new ObjectMapper();
        SimpleModule courseDeserializerModule = new SimpleModule("CustomCourseDeserializer");
        courseDeserializerModule.addDeserializer(Course.class, new CustomCourseDeserializer());
        testMapper.registerModule(courseDeserializerModule);
        Course course;
        try {
            course = testMapper.readValue(testFile, Course.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(einfuehrungInDieProgrammierung.toString(),course.toString());
    }

    private static Course getCourse() {
        Address addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        LocalDate birthLenze = LocalDate.of(1989, 4, 14);
        Lecturer lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);
        LocalDate periodBegin = LocalDate.of(2023, 9, 1);
        LocalDate periodEnd = LocalDate.of(2024, 2, 29);
        Address fachbereichInformatik = new Address("Emil-Figge Str.", "42", "Dortmund", "44227");
        return new Course(1, "Einführung in die Programmierung", "grundelemente von Java", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);
    }
}
