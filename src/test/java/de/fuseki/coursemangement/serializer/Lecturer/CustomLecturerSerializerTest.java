package de.fuseki.coursemangement.serializer.Lecturer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.serializer.CustomLecturerSerializer;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomLecturerSerializerTest {

    @Test
    public void testSerializeLecturer() {
        Address addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        LocalDate birthLenze = LocalDate.of(1989, 4, 14);
        Lecturer lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);

        lenze.addCourse(2);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule moduleLecturer = new SimpleModule("CustomLecturerSerializer", new Version(1, 0, 0, null, null, null));
        moduleLecturer.addSerializer(Lecturer.class, new CustomLecturerSerializer());
        mapper.registerModule(moduleLecturer);
        File jsonFile = null;
        String fileInString = "";

        try {
            jsonFile = new File("src/test/java/de/fuseki/coursemangement/serializer/Lecturer/testCustomLecturerSerializer.json");
            FileWriter fileWriter = new FileWriter(jsonFile);
            mapper.writeValue(fileWriter, lenze);
            fileInString = new BufferedReader(new FileReader(jsonFile)).readLine();

        } catch (Exception e) {
            System.err.println("Test Serialize Student error!");
            fail();
        }
        String expected = "{\"name\":\"Harald\",\"surname\":\"Lenze\",\"emailAddress\":\"lenze@fh-dortmund.de\",\"address\":{\"street\":\"Schürrmannstraße\",\"houseNumber\":\"32\",\"city\":\"Essen\",\"postalCode\":\"45136\"},\"birthdate\":\"1989-04-14\",\"employeeId\":2,\"courseIds\":[{\"id\":2}]}";

        assertEquals(expected, fileInString);


    }
}
