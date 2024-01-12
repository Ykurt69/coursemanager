package de.fuseki.coursemangement.deserializer.lecturer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fuseki.coursemangement.deserializer.CustomLecturerDeserializer;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Lecturer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class testCustomLecturerDeserializer {
    @Test
    public void testDeserializeLecturer(){
        LocalDate birthLenze = LocalDate.of(1989, 4, 14);
        Address addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        Lecturer expectedLecturer = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);
        expectedLecturer.addCourse(2);

        File testFile = new File("src/test/java/de/fuseki/coursemangement/deserializer/lecturer/testCustomLecturerDeserializer.json");
        SimpleModule lecturerDeserializer = new SimpleModule("CustomLecturerDeserializer");
        lecturerDeserializer.addDeserializer(Lecturer.class, new CustomLecturerDeserializer());
        ObjectMapper testMapper = new ObjectMapper();
        testMapper.registerModule(lecturerDeserializer);
        Lecturer testLecturer;

        try {
            testLecturer = testMapper.readValue(testFile, Lecturer.class);
        } catch (IOException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals(expectedLecturer.toString(),testLecturer.toString());
    }
}
