package com.example.recipekotlin.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipekotlin.R
import com.example.recipekotlin.database.recipe.Recipe
import com.example.recipekotlin.databinding.RecipeListItemBinding

//Fkt: Verbindung der Daten mit dem einzelnem ViewItem + Darstellung eines einzelnen Item
// Direkt bei der Klassendefinition, wird angezeigt, wie der ViewHolder instanziiert wird keine explizite Funktion
class RecipeViewHolder(parent: ViewGroup, private val onRecipeClicked: (Recipe) -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
) {

    private val binding = RecipeListItemBinding.bind(itemView)

    private lateinit var recipe: Recipe

    init {
        binding.root.setOnClickListener {
            onRecipeClicked(recipe)
        }
    }
    fun bind(recipe: Recipe) {
        this.recipe = recipe
        binding.textViewItemName.text = recipe.name
    }
}
