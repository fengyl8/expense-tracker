public class Expense {
    private double amount;
    private String description;
    private String category;

    public Expense(double amount, String description, String category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void printExpense() {
        System.out.printf(
                "%s | %s | %.2f%n",
                description,
                category,
                amount
        );
    }
}