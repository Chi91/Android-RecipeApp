package com.example.recipekotlin.database.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
class Ingredient(
    @PrimaryKey(autoGenerate = true) val ingredientID: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount" ) val amount: String,
    @ColumnInfo(name = "volume") val volume: String,
    @ColumnInfo(name = "recipeOwnerID") val recipeOwnerID: Int,
    @ColumnInfo(name = "ingredientPic") val ingredientPic: String
)
