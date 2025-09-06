package com.example.expensemanager.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.expensemanager.domain.ExpenseRepository;

/**
 * Factory class for creating ExpenseViewModel instances.
 * This factory is required when ViewModel needs custom constructor parameters
 * (in this case, the ExpenseRepository dependency).
 */
public class ExpenseViewModelFactory implements ViewModelProvider.Factory {

    /** The repository instance to be injected into ViewModel */
    private final ExpenseRepository repository;

    /**
     * Constructor for ExpenseViewModelFactory.
     *
     * @param repository The expense repository to be passed to the ViewModel
     */
    public ExpenseViewModelFactory(ExpenseRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new instance of the requested ViewModel class.
     * This method is called by the ViewModelProvider when creating ViewModel instances.
     *
     * @param modelClass The class of the ViewModel to create
     * @param <T> The type of ViewModel
     * @return A new instance of the requested ViewModel
     * @throws IllegalArgumentException if the requested ViewModel class is not supported
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ExpenseViewModel.class)) {
            return (T) new ExpenseViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
