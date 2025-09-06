package com.example.expensemanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.expensemanager.domain.Expense;
import com.example.expensemanager.domain.ExpenseRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLite implementation of the ExpenseRepository interface.
 * This class handles all database operations for expense management including CRUD operations.
 * It uses SQLite database for persistent storage of expense data.
 */
public class ExpenseRepositoryImpl implements ExpenseRepository {

    /** Database helper instance for managing database connections */
    private final ExpenseDatabaseHelper dbHelper;

    /**
     * Constructor for ExpenseRepositoryImpl.
     * Initializes the database helper which will create the database if it doesn't exist.
     *
     * @param context The application context required for database operations
     */
    public ExpenseRepositoryImpl(Context context) {
        this.dbHelper = new ExpenseDatabaseHelper(context);
    }

    /**
     * Retrieves all expenses from the database.
     * Queries the expenses table and returns a list of all stored expenses.
     *
     * @return List of all expenses in the database, empty list if no expenses exist
     */
    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
            ExpenseDatabaseHelper.TABLE_EXPENSES,
            null, null, null, null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ID));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_DESCRIPTION));
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_AMOUNT));
                String category = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_CATEGORY));
                long date = cursor.getLong(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_DATE));

                expenses.add(new Expense(id, description, amount, category, date));
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return expenses;
    }

    /**
     * Retrieves a specific expense by its ID.
     * Searches the database for an expense with the given ID.
     *
     * @param id The unique identifier of the expense to retrieve
     * @return The expense with the specified ID, or null if not found
     */
    @Override
    public Expense getExpenseById(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Expense expense = null;

        Cursor cursor = db.query(
            ExpenseDatabaseHelper.TABLE_EXPENSES,
            null,
            ExpenseDatabaseHelper.COLUMN_ID + " = ?",
            new String[]{id},
            null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String description = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_DESCRIPTION));
            double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_AMOUNT));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_CATEGORY));
            long date = cursor.getLong(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_DATE));

            expense = new Expense(id, description, amount, category, date);
            cursor.close();
        }

        db.close();
        return expense;
    }

    /**
     * Adds a new expense to the database.
     * Inserts the expense data into the expenses table.
     *
     * @param expense The expense object to be added to the database
     */
    @Override
    public void addExpense(Expense expense) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExpenseDatabaseHelper.COLUMN_ID, expense.getId());
        values.put(ExpenseDatabaseHelper.COLUMN_DESCRIPTION, expense.getDescription());
        values.put(ExpenseDatabaseHelper.COLUMN_AMOUNT, expense.getAmount());
        values.put(ExpenseDatabaseHelper.COLUMN_CATEGORY, expense.getCategory());
        values.put(ExpenseDatabaseHelper.COLUMN_DATE, expense.getDate());

        db.insert(ExpenseDatabaseHelper.TABLE_EXPENSES, null, values);
        db.close();
    }

    /**
     * Updates an existing expense in the database.
     * Finds the expense by ID and updates all its fields with the new values.
     *
     * @param expense The expense object with updated information
     */
    @Override
    public void updateExpense(Expense expense) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExpenseDatabaseHelper.COLUMN_DESCRIPTION, expense.getDescription());
        values.put(ExpenseDatabaseHelper.COLUMN_AMOUNT, expense.getAmount());
        values.put(ExpenseDatabaseHelper.COLUMN_CATEGORY, expense.getCategory());
        values.put(ExpenseDatabaseHelper.COLUMN_DATE, expense.getDate());

        db.update(
            ExpenseDatabaseHelper.TABLE_EXPENSES,
            values,
            ExpenseDatabaseHelper.COLUMN_ID + " = ?",
            new String[]{expense.getId()}
        );
        db.close();
    }

    /**
     * Deletes an expense from the database.
     * Removes the expense with the specified ID from the expenses table.
     *
     * @param id The unique identifier of the expense to delete
     */
    @Override
    public void deleteExpense(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(
            ExpenseDatabaseHelper.TABLE_EXPENSES,
            ExpenseDatabaseHelper.COLUMN_ID + " = ?",
            new String[]{id}
        );
        db.close();
    }
}
