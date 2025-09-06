package com.example.expensemanager.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expensemanager.R;
import com.example.expensemanager.domain.Expense;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * RecyclerView adapter for displaying expense items in a list.
 * This adapter binds expense data to card-based list items and handles the display
 * of expense information including description, category, amount, and date.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    /** List of expenses to display */
    private List<Expense> expenses;

    /** Date formatter for displaying expense dates in a user-friendly format */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

    /**
     * Constructor for ExpenseAdapter.
     *
     * @param expenses Initial list of expenses to display, can be null
     */
    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Creates a new ViewHolder for expense items.
     * Inflates the item_expense layout and returns a new ExpenseViewHolder instance.
     *
     * @param parent The parent ViewGroup
     * @param viewType The view type (not used in this implementation)
     * @return A new ExpenseViewHolder instance
     */
    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    /**
     * Binds expense data to the ViewHolder at the specified position.
     * Retrieves the expense at the given position and calls the ViewHolder's bind method.
     *
     * @param holder The ViewHolder to bind data to
     * @param position The position of the item in the list
     */
    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.bind(expense);
    }

    /**
     * Returns the total number of items in the expense list.
     *
     * @return The number of expenses, or 0 if the list is null
     */
    @Override
    public int getItemCount() {
        return expenses != null ? expenses.size() : 0;
    }

    /**
     * Updates the adapter with a new list of expenses.
     * Replaces the current expense list and notifies the RecyclerView of the change.
     *
     * @param newExpenses The new list of expenses to display
     */
    public void updateExpenses(List<Expense> newExpenses) {
        this.expenses = newExpenses;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for expense items.
     * Holds references to the TextViews in the expense item layout and provides
     * a method to bind expense data to these views.
     *
     * Uses Lombok annotations to reduce boilerplate code:
     * - @AllArgsConstructor: Generates constructor with all fields
     */
    static class ExpenseViewHolder extends RecyclerView.ViewHolder {

        /** TextView for displaying the expense description */
        private final TextView descriptionTextView;

        /** TextView for displaying the expense category */
        private final TextView categoryTextView;

        /** TextView for displaying the expense amount */
        private final TextView amountTextView;

        /** TextView for displaying the expense date */
        private final TextView dateTextView;

        /**
         * Constructor for ExpenseViewHolder.
         * Initializes the TextView references by finding them in the item view.
         *
         * @param itemView The view for the expense item
         */
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }

        /**
         * Binds expense data to the TextViews.
         * Formats the expense information and sets it to the appropriate TextViews.
         *
         * @param expense The expense object to bind to the views
         */
        public void bind(Expense expense) {
            descriptionTextView.setText(expense.getDescription());
            categoryTextView.setText(expense.getCategory());
            amountTextView.setText(String.format(Locale.getDefault(), "$%.2f", expense.getAmount()));
            dateTextView.setText(dateFormat.format(new Date(expense.getDate())));
        }
    }
}
