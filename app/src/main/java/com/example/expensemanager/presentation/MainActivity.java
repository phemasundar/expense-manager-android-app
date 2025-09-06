package com.example.expensemanager.presentation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.expensemanager.R;
import com.example.expensemanager.data.ExpenseRepositoryImpl;
import com.example.expensemanager.domain.ExpenseRepository;

public class MainActivity extends AppCompatActivity {
    private ExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpenseRepository repository = new ExpenseRepositoryImpl();
        viewModel = new ViewModelProvider(this, new ExpenseViewModelFactory(repository))
                .get(ExpenseViewModel.class);

        // Observe expenses
        viewModel.getExpenses().observe(this, expenses -> {
            // Update UI
        });
    }
}
