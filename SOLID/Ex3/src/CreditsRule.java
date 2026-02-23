public class CreditsRule implements EligibilityRule {
    private final int threshold;

    public CreditsRule(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String evaluate(StudentProfile s) {
        if (s.earnedCredits < threshold) {
            return "credits below " + threshold;
        }
        return null;
    }
}
