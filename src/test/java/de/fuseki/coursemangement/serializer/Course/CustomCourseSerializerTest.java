package de.fuseki.coursemangement.serializer.Course;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fuseki.coursemangement.serializer.CustomCourseSerializer;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Course;
import de.fuseki.coursemangement.pojos.Lecturer;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomCourseSerializerTest {
    @Test
    public void testCourseSerializer(){
        Address addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        LocalDate birthLenze = LocalDate.of(1989, 4, 14);
        Lecturer lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);
        LocalDate periodBegin = LocalDate.of(2023, 9, 1);
        LocalDate periodEnd = LocalDate.of(2024, 2, 29);
        Address fachbereichInformatik = new Address("Emil-Figge Str.", "42", "Dortmund", "44227");
        Course einfuehrungInDieProgrammierung = new Course(1, "Einführung in die Programmierung", "grundelemente von Java", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule moduleCourse = new SimpleModule("CustomCourseSerializer", new Version(1, 0, 0, null, null, null));
        moduleCourse.addSerializer(Course.class, new CustomCourseSerializer());
        mapper.registerModule(moduleCourse);
        File jsonFile = null;
        String fileInString = "";

        try {
            jsonFile = new File("src/test/java/de/fuseki/coursemangement/serializer/Course/testCustomCourseSerializer.json");
            FileWriter fileWriter = new FileWriter(jsonFile);
            mapper.writeValue(fileWriter, einfuehrungInDieProgrammierung);
            fileInString = new BufferedReader(new FileReader(jsonFile)).readLine();

        } catch (Exception e) {
            System.err.println("Test Serialize Student error!");
            fail();
        }
        String expected = "{\"id\":1,\"title\":\"Einführung in die Programmierung\",\"description\":\"grundelemente von Java\",\"lecturerId\":2,\"address\":{\"street\":\"Emil-Figge Str.\",\"houseNumber\":\"42\",\"city\":\"Dortmund\",\"postalCode\":\"44227\"},\"dateBegin\":\"2023-09-01\",\"dateEnd\":\"2024-02-29\"}";

        assertEquals(expected, fileInString);

    }

}
