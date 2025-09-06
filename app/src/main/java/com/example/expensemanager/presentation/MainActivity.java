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

public class MainActivity extends AppCompatActivity {
    private ExpenseViewModel viewModel;
    private ExpenseAdapter expenseAdapter;
    private RecyclerView expensesRecyclerView;

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

    private void updateExpensesList(List<Expense> expenses) {
        expenseAdapter.updateExpenses(expenses);
    }
}
