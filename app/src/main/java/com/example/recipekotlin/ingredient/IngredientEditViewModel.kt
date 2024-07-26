package com.example.recipekotlin.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipekotlin.database.ingredient.Ingredient
import com.example.recipekotlin.database.ingredient.IngredientRepository
import kotlinx.coroutines.launch

class IngredientEditViewModel(private val repository: IngredientRepository): ViewModel() {

    fun insert(ingredient: Ingredient) = viewModelScope.launch{
        repository.insert(ingredient)
    }
    suspend fun getIngredient(ingredientID: Int) = repository.getIngredientByID(ingredientID)

    fun updateIngredient(newName: String, newAmount: String, newVolume: String,ingredient_ID: Int) = viewModelScope.launch {
        repository.updateIngredient(newName,newAmount,newVolume,ingredient_ID)
    }

    fun deleteIngredient(ingredient_ID: Int) = viewModelScope.launch {
        repository.deleteIngredient(ingredient_ID)
    }
}

class IngredientEditViewModelFactory(private val repository: IngredientRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IngredientEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngredientEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}