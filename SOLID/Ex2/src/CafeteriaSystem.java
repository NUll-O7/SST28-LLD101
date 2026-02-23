import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository store;
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceRepository store, TaxPolicy taxPolicy, DiscountPolicy discountPolicy, InvoiceFormatter formatter) {
        this.store = store;
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.formatter = formatter;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    // Orchestrates the billing process
    public void checkout(String customerType, List<OrderLine> lines) {
        // 1. Prepare Invoice Data
        String invId = "INV-" + (++invoiceSeq);
        Invoice invoice = new Invoice(invId);

        // 2. Calculate Subtotal
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoice.addLine(new InvoiceLine(item.name, l.qty, item.price, lineTotal));
        }
        invoice.subtotal = subtotal;

        // 3. Apply Tax
        double taxPct = taxPolicy.getTaxRate(customerType);
        double tax = subtotal * (taxPct / 100.0);
        invoice.taxPercent = taxPct;
        invoice.taxAmount = tax;

        // 4. Apply Discount
        double discount = discountPolicy.calculateDiscount(customerType, subtotal, lines.size());
        invoice.discountAmount = discount;

        // 5. Final Total
        double total = subtotal + tax - discount;
        invoice.total = total;

        // 6. Format
        String printable = formatter.format(invoice);
        System.out.print(printable);

        // 7. Persist
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
