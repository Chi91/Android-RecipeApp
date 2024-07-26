package com.example.recipekotlin.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipekotlin.R
import com.example.recipekotlin.database.ingredient.Ingredient
import com.example.recipekotlin.databinding.RecipeListItemBinding

class IngredientViewHolder(parent: ViewGroup,private val onIngredientClicked: (Ingredient) -> Unit ): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.recipe_list_item, parent, false)) {

    private val binding = RecipeListItemBinding.bind(itemView)

    private lateinit var ingredient: Ingredient

    init{
        binding.root.setOnClickListener {
            onIngredientClicked(ingredient)
        }
    }

    fun bind(ingredient: Ingredient){
        this.ingredient = ingredient
        binding.textViewItemName.text = ingredient.name
    }
}