package com.example.expensemanager.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.expensemanager.domain.ExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Factory class for creating ExpenseViewModel instances.
 * This factory is required when ViewModel needs custom constructor parameters
 * (in this case, the ExpenseRepository dependency).
 *
 * Uses Lombok annotations to reduce boilerplate code:
 * - @AllArgsConstructor: Generates constructor with all fields
 * - @Getter: Generates getter for the repository field
 */
@AllArgsConstructor
@Getter
public class ExpenseViewModelFactory implements ViewModelProvider.Factory {

    /** The repository instance to be injected into ViewModel */
    private final ExpenseRepository repository;

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
