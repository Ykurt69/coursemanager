package de.fuseki.coursemangement.serializer.Student;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Student;
import de.fuseki.coursemangement.serializer.CustomStudentSerializer;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomStudentSerializerTest {

    @Test
    public void testSerializeStudent() {
        LocalDate birthYusuf = LocalDate.of(2003, 12, 28);
        Address addressNeusta = new Address("Schürrmannstraße", "32", "Essen", "45136");
        Student testStudent = new Student("Yusuf", "Kurt", "ykurt@neusta-sd-west.de", addressNeusta, birthYusuf, 7218115);

        testStudent.addCourse(2);
        testStudent.addCourse(1);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule moduleStudent = new SimpleModule("CustomStudentSerializer", new Version(1, 0, 0, null, null, null));
        moduleStudent.addSerializer(Student.class, new CustomStudentSerializer());
        mapper.registerModule(moduleStudent);
        File jsonFile = null;
        String fileInString = "";
        try{
            jsonFile = new File("src/test/java/de/fuseki/coursemangement/serializer/Student/testSerailizeStudent.json");
            FileWriter fileWriter = new FileWriter(jsonFile);
            mapper.writeValue(fileWriter,testStudent);
            fileInString = new BufferedReader(new FileReader(jsonFile)).readLine();

        }catch (Exception e){
            System.err.println("Test Serialize Student error!");
            fail();
        }

        String expected = "{\"name\":\"Yusuf\",\"surname\":\"Kurt\",\"emailAddress\":\"ykurt@neusta-sd-west.de\",\"address\":{\"street\":\"Schürrmannstraße\",\"houseNumber\":\"32\",\"city\":\"Essen\",\"postalCode\":\"45136\"},\"birthdate\":\"2003-12-28\",\"matriculationNumber\":7218115,\"courseIds\":[{\"id\":2},{\"id\":1}]}";

        assertEquals(expected,fileInString);

    }
}
