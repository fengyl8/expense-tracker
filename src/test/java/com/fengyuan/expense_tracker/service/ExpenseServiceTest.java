package com.fengyuan.expense_tracker.service;

import com.fengyuan.expense_tracker.model.Expense;
import com.fengyuan.expense_tracker.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpenseServiceTest {

    private ExpenseService service;
    private List<Expense> expenses;
    private final LocalDate start = LocalDate.of(2026, 8, 24);
    private final LocalDate end = LocalDate.of(2026, 8, 26);

    @BeforeEach
    void setUp() {
        ExpenseRepository repository = mock(ExpenseRepository.class);
        service = new ExpenseService(repository);
        expenses = List.of(
                new Expense(1L, 10, "Before range", "Food", start.minusDays(1)),
                new Expense(2L, 12, "Start boundary", "Food", start),
                new Expense(3L, 45, "Inside range", "Transportation", start.plusDays(1)),
                new Expense(4L, 8, "End boundary", "Food", end),
                new Expense(5L, 15, "After range", "Food", end.plusDays(1)),
                new Expense(6L, 5, "No date", "Food", null),
                new Expense(7L, 6, "Lowercase category", "food", start)
        );
        when(repository.findAll()).thenReturn(expenses);
    }

    @Test
    void noFiltersReturnsAllExpensesIncludingUndatedOnes() {
        assertEquals(expenses, service.getAllExpenses(null, null, null));
    }

    @Test
    void categoryMatchIsCaseSensitive() {
        assertIds(List.of(1L, 2L, 4L, 5L, 6L),
                service.getAllExpenses("Food", null, null));
    }

    @Test
    void dateRangeIncludesBothBoundariesAndExcludesUndatedExpenses() {
        assertIds(List.of(2L, 3L, 4L, 7L),
                service.getAllExpenses(null, start, end));
    }

    @Test
    void categoryAndDateRangeMustBothMatch() {
        assertIds(List.of(2L, 4L), service.getAllExpenses("Food", start, end));
    }

    @Test
    void unknownCategoryReturnsEmptyList() {
        assertEquals(List.of(), service.getAllExpenses("DoesNotExist", null, null));
    }

    @Test
    void dateRangeWithNoMatchesReturnsEmptyList() {
        assertEquals(List.of(), service.getAllExpenses(null, end.plusDays(2), end.plusDays(3)));
    }

    @Test
    void singleDayRangeIncludesThatDay() {
        assertIds(List.of(2L, 7L), service.getAllExpenses(null, start, start));
    }

    @Test
    void fromOnlyAppliesInclusiveLowerBound() {
        assertIds(List.of(4L, 5L), service.getAllExpenses(null, end, null));
    }

    @Test
    void toOnlyAppliesInclusiveUpperBound() {
        assertIds(List.of(1L, 2L, 7L), service.getAllExpenses(null, null, start));
    }

    @Test
    void reversedDateRangeIsRejected() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> service.getAllExpenses(null, end, start));
        assertEquals("from must be on or before to", error.getMessage());
    }

    private void assertIds(List<Long> expected, List<Expense> actual) {
        assertEquals(expected, actual.stream().map(Expense::getId).sorted().toList());
    }
}
