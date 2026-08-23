package com.fengyuan.expense_tracker.model;

import java.time.LocalDate;

public class Expense {

    private Long id;
    private double amount;
    private String description;
    private String category;
    private LocalDate date;

    public Expense() {
    }

    public Expense(
            Long id,
            double amount,
            String description,
            String category,
            LocalDate date
    ) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
