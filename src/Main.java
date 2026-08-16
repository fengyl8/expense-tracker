//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Expense expense1 = new Expense(12.50, "Lunch", "Food");
        Expense expense2 = new Expense(2.75, "Metro", "Travel");
        Expense expense3 = new Expense(18.00, "Book", "Learning");

        Expense[] expenses = {expense1, expense2, expense3};

        double total = 0;

        for (Expense expense : expenses) {
            expense.printExpense();
            total += expense.getAmount();
        }

        System.out.printf("TOTAL: %.2f%n", total);
    }
}