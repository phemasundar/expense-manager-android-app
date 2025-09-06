package com.example.expensemanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.expensemanager.domain.Expense;
import com.example.expensemanager.domain.ExpenseRepository;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final ExpenseDatabaseHelper dbHelper;

    public ExpenseRepositoryImpl(Context context) {
        this.dbHelper = new ExpenseDatabaseHelper(context);
    }

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
