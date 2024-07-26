package com.example.recipekotlin.database.recipe

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


//Repo abstrahiert den Zugriff auf multiplen Datenquellen
// Best Practice für Code Seperation -
//Fkt: Verwaltet die Queries und multiple Backends
// implemntiert der Logik für fetch data

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipes: Flow<List<Recipe>> = recipeDao.getAllRecipe()

    @WorkerThread
    suspend fun insert(recipe: Recipe){
        recipeDao.insert(recipe)
    }
    //Besonderer Syntax bei return ,statt {} = verwenden
    suspend fun getRecipeById(recipeId: Int): Recipe? = recipeDao.getRecipeById(recipeId)

    suspend fun updateRecipe(newName: String, newDescription: String, newDuration: String, newDifficulty: String, recipe_ID: Int){
        recipeDao.updateRecipe(newName,newDescription,newDuration,newDifficulty,recipe_ID)
    }

    suspend fun deleteRecipe(recipeId: Int){
        recipeDao.deleteRecipe(recipeId)
    }
}