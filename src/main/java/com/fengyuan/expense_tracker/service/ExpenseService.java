package com.fengyuan.expense_tracker.service;

import com.fengyuan.expense_tracker.exception.ExpenseNotFoundException;
import com.fengyuan.expense_tracker.model.Expense;
import com.fengyuan.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        validateExpense(expense);

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(String category, LocalDate from, LocalDate to) {
        if (from != null && to != null && from.isAfter(to)) {
            throw new IllegalArgumentException("from must be on or before to");
        }

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .filter(expense -> category == null || category.equals(expense.getCategory()))
                .filter(expense -> from == null || (expense.getDate() != null
                        && !expense.getDate().isBefore(from)))
                .filter(expense -> to == null || (expense.getDate() != null
                        && !expense.getDate().isAfter(to)))
                .toList();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        validateExpense(updatedExpense);

        Expense existingExpense = getExpenseById(id);
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    private void validateExpense(Expense expense) {
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
    }
}
