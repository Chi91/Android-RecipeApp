package com.example.recipekotlin.ingredient

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recipekotlin.database.ingredient.Ingredient
import com.example.recipekotlin.recipe.RecipeListAdapter


class IngredientListAdapater(private val onIngredientClicked: (Ingredient)-> Unit) : ListAdapter<Ingredient, IngredientViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.ingredientID == newItem.ingredientID
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(parent, onIngredientClicked)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}