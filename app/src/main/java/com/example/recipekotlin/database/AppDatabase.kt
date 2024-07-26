package com.example.recipekotlin.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.recipekotlin.RecipeApplication
import com.example.recipekotlin.database.ingredient.Ingredient
import com.example.recipekotlin.database.ingredient.IngredientDao
import com.example.recipekotlin.database.recipe.Recipe
import com.example.recipekotlin.database.recipe.RecipeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


//Klasse muss abstrakt sein, weil Room es selber implementiert, wie auch Property
@Database(entities = [Recipe::class, Ingredient::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao

    // companion object zugriff auf Klasse ohne sie zu instanziieren
    // sinnvoll, weil nur 1x db öffnen
    companion object {
        //Singleton Pattern benutzen, nur 1x db
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context,scope: CoroutineScope): AppDatabase {
            // ?: elvis operator
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )    .fallbackToDestructiveMigration()
                    .addCallback(RecipeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance

            }
        }
    }
    // Wie die db instanziiert wird, an sich nicht so sinnvoll wegen persistenz
    private class RecipeDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var recipeDao = database.recipeDao()
                    recipeDao.deleteAll()
                }
            }
        }
    }
}
