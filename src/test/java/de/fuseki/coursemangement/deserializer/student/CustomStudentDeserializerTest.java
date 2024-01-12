package de.fuseki.coursemangement.deserializer.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fuseki.coursemangement.deserializer.CustomStudentDeserializer;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Student;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomStudentDeserializerTest {
    @Test
    public void testCustomStudentDeserializer(){
        LocalDate birthYusuf = LocalDate.of(2003, 12, 28);
        Address addressNeusta = new Address("Schürrmannstraße", "32", "Essen", "45136");
        Student expectedStudent = new Student("Yusuf", "Kurt", "ykurt@neusta-sd-west.de", addressNeusta, birthYusuf, 7218115);
        expectedStudent.addCourse(1);

        File testFile = new File("src/test/java/de/fuseki/coursemangement/deserializer/student/testDeserailizeStudent.json");
        SimpleModule module = new SimpleModule("CustomStudentDeserializer");
        module.addDeserializer(Student.class, new CustomStudentDeserializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        Student testStudent;

        try {
            testStudent = mapper.readValue(testFile, Student.class);

        } catch (IOException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals(expectedStudent.toString(),testStudent.toString());
    }
}
