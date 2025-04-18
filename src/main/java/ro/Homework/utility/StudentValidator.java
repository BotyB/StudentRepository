package ro.Homework.utility;

import java.time.LocalDate;
import ro.Homework.model.Student;
import java.util.logging.Logger;

public final class StudentValidator {
    private static final Logger logger = Logger.getLogger(StudentValidator.class.getName());

    public static void validateStudent(Student student) throws StudentValidationException {
        validateFirstName(student.getFirstName());
        validateLastName(student.getLastName());
        validateDateOfBirth(student.getDateOfBirth());
        validateCNP(student.getId());
    }

    private static void validateFirstName(String firstName) throws StudentValidationException {
        if (firstName == null || firstName.trim().isEmpty()) {
            logger.warning("Validation failed: Empty first name");
            throw new StudentValidationException("First name cannot be empty");
        }
    }

    private static void validateLastName(String lastName) throws StudentValidationException {
        if (lastName == null || lastName.trim().isEmpty()) {
            logger.warning("Validation failed: Empty last name");
            throw new StudentValidationException("Last name cannot be empty");
        }
    }

    private static void validateDateOfBirth(LocalDate dateOfBirth) throws StudentValidationException {
        if (dateOfBirth == null) {
            logger.warning("Validation failed: Null birth date");
            throw new StudentValidationException("Date of birth cannot be null");
        }

        int currentYear = LocalDate.now().getYear();
        if (dateOfBirth.getYear() < 1900 || dateOfBirth.getYear() > currentYear - 18) {
            logger.warning("Validation failed: Invalid birth year - " + dateOfBirth.getYear());
            throw new StudentValidationException(
                    "Date of birth must be between 1900 and " + (currentYear - 18)
            );
        }
    }

    private static void validateCNP(String cnp) throws StudentValidationException {
        if (cnp == null || cnp.trim().isEmpty()) {
            logger.warning("Validation failed: Empty CNP");
            throw new StudentValidationException("CNP cannot be empty");
        }
        if (cnp.length() != 13) {
            logger.warning("Validation failed: Invalid CNP length - " + cnp.length());
            throw new StudentValidationException("CNP must be 13 characters");
        }
    }
}