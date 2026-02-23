public interface EligibilityRule {
    // Returns a failure reason if the student violates this rule, or null/empty if passed.
    // Alternatively, return a Result object.
    // Let's return String (reason) or null.
    String evaluate(StudentProfile student);
}
