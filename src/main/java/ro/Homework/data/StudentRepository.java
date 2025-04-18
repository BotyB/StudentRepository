package ro.Homework.data;

import ro.Homework.model.Student;
import ro.Homework.utility.AgeCalculator;
import ro.Homework.utility.StudentValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;
// For Testing getter for allStudents please uncomment
// the method at the bottom and the import statement in StudentManager.java

public class StudentRepository {
    private final Map<String, Student> students = new HashMap<>();
    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());

    public void addStudent(Student student) throws StudentValidationException {
        if (students.containsKey(student.getId())) {
            logger.warning("Duplicate CNP attempt: " + student.getId());
            throw new StudentValidationException("Student with CNP " + student.getId() + " already exists");
        }
        students.put(student.getId(), student);
        logger.info("Added student: " + student.getId());
    }

    public void deleteStudent(String cnp) throws StudentValidationException {
        if (cnp == null || cnp.isEmpty()) {
            logger.warning("Delete attempt with empty CNP");
            throw new StudentValidationException("CNP cannot be empty");
        }
        if (!students.containsKey(cnp)) {
            logger.warning("Delete attempt for non-existent CNP: " + cnp);
            throw new StudentValidationException("Student not found: " + cnp);
        }
        students.remove(cnp);
        logger.info("Deleted student: " + cnp);
    }

    public List<Student> retrieveStudentsByAge(int age) throws StudentValidationException {
        if (age < 0) {
            logger.warning("Negative age query: " + age);
            throw new StudentValidationException("Age cannot be negative");
        }

        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (AgeCalculator.calculateAge(student.getDateOfBirth()) == age) {
                result.add(student);
            }
        }
        logger.info("Age query for " + age + " returned " + result.size() + " students");
        return result;
    }

    public List<Student> listStudentsSorted(String sortBy) throws StudentValidationException {
        if (sortBy == null || sortBy.isEmpty()) {
            logger.warning("Empty sort criteria attempt");
            throw new StudentValidationException("Sort criteria cannot be empty");
        }

        List<Student> studentList = new ArrayList<>(students.values());

        switch (sortBy.toLowerCase()) {
            case "lastname":
                studentList.sort(Comparator.comparing(Student::getLastName));
                break;
            case "birthdate":
                studentList.sort(Comparator.comparing(Student::getDateOfBirth));
                break;
            default:
                logger.warning("Invalid sort criteria: " + sortBy);
                throw new StudentValidationException("Invalid sort option. Use 'lastName' or 'birthDate'");
        }
        logger.info("Sorted students by: " + sortBy);
        return studentList;
    }

   public Collection<Student> getAllStudents() { //--> Getter added for testing purposes
   return Collections.unmodifiableCollection(students.values()); //--> Uncomment for testing purposes
   }
}