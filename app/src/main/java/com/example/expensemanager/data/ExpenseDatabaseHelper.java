package com.example.expensemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpenseDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "expense_manager.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    public static final String TABLE_EXPENSES = "expenses";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    // SQL to create table
    private static final String CREATE_EXPENSES_TABLE =
        "CREATE TABLE " + TABLE_EXPENSES + " (" +
        COLUMN_ID + " TEXT PRIMARY KEY, " +
        COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
        COLUMN_AMOUNT + " REAL NOT NULL, " +
        COLUMN_CATEGORY + " TEXT NOT NULL, " +
        COLUMN_DATE + " INTEGER NOT NULL" +
        ")";

    public ExpenseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EXPENSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now, drop and recreate table on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        onCreate(db);
    }
}
