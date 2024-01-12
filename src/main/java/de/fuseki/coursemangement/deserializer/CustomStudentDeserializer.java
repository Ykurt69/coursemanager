package de.fuseki.coursemangement.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomStudentDeserializer extends StdDeserializer<Student> {
        public CustomStudentDeserializer(){
            this(null);
        }
        public CustomStudentDeserializer(Class<?> vc){
            super(vc);
        }

        public Student deserialize(JsonParser parser , DeserializationContext deserializer){
            ObjectCodec codec = parser.getCodec();
            Student student;
            try {
                JsonNode node = codec.readTree(parser);
                String name = node.get("name").asText();
                String surname = node.get("surname").asText();
                String emailAddress = node.get("emailAddress").asText();
                JsonNode addressAsNode = node.get("address");
                int matriculationNumber = node.get("matriculationNumber").asInt();

                // Create Address
                ObjectMapper objectMapper = new ObjectMapper();
                Address address = objectMapper.treeToValue(addressAsNode, Address.class);

                // Create Date
                LocalDate birthdate = LocalDate.parse(node.get("birthdate").asText());

                // get the List of courseIds
                JsonNode listNode = node.get("courseIds");
                ArrayList<Integer> courseIds = new ArrayList<>();
                for (JsonNode integerNode : listNode){
                    courseIds.add(integerNode.get("id").asInt());
                }

                student = new Student(name,surname,emailAddress,address,birthdate,matriculationNumber);
                student.setCourseIds(courseIds);

            } catch (IOException e) {
                System.err.println("Failed to Deserialize Student.");
                throw new RuntimeException(e);
            }
            return student;
        }
}
