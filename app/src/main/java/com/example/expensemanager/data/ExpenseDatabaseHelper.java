package com.example.expensemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite database helper class for managing the expense manager database.
 * This class handles database creation, upgrades, and provides constants for table and column names.
 *
 * Database Schema:
 * - expenses table with columns: id (TEXT PRIMARY KEY), description (TEXT), amount (REAL), category (TEXT), date (INTEGER)
 */
public class ExpenseDatabaseHelper extends SQLiteOpenHelper {

    /** Database name constant */
    private static final String DATABASE_NAME = "expense_manager.db";

    /** Database version constant */
    private static final int DATABASE_VERSION = 1;

    /** Expenses table name */
    public static final String TABLE_EXPENSES = "expenses";

    /** Expense ID column name */
    public static final String COLUMN_ID = "id";

    /** Expense description column name */
    public static final String COLUMN_DESCRIPTION = "description";

    /** Expense amount column name */
    public static final String COLUMN_AMOUNT = "amount";

    /** Expense category column name */
    public static final String COLUMN_CATEGORY = "category";

    /** Expense date column name (stored as timestamp) */
    public static final String COLUMN_DATE = "date";

    /** SQL statement to create the expenses table */
    private static final String CREATE_EXPENSES_TABLE =
        "CREATE TABLE " + TABLE_EXPENSES + " (" +
        COLUMN_ID + " TEXT PRIMARY KEY, " +
        COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
        COLUMN_AMOUNT + " REAL NOT NULL, " +
        COLUMN_CATEGORY + " TEXT NOT NULL, " +
        COLUMN_DATE + " INTEGER NOT NULL" +
        ")";

    /**
     * Constructor for ExpenseDatabaseHelper.
     *
     * @param context The application context
     */
    public ExpenseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * Creates the expenses table with the defined schema.
     *
     * @param db The SQLite database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EXPENSES_TABLE);
    }

    /**
     * Called when the database needs to be upgraded.
     * Currently drops the existing table and recreates it.
     *
     * @param db The SQLite database instance
     * @param oldVersion The old database version
     * @param newVersion The new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now, drop and recreate table on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        onCreate(db);
    }
}
