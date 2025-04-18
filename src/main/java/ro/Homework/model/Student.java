package ro.Homework.model;

import ro.Homework.utility.Gender;
import ro.Homework.utility.StudentValidationException;
import ro.Homework.utility.StudentValidator;
import java.time.LocalDate;
/**
 * To Tess with getter allStudents:
 * Uncomment the method in StudentManager + import
 * Uncomment method in StudentRepository at the Bottom
 */

public class Student {
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String id;

    public Student(String firstName, String lastName, LocalDate dateOfBirth,
                   Gender gender, String id) throws StudentValidationException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;

        StudentValidator.validateStudent(this);
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getId() { return id; }
    public Gender getGender() { return gender; }
}