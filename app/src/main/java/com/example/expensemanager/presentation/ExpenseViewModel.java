package com.example.expensemanager.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.expensemanager.domain.Expense;
import com.example.expensemanager.domain.ExpenseRepository;
import com.example.expensemanager.domain.GetExpensesUseCase;
import lombok.Getter;

import java.util.List;

/**
 * ViewModel for managing expense-related data and business logic.
 * This class acts as a bridge between the UI layer and the domain layer,
 * providing observable data and handling expense operations.
 *
 * Uses Lombok annotations to reduce boilerplate code:
 * - @Getter: Generates getter for the expenses LiveData
 */
@Getter
public class ExpenseViewModel extends ViewModel {

    /** Use case for retrieving expenses from the repository */
    private final GetExpensesUseCase getExpensesUseCase;

    /** LiveData for observing expense list changes */
    private final MutableLiveData<List<Expense>> expensesLiveData = new MutableLiveData<>();

    /**
     * Constructor for ExpenseViewModel.
     * Initializes the use case and loads initial expense data.
     *
     * @param repository The expense repository for data operations
     */
    public ExpenseViewModel(ExpenseRepository repository) {
        this.getExpensesUseCase = new GetExpensesUseCase(repository);
        loadExpenses();
    }

    /**
     * Returns the LiveData object for observing expense list changes.
     * UI components can observe this to automatically update when expense data changes.
     *
     * @return LiveData containing the list of expenses
     */
    public LiveData<List<Expense>> getExpenses() {
        return expensesLiveData;
    }

    /**
     * Loads expenses from the repository and updates the LiveData.
     * This method is called during initialization and after expense modifications.
     */
    private void loadExpenses() {
        List<Expense> expenses = getExpensesUseCase.execute();
        expensesLiveData.setValue(expenses);
    }

    /**
     * Adds a new expense and refreshes the expense list.
     * Note: This is a placeholder implementation that needs to be completed
     * with proper add expense logic.
     *
     * @param expense The expense to be added
     */
    public void addExpense(Expense expense) {
        // Implementation for adding expense
        loadExpenses();
    }
}
