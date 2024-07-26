package com.example.recipekotlin.database.ingredient

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val ingredientDao: IngredientDao) {

    val allIngredient: Flow<List<Ingredient>> = ingredientDao.getAllIngredients()

    @WorkerThread
    suspend fun insert(ingredient: Ingredient){
        ingredientDao.insert(ingredient)
    }
    suspend fun getIngredientByID(ingredientID: Int): Ingredient? =
        ingredientDao.getIngredientByID(ingredientID)

    suspend fun updateIngredient(newName: String, newAmount: String, newVolume: String,ingredient_ID: Int){
        ingredientDao.updateIngredient(newName,newAmount,newVolume,ingredient_ID)
    }

    suspend fun deleteIngredient(ingredient_ID: Int){
        ingredientDao.deleteIngredient(ingredient_ID)
    }
}