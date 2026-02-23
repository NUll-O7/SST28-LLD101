public class AttendanceRule implements EligibilityRule {
    private final int threshold;

    public AttendanceRule(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String evaluate(StudentProfile s) {
        if (s.attendancePct < threshold) {
            return "attendance below " + threshold;
        }
        return null;
    }
}
