package com.example.expensemanager.domain;

import java.util.List;

public interface ExpenseRepository {
    List<Expense> getAllExpenses();
    Expense getExpenseById(String id);
    void addExpense(Expense expense);
    void updateExpense(Expense expense);
    void deleteExpense(String id);
}
