package com.example.recipekotlin.recipe

import androidx.lifecycle.*
import com.example.recipekotlin.database.recipe.Recipe
import com.example.recipekotlin.database.recipe.RecipeRepository
import kotlinx.coroutines.launch

// ViewModel - Datenhalterungs - und verarbeitungsklasse
// Umwandlung Flow zu LiveData und stellen sicher, dass wenn die Datenbank ändert, die UI automatisch ändert
// Besonderheit Kotlin : Parameter von Klassen gleich Property d Klasse
class RecipeEditViewModel(private val repository: RecipeRepository) : ViewModel() {

    //Starte eine neue coroutine - asynchroner Aufruf
    // Weiterleitung an Repo
    fun insert(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }

    suspend fun getRecipe(recipeId: Int) = repository.getRecipeById(recipeId)

    fun updateRecipe(newName: String, newDescription: String, newDuration: String, newDifficulty: String, recipe_ID: Int) = viewModelScope.launch {
        repository.updateRecipe(newName,newDescription,newDuration,newDifficulty,recipe_ID)
    }

    fun deleteRecipe(recipeId: Int) = viewModelScope.launch {
        repository.deleteRecipe(recipeId)
    }

}
// Factory Method Pattern - Factory Method um eine Klasse zu erstellen mit vorgegebener Funktionalität
// Ziel dieser Factory ist ein ViewModel zu erstellen mit lifecylc awareness
class RecipeEditViewModelFactory(private val repository: RecipeRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Überprüfe ob viewmodelklasse existiert
        if(modelClass.isAssignableFrom(RecipeEditViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RecipeEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}