package ro.Homework.controller;

import ro.Homework.data.StudentRepository;
import ro.Homework.model.Student;
import ro.Homework.utility.StudentValidationException;
import java.util.List;
import java.util.Collection; //--> Uncomment for testing purposes DO NOT DELETE!
// Also uncomment the method at the bottom of this class
// And the import statement in StudentRepository.java

public class StudentManager {
    private final StudentRepository repository = new StudentRepository();

    public void addStudent(Student student) throws StudentValidationException {
        repository.addStudent(student);
    }

    public void deleteStudent(String cnp) throws StudentValidationException {
        repository.deleteStudent(cnp);
    }

    public List<Student> retrieveStudentsByAge(int age) throws StudentValidationException {
        return repository.retrieveStudentsByAge(age);
    }

    public List<Student> listStudentsSorted(String sortBy) throws StudentValidationException {
        return repository.listStudentsSorted(sortBy);
    }

    public Collection<Student> getAllStudents() { //--> Getter added for testing purposes
        return repository.getAllStudents();
    }
}