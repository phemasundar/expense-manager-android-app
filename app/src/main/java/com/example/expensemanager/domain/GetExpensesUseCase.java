package com.example.expensemanager.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Use case for retrieving all expenses from the repository.
 * This class encapsulates the business logic for fetching expense data
 * and acts as an intermediary between the presentation layer and data layer.
 *
 * Uses Lombok annotations to reduce boilerplate code:
 * - @AllArgsConstructor: Generates constructor with all fields
 * - @Getter: Generates getter for the repository field
 */
@AllArgsConstructor
@Getter
public class GetExpensesUseCase {

    /** The repository instance for accessing expense data */
    private final ExpenseRepository repository;

    /**
     * Executes the use case to retrieve all expenses.
     * This method calls the repository to fetch all stored expenses.
     *
     * @return List of all expenses in the system
     */
    public List<Expense> execute() {
        return repository.getAllExpenses();
    }
}
