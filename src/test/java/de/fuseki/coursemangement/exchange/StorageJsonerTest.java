package de.fuseki.coursemangement.exchange;

import de.fuseki.coursemangement.pojos.Address;
import de.fuseki.coursemangement.pojos.Course;
import de.fuseki.coursemangement.pojos.Lecturer;
import de.fuseki.coursemangement.pojos.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StorageJsonerTest {
    private Address addressLenze;
    private Address adressmueller;
    private Address fachbereichInformatik;
    private Address addressNeusta;
    private LocalDate periodBegin;
    private LocalDate periodEnd;
    private LocalDate birthLenze;
    private LocalDate birthMueller;
    private LocalDate birthYusuf;
    private Lecturer lenze;
    private Lecturer mueller;
    private Course einfuehrungInDieProgrammierung;
    private Course matheFuerInformatik;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Lecturer> lecturers;
    private Storage storage;
    private StorageJsoner storageJsoner;

    @BeforeEach
    public void setUp() {
        addressLenze = new Address("Schürrmannstraße", "32", "Essen", "45136");
        adressmueller = new Address("Friedenrikenstraße", "22a", "Essen", "45130");
        fachbereichInformatik = new Address("Emil-Figge Str.", "42", "Dortmund", "44227");
        addressNeusta = new Address("Schürrmannstraße", "32", "Essen", "45136");

        periodBegin = LocalDate.of(2023, 9, 1);
        periodEnd = LocalDate.of(2024, 2, 29);
        birthLenze = LocalDate.of(1989, 4, 14);
        birthMueller = LocalDate.of(1980, 11, 8);
        birthYusuf = LocalDate.of(2003, 12, 28);

        lenze = new Lecturer("Harald", "Lenze", "lenze@fh-dortmund.de", addressLenze, birthLenze, 2);
        mueller = new Lecturer("Peter", "Mueller", "Mueller@fh-dortmund.de", adressmueller, birthMueller, 1);

        einfuehrungInDieProgrammierung = new Course(1, "Einführung in die Programmierung", "grundelemente von Java", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);
        matheFuerInformatik = new Course(2, "Mathe für Unformatik", "Grundlagen der Mathematik", lenze.getEmployeeId(), fachbereichInformatik, periodBegin, periodEnd);

        students = new ArrayList<>();
        students.add(new Student("Yusuf", "Kurt", "ykurt@neusta-sd-west.de", addressNeusta, birthYusuf, 7218115));

        lenze.addCourse(einfuehrungInDieProgrammierung);
        lenze.addCourse(matheFuerInformatik);

        students.get(0).addCourse(einfuehrungInDieProgrammierung);
        students.get(0).addCourse(matheFuerInformatik);

        storage = new Storage();

        courses = new ArrayList<>();
        courses.add(einfuehrungInDieProgrammierung);
        courses.add(matheFuerInformatik);
        lecturers = new ArrayList<>();
        lecturers.add(lenze);
        lecturers.add(mueller);

        storage.setLecturers(lecturers);
        storage.setStudents(students);
        storage.setAllCourses(courses);
        storageJsoner = new StorageJsoner();
    }

    @Test
    public void testExportStorage() {
        storageJsoner.exportStorage("src/test/java/de/fuseki/coursemangement/exchange/testedStorageFile.storageJsoner", storage);
        String expected = "{\"allCourses\":[{\"id\":1,\"title\":\"Einführung in die Programmierung\",\"description\":\"grundelemente von Java\",\"lecturerId\":2,\"address\":{\"street\":\"Emil-Figge Str.\",\"houseNumber\":\"42\",\"city\":\"Dortmund\",\"postalCode\":\"44227\"},\"dateBegin\":\"2023-09-01\",\"dateEnd\":\"2024-02-29\"},{\"id\":2,\"title\":\"Mathe für Unformatik\",\"description\":\"Grundlagen der Mathematik\",\"lecturerId\":2,\"address\":{\"street\":\"Emil-Figge Str.\",\"houseNumber\":\"42\",\"city\":\"Dortmund\",\"postalCode\":\"44227\"},\"dateBegin\":\"2023-09-01\",\"dateEnd\":\"2024-02-29\"}],\"lecturers\":[{\"name\":\"Harald\",\"surname\":\"Lenze\",\"emailAddress\":\"lenze@fh-dortmund.de\",\"address\":{\"street\":\"Schürrmannstraße\",\"houseNumber\":\"32\",\"city\":\"Essen\",\"postalCode\":\"45136\"},\"birthdate\":\"1989-04-14\",\"employeeId\":2,\"courseIds\":[{\"id\":1},{\"id\":2}]},{\"name\":\"Peter\",\"surname\":\"Mueller\",\"emailAddress\":\"Mueller@fh-dortmund.de\",\"address\":{\"street\":\"Friedenrikenstraße\",\"houseNumber\":\"22a\",\"city\":\"Essen\",\"postalCode\":\"45130\"},\"birthdate\":\"1980-11-08\",\"employeeId\":1,\"courseIds\":[]}],\"students\":[{\"name\":\"Yusuf\",\"surname\":\"Kurt\",\"emailAddress\":\"ykurt@neusta-sd-west.de\",\"address\":{\"street\":\"Schürrmannstraße\",\"houseNumber\":\"32\",\"city\":\"Essen\",\"postalCode\":\"45136\"},\"birthdate\":\"2003-12-28\",\"matriculationNumber\":7218115,\"courseIds\":[{\"id\":1},{\"id\":2}]}]}";
        String testString = "";
        try {
            testString = new BufferedReader(new FileReader(new File("src/test/java/de/fuseki/coursemangement/exchange/testedStorageFile.storageJsoner"))).readLine().toString();
        } catch (Exception e) {
            fail();
        }

        // Assert
        assertEquals(expected, testString);
    }

    @Test
    public void testImportStorage(){
        Storage testStorage = storageJsoner.updateStorage("src/test/java/de/fuseki/coursemangement/exchange/testedStorageFile.json");

        assertEquals(storage,testStorage);
    }
}