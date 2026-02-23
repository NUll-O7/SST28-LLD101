import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final ConsolePrinter printer;

    public OnboardingService(StudentRepository db, ConsolePrinter printer) {
        this.db = db;
        this.printer = printer;
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printRawInput(raw);

        StudentParser parser = new StudentParser();
        StudentInput parsed = parser.parse(raw);

        String name = parsed.name;
        String email = parsed.email;
        String phone = parsed.phone;
        String program = parsed.program;

        // validation inline, printing inline
        StudentValidator validator = new StudentValidator();
        List<String> errors = validator.validate(parsed);

        if (!errors.isEmpty()) {
            printer.printValidationErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        printer.printConfirmation(rec, db.count());
    }
}
