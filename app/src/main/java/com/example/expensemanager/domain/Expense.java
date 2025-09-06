package com.example.expensemanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model representing an expense entry.
 * This class encapsulates all the data related to a single expense including
 * its identifier, description, amount, category, and date.
 *
 * Uses Lombok annotations to reduce boilerplate code:
 * - @Data: Generates getters, setters, equals, hashCode, and toString
 * - @AllArgsConstructor: Generates constructor with all fields
 * - @NoArgsConstructor: Generates no-args constructor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    /** Unique identifier for the expense */
    private String id;

    /** Description of the expense (e.g., "Lunch at restaurant") */
    private String description;

    /** Monetary amount of the expense */
    private double amount;

    /** Category of the expense (e.g., "Food", "Transportation") */
    private String category;

    /** Date when the expense occurred (stored as timestamp) */
    private long date;
}
