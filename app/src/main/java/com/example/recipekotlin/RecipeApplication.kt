package com.example.recipekotlin

import android.app.Application
import com.example.recipekotlin.database.AppDatabase
import com.example.recipekotlin.database.ingredient.IngredientRepository
import com.example.recipekotlin.database.recipe.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// muss in manifest deklariert werdene
class RecipeApplication : Application() {

    // DB soll nicht UI lifecylce entsprechen
    val applicationScope = CoroutineScope(SupervisorJob())

    // Instanziierung von db und recipeRepo
    // by lazy kotlin property für erstellen, nur wenn gebraucht wird
    // im manifest definieren
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val recipeRepo by lazy { RecipeRepository(database.recipeDao()) }
    val ingredientRepo by lazy { IngredientRepository(database.ingredientDao())}
}