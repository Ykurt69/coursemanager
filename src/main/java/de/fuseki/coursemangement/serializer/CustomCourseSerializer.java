package de.fuseki.coursemangement.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.fuseki.coursemangement.pojos.Course;

import java.io.IOException;

public class CustomCourseSerializer extends StdSerializer<Course> {

    public CustomCourseSerializer() {
        this(null);
    }

    public CustomCourseSerializer(Class<Course> t) {
        super(t);
    }


    @Override
    public void serialize(Course course, JsonGenerator jsonGenerator, SerializerProvider serializer) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", course.getId());
            jsonGenerator.writeStringField("title", course.getTitle());
            jsonGenerator.writeStringField("description", course.getDescription());
            jsonGenerator.writeNumberField("lecturerId", course.getLecturerId());
            jsonGenerator.writeObjectField("address", course.getAddress());
            jsonGenerator.writeStringField("dateBegin", course.getDateBegin().toString());
            jsonGenerator.writeStringField("dateEnd", course.getDateEnd().toString());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
