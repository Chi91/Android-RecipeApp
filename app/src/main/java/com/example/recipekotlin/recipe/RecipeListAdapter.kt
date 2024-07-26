package com.example.recipekotlin.recipe

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recipekotlin.database.recipe.Recipe


class RecipeListAdapter(private val onRecipeClicked: (Recipe) -> Unit) : ListAdapter<Recipe, RecipeViewHolder>(DiffCallback) {

    // Das ist wegen LiveData, wenn Werte im Recipe sich ändern
    // Fkt Überprüfung der Liste - was zurückgegeben wird
    // Nur dasjenige was sich verändert wird verändert, nicht komplette Liste neugerendert -> performant!
    object DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.recipeID == newItem.recipeID
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.name == newItem.name
        }
    }
    // Bei Erstellung ViewHolder wird definiert, wie ein Item aus Collection dargestellt wird ohne Daten
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(parent, onRecipeClicked)
    }

    // Erst bei submit(recipes) wird Methode aufgerufen für jedes zu darstellendes Item
    // RecyclerView stellt Liste dynamisch dar d.h nicht alle Elemente werden sofort dargestellt, sondern recycelt
    // Position gibt an wo sich RecyclerView aktuell befindet
    // Mit Position wird aus Collection das jeweilige Item rausgeholt und an ViewHolder gebunden
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipe = getItem(position))
    }
}
