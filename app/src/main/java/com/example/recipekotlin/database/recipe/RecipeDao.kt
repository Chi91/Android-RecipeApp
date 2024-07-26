package com.example.recipekotlin.database.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipekotlin.database.recipe.Recipe
import kotlinx.coroutines.flow.Flow

// DAO (Data Access Object)
// Zugriff auf db über Fkt
// suspend um Fkt in seperaten Thread laufen zulassen (coroutines <-> callback)
// flow - observer für die Daten, Room generiert Code und update FlowDaten, wenn db ändert
// Unterschied zu LiveData, Flow ist nicht lifecycle aware
@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table ORDER BY name ASC")
    fun getAllRecipe(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE recipeID = :recipeId")
    suspend fun getRecipeById(recipeId: Int): Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAll()

    @Query("UPDATE recipe_table SET name= :newName, description= :newDescription, duration= :newDuration, difficulty= :newDifficulty WHERE recipeID= :recipe_ID")
    suspend fun updateRecipe(newName: String, newDescription: String, newDuration: String, newDifficulty: String, recipe_ID: Int)

    @Query("DELETE FROM recipe_table WHERE recipeID= :recipe_ID")
    suspend fun deleteRecipe(recipe_ID: Int)

}