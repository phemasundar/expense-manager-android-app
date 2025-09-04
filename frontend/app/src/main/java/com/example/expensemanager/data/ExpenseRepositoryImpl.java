package com.example.expensemanager.data;

import com.example.expensemanager.domain.Expense;
import com.example.expensemanager.domain.ExpenseRepository;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final List<Expense> expenses = new ArrayList<>();

    @Override
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }

    @Override
    public Expense getExpenseById(String id) {
        return expenses.stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        expenses.removeIf(e -> e.getId().equals(expense.getId()));
        expenses.add(expense);
    }

    @Override
    public void deleteExpense(String id) {
        expenses.removeIf(expense -> expense.getId().equals(id));
    }
}
