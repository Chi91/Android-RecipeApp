package com.example.recipekotlin.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

import android.view.View

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels

import androidx.navigation.findNavController

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.RecyclerListener

import com.example.recipekotlin.R
import com.example.recipekotlin.RecipeApplication
import com.example.recipekotlin.databinding.FragmentRecipeListBinding

// Viewbinding über Parameter - definition welches XML
class RecipeList : Fragment(R.layout.fragment_recipe_list) {
    private lateinit var binding: FragmentRecipeListBinding

    private val viewModel: RecipeListViewModel by viewModels(factoryProducer = {
        RecipeListViewModelFactory(
            (requireContext().applicationContext as RecipeApplication).recipeRepo
        )
    })

    private lateinit var adapter: RecipeListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Konkrete Bindung zwischen View und XML
        binding = FragmentRecipeListBinding.bind(view)
        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_recipeList_to_recipeEdit)
        }
        // Instanziierung des Adapaters
        // Jedes Item ist anklickbar und wechselt zur definierten Fragment
        // Bundle( vergleichbar mit HashMap, Key Value) Datenübertragung zwischen Fragmenten
        adapter = RecipeListAdapter(onRecipeClicked = { recipe ->
            findNavController().navigate(R.id.action_recipeList_to_recipeEdit, bundleOf("recipe_id" to recipe.recipeID))
            Log.i("RecipeList","${recipe.recipeID}")
        })
        binding.rvRecipe.adapter = adapter

        // Jeder RecyclerView benötig LayoutManager um zu  bestimmen, wie innerhalb der Liste dargestellt wird
        binding.rvRecipe.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        //Daten aus Viewmodel mit dem Adapter verbinden
        // durch livedata sind die Data observerable, bei ändern automatisch neurendern
        // hier kommt es zur Darstellung im Fragment dadurch, dass RecyclerView mit adapter verbunden ist
        viewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            adapter.submitList(recipes)
        }
    }
}