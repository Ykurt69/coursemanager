package de.fuseki.coursemangement.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.pojos.Address;

import java.time.LocalDate;
import java.util.ArrayList;

public class CustomLecturerDeserializer extends StdDeserializer<Lecturer> {
    public CustomLecturerDeserializer() {
        this(null);
    }

    public CustomLecturerDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public Lecturer deserialize(JsonParser parser, DeserializationContext deserializer) {
        ObjectCodec codec = parser.getCodec();
        Lecturer lecturer;
        try {
            JsonNode node = codec.readTree(parser);
            String name = node.get("name").asText();
            String surname = node.get("surname").asText();
            String emailAddress = node.get("emailAddress").asText();
            // Create Address
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode addressAsNode = node.get("address");
            Address address = objectMapper.treeToValue(addressAsNode, Address.class);

            // Create Date
            LocalDate birthdate = LocalDate.parse(node.get("birthdate").asText());
            int employeeId = node.get("employeeId").asInt();

            // get the List of courseIds
            JsonNode listNode = node.get("courseIds");
            ArrayList<Integer> courseIds = new ArrayList<>();
            for (JsonNode integerNode : listNode) {
                courseIds.add(integerNode.get("id").asInt());
            }
            lecturer = new Lecturer(name, surname, emailAddress, address, birthdate, employeeId);
            lecturer.setCourseIds(courseIds);
        } catch (Exception e) {
            System.err.println("Failed to deserialize Lecturer");
            throw new RuntimeException();
        }
        return lecturer;
    }
}
