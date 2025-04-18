package ro.Homework;

import ro.Homework.controller.StudentManager;
import ro.Homework.model.Student;
import ro.Homework.utility.Gender;
import ro.Homework.utility.StudentValidationException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentApp {
    private static final Logger logger = Logger.getLogger(StudentApp.class.getName());
    public static void main(String[] args) {
        configureLogger();
        try (Scanner scanner = new Scanner(System.in)) {
            StudentManager manager = new StudentManager();
            while (true) {
                System.out.print("\nEnter command (add, delete, retrieve, list, exit): ");
                String cmd = scanner.next().trim().toLowerCase();
                switch (cmd) {
                    case "add"       -> handleAdd(manager, scanner);
                    case "delete"    -> handleDelete(manager, scanner);
                    case "retrieve"  -> handleRetrieve(manager, scanner);
                    case "list"      -> handleList(manager, scanner);
                    case "exit"      -> {
                        logger.info("Exiting Application.");
                        return;
                    }
                    default -> System.out.println("Unknown command. Try: add, delete, retrieve, list, exit");
                }
            }
        }
    }

    private static void configureLogger() {
        Logger.getLogger("").setLevel(Level.INFO);
    }

    private static void handleAdd(StudentManager mgr, Scanner sc) {
        try {
            System.out.print("First name: ");       String first = sc.next();
            System.out.print("Last name: ");        String last  = sc.next();
            System.out.print("Birthdate (YYYY-MM-DD): ");
            LocalDate dob = LocalDate.parse(sc.next());
            System.out.print("Gender (M/F): ");     Gender g = Gender.fromString(sc.next());
            System.out.print("CNP (13 digits): "); String cnp    = sc.next();

            Student s = new Student(first, last, dob, g, cnp);
            mgr.addStudent(s);
            System.out.println("== Student Added ==");
        } catch (DateTimeParseException e) {
            System.out.println("== Invalid date format ==");
        } catch (StudentValidationException e) {
            System.out.println("! " + e.getMessage());
        }
    }

    private static void handleDelete(StudentManager mgr, Scanner sc) {
        System.out.print("CNP to delete: ");
        String cnp = sc.next();
        try {
            mgr.deleteStudent(cnp);
            System.out.println("== Student deleted ==");
        } catch (StudentValidationException e) {
            System.out.println("! " + e.getMessage());
        }
    }

    private static void handleRetrieve(StudentManager mgr, Scanner sc) {
        System.out.print("Age to retrieve: ");
        String ageStr = sc.next();
        try {
            int age = Integer.parseInt(ageStr);
            List<Student> list = mgr.retrieveStudentsByAge(age);
            list.forEach(s -> System.out.printf("  • %s %s (CNP: %s)%n",
                    s.getFirstName(), s.getLastName(), s.getId()));
        } catch (NumberFormatException e) {
            System.out.println("! Age must be a number.");
        } catch (StudentValidationException e) {
            System.out.println("! " + e.getMessage());
        }
    }

    private static void handleList(StudentManager mgr, Scanner sc) {
        System.out.print("Sort by (lastName / birthDate): ");
        String criteria = sc.next();
        try {
            List<Student> list = mgr.listStudentsSorted(criteria);
            list.forEach(s -> System.out.printf("  • %s, %s (DOB: %s)%n",
                    s.getLastName(), s.getFirstName(), s.getDateOfBirth()));
        } catch (StudentValidationException e) {
            System.out.println("! " + e.getMessage());
        }
    }
}