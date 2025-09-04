package com.example.expensemanager.domain;

import java.util.List;

public class GetExpensesUseCase {
    private final ExpenseRepository repository;

    public GetExpensesUseCase(ExpenseRepository repository) {
        this.repository = repository;
    }

    public List<Expense> execute() {
        return repository.getAllExpenses();
    }
}
