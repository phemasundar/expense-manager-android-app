package com.example.expensemanager.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.expensemanager.domain.Expense;
import com.example.expensemanager.domain.ExpenseRepository;
import com.example.expensemanager.domain.GetExpensesUseCase;
import java.util.List;

public class ExpenseViewModel extends ViewModel {
    private final GetExpensesUseCase getExpensesUseCase;
    private final MutableLiveData<List<Expense>> expensesLiveData = new MutableLiveData<>();

    public ExpenseViewModel(ExpenseRepository repository) {
        this.getExpensesUseCase = new GetExpensesUseCase(repository);
        loadExpenses();
    }

    public LiveData<List<Expense>> getExpenses() {
        return expensesLiveData;
    }

    private void loadExpenses() {
        List<Expense> expenses = getExpensesUseCase.execute();
        expensesLiveData.setValue(expenses);
    }

    public void addExpense(Expense expense) {
        // Implementation for adding expense
        loadExpenses();
    }
}
