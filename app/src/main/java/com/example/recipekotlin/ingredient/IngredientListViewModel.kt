package com.example.recipekotlin.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.recipekotlin.database.ingredient.Ingredient
import com.example.recipekotlin.database.ingredient.IngredientRepository

class IngredientListViewModel(private val repository: IngredientRepository): ViewModel() {

    val allIngredient: LiveData<List<Ingredient>> = repository.allIngredient.asLiveData()
}

class IngredientListViewModelFactory(private val repository: IngredientRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IngredientListViewModel::class.java)) {
            return IngredientListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}