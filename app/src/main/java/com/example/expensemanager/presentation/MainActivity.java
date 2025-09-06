package com.example.expensemanager.presentation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expensemanager.R;
import com.example.expensemanager.data.ExpenseRepositoryImpl;
import com.example.expensemanager.domain.Expense;
import com.example.expensemanager.domain.ExpenseRepository;
import java.util.List;

/**
 * Main activity for the Expense Manager application.
 * This activity serves as the main entry point and displays a list of expenses
 * using a RecyclerView. It initializes the database on first launch and shows
 * all existing expenses to the user.
 */
public class MainActivity extends AppCompatActivity {

    /** ViewModel for managing expense data and business logic */
    private ExpenseViewModel viewModel;

    /** Adapter for binding expense data to the RecyclerView */
    private ExpenseAdapter expenseAdapter;

    /** RecyclerView for displaying the list of expenses */
    private RecyclerView expensesRecyclerView;

    /**
     * Called when the activity is first created.
     * Sets up the UI components, initializes the database, and starts observing expense data.
     * The database is automatically created on first launch if it doesn't exist.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state, or null
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        expensesRecyclerView = findViewById(R.id.expensesRecyclerView);
        expensesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseAdapter = new ExpenseAdapter(null);
        expensesRecyclerView.setAdapter(expenseAdapter);

        ExpenseRepository repository = new ExpenseRepositoryImpl(this);
        viewModel = new ViewModelProvider(this, new ExpenseViewModelFactory(repository))
                .get(ExpenseViewModel.class);

        // Observe expenses
        viewModel.getExpenses().observe(this, this::updateExpensesList);
    }

    /**
     * Updates the expense list in the RecyclerView.
     * Called when the observed expense data changes, this method updates the adapter
     * with the new list of expenses, which triggers a UI refresh.
     *
     * @param expenses The updated list of expenses to display
     */
    private void updateExpensesList(List<Expense> expenses) {
        expenseAdapter.updateExpenses(expenses);
    }
}
