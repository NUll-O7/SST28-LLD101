import java.util.List;

public class ConsolePrinter {
    public void printRawInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printValidationErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) {
            System.out.println("- " + e);
        }
    }

    public void printConfirmation(StudentRecord student, int totalStudents) {
        System.out.println("OK: created student " + student.id);
        System.out.println("Saved. Total students: " + totalStudents);
        System.out.println("CONFIRMATION:");
        System.out.println(student);
    }
}
