package de.fuseki.coursemangement.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.fuseki.coursemangement.pojos.Lecturer;

import java.io.IOException;

public class CustomLecturerSerializer extends StdSerializer<Lecturer> {
    public CustomLecturerSerializer() {
        this(null);
    }

    public CustomLecturerSerializer(Class<Lecturer> t) {
        super(t);
    }


    @Override
    public void serialize(Lecturer lecturer, JsonGenerator jsonGenerator, SerializerProvider serializer) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", lecturer.getName());
            jsonGenerator.writeStringField("surname", lecturer.getSurname());
            jsonGenerator.writeStringField("emailAddress", lecturer.getEmailAddress());
            jsonGenerator.writeObjectField("address", lecturer.getAddress());
            jsonGenerator.writeStringField("birthdate", lecturer.getBirthdate().toString());
            jsonGenerator.writeNumberField("employeeId", lecturer.getEmployeeId());
            jsonGenerator.writeArrayFieldStart("courseIds");
            for (Integer integer : lecturer.getCourses()) {
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
