package com.example.recipekotlin.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.recipekotlin.database.recipe.Recipe
import com.example.recipekotlin.database.recipe.RecipeRepository

class RecipeListViewModel(private val repository: RecipeRepository): ViewModel() {

    val allRecipes: LiveData<List<Recipe>> = repository.allRecipes.asLiveData()
}

class RecipeListViewModelFactory(private val repository: RecipeRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Überprüfe ob viewmodelklasse existiert
        if(modelClass.isAssignableFrom(RecipeListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RecipeListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
