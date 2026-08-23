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
        expense.setId(nextId);
        nextId++;

        expenses.add(expense);
        return expense;
    }

    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }
}
