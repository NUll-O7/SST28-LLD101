public class InvoiceLine {
    public final String itemName;
    public final int qty;
    public final double itemPrice;
    public final double total;

    public InvoiceLine(String itemName, int qty, double itemPrice, double total) {
        this.itemName = itemName;
        this.qty = qty;
        this.itemPrice = itemPrice;
        this.total = total;
    }
}
