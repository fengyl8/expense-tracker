package com.fengyuan.expense_tracker.service;

import com.fengyuan.expense_tracker.model.Expense;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private final List<Expense> expenses = new ArrayList<>();
    private long nextId = 1;

    public Expense createExpense(Expense expense) {
        if (expense.getAmount() <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0"
            );
        }

        if (expense.getDescription() == null
                || expense.getDescription().isBlank()) {
            throw new IllegalArgumentException(
                    "Description must not be blank"
            );
        }

        expense.setId(nextId);
        nextId++;

        expenses.add(expense);
        return expense;
    }

    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }
}
