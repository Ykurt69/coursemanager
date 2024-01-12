package de.fuseki.coursemangement.exchange;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fuseki.coursemangement.deserializer.CustomStudentDeserializer;
import de.fuseki.coursemangement.serializer.CustomCourseSerializer;
import de.fuseki.coursemangement.deserializer.CustomCourseDeserializer;
import de.fuseki.coursemangement.deserializer.CustomLecturerDeserializer;
import de.fuseki.coursemangement.pojos.Course;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.pojos.Student;
import de.fuseki.coursemangement.serializer.CustomLecturerSerializer;
import de.fuseki.coursemangement.serializer.CustomStudentSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StorageJsoner {

    /**
     * Adds all needed Serializer to the ObjectMapper and returns it.
     *
     * @return a Mapper which is configured to export the A Storage.
     */
    private ObjectMapper getExportMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule exportModule = new SimpleModule("CustomLecturerSerializer", new Version(1, 0, 0, null, null, null));
        exportModule.addSerializer(Lecturer.class, new CustomLecturerSerializer());
        exportModule.addSerializer(Student.class, new CustomStudentSerializer());
        exportModule.addSerializer(Course.class, new CustomCourseSerializer());
        mapper.registerModule(exportModule);
        return mapper;
    }

    /**
     * Exports the storage into a File in the path.
     *
     * @param path    of the File.
     * @param storage of the exported Storage.
     */
    public void exportStorage(String path, Storage storage) {
        ObjectMapper mapper = getExportMapper();
        try {
            File jsonFile = new File(path);
            FileWriter fileWriter = new FileWriter(jsonFile);
            mapper.writeValue(fileWriter, storage);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Can´t find the storage file.");
        }
    }

    /**
     * Instances a ObjectMapper and adds the Deserializer to it.
     * @return the configured Mapper.
     */
    private ObjectMapper getImportMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule deserializerModule = new SimpleModule();
        deserializerModule.addDeserializer(Course.class, new CustomCourseDeserializer());
        deserializerModule.addDeserializer(Lecturer.class, new CustomLecturerDeserializer());
        deserializerModule.addDeserializer(Student.class, new CustomStudentDeserializer());
        mapper.registerModule(deserializerModule);
        return mapper;
    }

    /**
     * Imports the Storage of the file in the path.
     *
     * @param path of the imported file.
     * @return an instance from the Storage of the file.
     */
    public Storage updateStorage(String path) {
        // Creating ObjectMapper and Adding the Serializer Modules into it.
        ObjectMapper mapper = getImportMapper();

        // Importing the Storage
        File storageFile = new File(path);
        Storage storage = null;
        try {
            storage = mapper.readValue(storageFile, Storage.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return storage;
    }

}
