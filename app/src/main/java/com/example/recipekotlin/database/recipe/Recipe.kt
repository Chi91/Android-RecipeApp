package com.example.recipekotlin.database.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
class Recipe(
    //Defintion d. PrimärSchlüssel
    @PrimaryKey(autoGenerate = true)

    // Defintion Spalten d. Tabelle
    @ColumnInfo(name= "recipeID") val recipeID: Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "duration") val duration: String,
    @ColumnInfo(name= "difficulty") val difficulty: String
)
