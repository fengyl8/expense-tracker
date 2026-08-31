package com.fengyuan.expense_tracker.config;

import com.fengyuan.expense_tracker.model.Expense;
import com.fengyuan.expense_tracker.repository.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedExpenses(ExpenseRepository expenseRepository) {
        return args -> {
            if (expenseRepository.count() == 0) {
                expenseRepository.saveAll(List.of(
                        new Expense(
                                null,
                                12.50,
                                "Lunch",
                                "Food",
                                LocalDate.of(2026, 8, 24)
                        ),
                        new Expense(
                                null,
                                45.00,
                                "Gas",
                                "Transportation",
                                LocalDate.of(2026, 8, 25)
                        ),
                        new Expense(
                                null,
                                8.75,
                                "Coffee",
                                "Food",
                                LocalDate.of(2026, 8, 26)
                        )
                ));
            }
        };
    }
}