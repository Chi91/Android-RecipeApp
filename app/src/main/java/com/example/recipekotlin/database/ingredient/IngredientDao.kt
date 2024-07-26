package com.example.recipekotlin.database.ingredient

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipekotlin.database.recipe.Recipe
import kotlinx.coroutines.flow.Flow


@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient_table")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ingredient: Ingredient)

    @Query("SELECT * FROM ingredient_table WHERE ingredientID = :ingredientId")
    suspend fun getIngredientByID(ingredientId : Int): Ingredient?

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAll()

    @Query("UPDATE ingredient_table SET name= :newName, amount= :newAmount, volume =:newVolume WHERE ingredientID=:ingredient_ID")
    suspend fun updateIngredient(newName: String, newAmount: String, newVolume: String,ingredient_ID: Int)

    @Query("DELETE FROM ingredient_table WHERE ingredientID=:ingredient_ID")
    suspend fun deleteIngredient(ingredient_ID: Int)

}