package com.example.expensemanager.domain;

import java.util.List;

/**
 * Repository interface for expense data operations.
 * This interface defines the contract for accessing and manipulating expense data,
 * providing a clean separation between the domain layer and data access layer.
 */
public interface ExpenseRepository {

    /**
     * Retrieves all expenses from the data source.
     *
     * @return List of all expenses, empty list if no expenses exist
     */
    List<Expense> getAllExpenses();

    /**
     * Retrieves a specific expense by its unique identifier.
     *
     * @param id The unique identifier of the expense to retrieve
     * @return The expense with the specified ID, or null if not found
     */
    Expense getExpenseById(String id);

    /**
     * Adds a new expense to the data source.
     *
     * @param expense The expense to be added
     */
    void addExpense(Expense expense);

    /**
     * Updates an existing expense in the data source.
     *
     * @param expense The expense with updated information
     */
    void updateExpense(Expense expense);

    /**
     * Deletes an expense from the data source.
     *
     * @param id The unique identifier of the expense to delete
     */
    void deleteExpense(String id);
}
