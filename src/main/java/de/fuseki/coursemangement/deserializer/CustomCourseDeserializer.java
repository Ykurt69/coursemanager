package de.fuseki.coursemangement.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Course;

import java.io.IOException;
import java.time.LocalDate;

public class CustomCourseDeserializer extends StdDeserializer<Course> {
    public CustomCourseDeserializer(){
        this(null);
    }

    public CustomCourseDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public Course deserialize(JsonParser parser , DeserializationContext deserializer){
        ObjectCodec codec = parser.getCodec();
        Course course;
        try {
            JsonNode node = codec.readTree(parser);
            int id = node.get("id").asInt();
            String title = node.get("title").asText();
            String description = node.get("description").asText();
            int lecturerId = node.get("lecturerId").asInt();
            // Using the AddressDeserializer
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode addressAsNode = node.get("address");
            Address address = objectMapper.treeToValue(addressAsNode, Address.class);
            // Creating the Date
            LocalDate dateBegin = LocalDate.parse(node.get("dateBegin").asText());
            LocalDate dateEnd = LocalDate.parse(node.get("dateEnd").asText());
            // Creating the course
            course = new Course(id,title,description,lecturerId,address,dateBegin,dateEnd);
        } catch (IOException e) {
            System.err.println("Failed to deserialize Course.");
            throw new RuntimeException(e);
        }
        return course;
    }

}
