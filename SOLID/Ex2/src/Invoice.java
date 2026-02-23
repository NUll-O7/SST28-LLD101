import java.util.ArrayList;
import java.util.List;

public class Invoice {
    public final String id;
    public final List<InvoiceLine> lines = new ArrayList<>();
    public double subtotal;
    public double taxPercent;
    public double taxAmount;
    public double discountAmount;
    public double total;

    public Invoice(String id) {
        this.id = id;
    }

    public void addLine(InvoiceLine line) {
        lines.add(line);
    }
}
