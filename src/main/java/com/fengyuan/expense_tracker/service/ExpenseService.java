package com.fengyuan.expense_tracker.service;

import com.fengyuan.expense_tracker.exception.ExpenseNotFoundException;
import com.fengyuan.expense_tracker.model.Expense;
import com.fengyuan.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

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

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
    }
}
