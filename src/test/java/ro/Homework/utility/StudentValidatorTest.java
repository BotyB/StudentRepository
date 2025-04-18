package ro.Homework.utility;

import org.junit.jupiter.api.Test;
import ro.Homework.model.Student;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class StudentValidatorTest {

    @Test
    void validStudentHappyPathTest() {
        assertDoesNotThrow(() -> {
            Student student = new Student("John", "Doe", LocalDate.of(1999, 5, 20), Gender.MALE, "1234567890123");
            StudentValidator.validateStudent(student);
        });
    }
    //Negative Tests below
    @Test
    void emptyFirstNameTest() {
        StudentValidationException exception = assertThrows(StudentValidationException.class, () -> new Student("", "Doe", LocalDate.of(2000, 1, 1), Gender.MALE, "1234567890123"));
        assertEquals("First name cannot be empty", exception.getMessage());
    }

    @Test
    void emptyLastNameTest() {
        StudentValidationException exception = assertThrows(StudentValidationException.class, () -> new Student("Jane", " ", LocalDate.of(2000, 1, 1), Gender.FEMALE, "1234567890123"));
        assertEquals("Last name cannot be empty", exception.getMessage());
    }

    @Test
    void nullDateOfBirthTest() {
        StudentValidationException exception = assertThrows(StudentValidationException.class, () -> new Student("Jane", "Doe", null, Gender.FEMALE, "1234567890123"));
        assertEquals("Date of birth cannot be null", exception.getMessage());
    }

    @Test
    void invalidBirthYearTest() {
        int tooYoungYear = LocalDate.now().getYear() - 17;
        StudentValidationException exception = assertThrows(StudentValidationException.class, () -> new Student("Jane", "Doe", LocalDate.of(tooYoungYear, 1, 1), Gender.FEMALE, "1234567890123"));
        assertTrue(exception.getMessage().contains("Date of birth must be between 1900"));
    }

    @Test
    void invalidCnpLengthTest() {
        StudentValidationException exception = assertThrows(StudentValidationException.class, () -> new Student("Jane", "Doe", LocalDate.of(1990, 1, 1), Gender.FEMALE, "12345"));
        assertEquals("CNP must be 13 characters", exception.getMessage());
    }

    @Test
    void nullCnpTest() {
        StudentValidationException exception = assertThrows(StudentValidationException.class, () -> new Student("Jane", "Doe", LocalDate.of(1990, 1, 1), Gender.FEMALE, null));
        assertEquals("CNP cannot be empty", exception.getMessage());
    }

    @Test
    void emptyCnpTest() {
        StudentValidationException exception = assertThrows(StudentValidationException.class, () -> new Student("Jane", "Doe", LocalDate.of(1990, 1, 1), Gender.FEMALE, "   "));
        assertEquals("CNP cannot be empty", exception.getMessage());
    }
}