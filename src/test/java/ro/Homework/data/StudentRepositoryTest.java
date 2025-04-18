package ro.Homework.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.Homework.model.Student;
import ro.Homework.utility.Gender;
import ro.Homework.utility.StudentValidationException;
import java.time.LocalDate;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private StudentRepository repository;
    private Student s1;
    private Student s2;

    @BeforeEach
    void setUp() {
        repository = new StudentRepository();
        try {
            s1 = new Student("Ana", "Pop", LocalDate.of(1995, 1, 1), Gender.FEMALE, "1234567890123");
            s2 = new Student("Ion", "Ionescu", LocalDate.of(1990, 2, 2), Gender.MALE, "9876543210987");
        } catch (StudentValidationException e) {
            throw new RuntimeException("Invalid test data", e);
        }
    }

    @Test
    void addStudentToMapTest() throws StudentValidationException {
        repository.addStudent(s1);
        Collection<Student> all = repository.getAllStudents();
        assertEquals(1, all.size());
        assertTrue(all.contains(s1));
    }

    @Test
    void addDuplicateCnpTest() throws StudentValidationException {
        repository.addStudent(s1);
        Student duplicate = new Student("Fake", "Duplicate", LocalDate.of(2000, 3, 3), Gender.FEMALE, "1234567890123");
        StudentValidationException ex = assertThrows(StudentValidationException.class, () -> repository.addStudent(duplicate));
        assertEquals("Student with CNP 1234567890123 already exists", ex.getMessage());
    }

    @Test
    void addingAndDeletingStudentsTest() throws StudentValidationException {
        repository.addStudent(s1);
        repository.deleteStudent(s1.getId());
        assertEquals(0, repository.getAllStudents().size());
    }

    @Test
    void deletingNonExistentTest() {
        StudentValidationException ex = assertThrows(StudentValidationException.class,
                () -> repository.deleteStudent("0000000000000"));
        assertEquals("Student not found: 0000000000000", ex.getMessage());
    }

    @Test
    void getAllStudentsSortedTest() throws StudentValidationException {
        repository.addStudent(s1);
        repository.addStudent(s2);
        Collection<Student> all = repository.getAllStudents();

        assertEquals(2, all.size(), "Should return exactly 2 students");
        assertTrue(all.contains(s1), "Should contain student s1");
        assertTrue(all.contains(s2), "Should contain student s2");

        all.forEach(student -> System.out.println("Listed: " + student));
    }
}
