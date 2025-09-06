package com.example.expensemanager.domain;

/**
 * Domain model representing an expense entry.
 * This class encapsulates all the data related to a single expense including
 * its identifier, description, amount, category, and date.
 */
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

    /**
     * Constructor for creating a new Expense instance.
     *
     * @param id Unique identifier for the expense
     * @param description Description of what the expense was for
     * @param amount The monetary amount of the expense
     * @param category The category this expense belongs to
     * @param date The date of the expense as a timestamp
     */
    public Expense(String id, String description, double amount, String category, long date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    /**
     * Gets the unique identifier of the expense.
     *
     * @return The expense ID
     */
    public String getId() { return id; }

    /**
     * Sets the unique identifier of the expense.
     *
     * @param id The expense ID to set
     */
    public void setId(String id) { this.id = id; }

    /**
     * Gets the description of the expense.
     *
     * @return The expense description
     */
    public String getDescription() { return description; }

    /**
     * Sets the description of the expense.
     *
     * @param description The expense description to set
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Gets the monetary amount of the expense.
     *
     * @return The expense amount
     */
    public double getAmount() { return amount; }

    /**
     * Sets the monetary amount of the expense.
     *
     * @param amount The expense amount to set
     */
    public void setAmount(double amount) { this.amount = amount; }

    /**
     * Gets the category of the expense.
     *
     * @return The expense category
     */
    public String getCategory() { return category; }

    /**
     * Sets the category of the expense.
     *
     * @param category The expense category to set
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Gets the date of the expense as a timestamp.
     *
     * @return The expense date as timestamp
     */
    public long getDate() { return date; }

    /**
     * Sets the date of the expense as a timestamp.
     *
     * @param date The expense date to set as timestamp
     */
    public void setDate(long date) { this.date = date; }
}
