package de.fuseki.coursemangement.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.fuseki.coursemangement.pojos.Student;

import java.io.IOException;

public class CustomStudentSerializer extends StdSerializer<Student> {
    public CustomStudentSerializer() {
        this(null);
    }

    public CustomStudentSerializer(Class<Student> t) {
        super(t);
    }

    @Override
    public void serialize(Student student, JsonGenerator jsonGenerator, SerializerProvider serializer) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", student.getName());
            jsonGenerator.writeStringField("surname", student.getSurname());
            jsonGenerator.writeStringField("emailAddress", student.getEmailAddress());
            jsonGenerator.writeObjectField("address", student.getAddress());
            jsonGenerator.writeStringField("birthdate", student.getBirthdate().toString());
            jsonGenerator.writeNumberField("matriculationNumber", student.getMatriculationNumber());
            jsonGenerator.writeArrayFieldStart("courseIds");
            for (Integer integer : student.getCourses()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", integer);
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
