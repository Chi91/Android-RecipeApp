package com.example.recipekotlin.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.recipekotlin.R
import com.example.recipekotlin.RecipeApplication
import com.example.recipekotlin.database.recipe.Recipe
import com.example.recipekotlin.databinding.FragmentRecipeEditBinding
import kotlinx.coroutines.launch


// UI Controller der Architektur
// Fkt: zeichnen der View - keine bearbeitung der Daten nur Weiterleitung an ViewModel
// Best Practice - jedes Fragment hat eigenen ViewModel

class RecipeEdit : Fragment() {

    private lateinit var binding: FragmentRecipeEditBinding

    // DatenHalterungsklasse wird instanziiert und der Varibale direkt zugewiesen
    private val viewModel: RecipeEditViewModel by viewModels(factoryProducer = {
        RecipeEditViewModelFactory(
            (requireContext().applicationContext as RecipeApplication).recipeRepo
        )
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecipeEditBinding.inflate(inflater, container, false)
        binding.saveBtn.setOnClickListener {
            val recipeTitle = binding.etName.text.toString()
            val recipeDescription = binding.etDesc.text.toString()
            val recipeDuration = binding.etDuration.text.toString()
            val recipeDifficulty = binding.etDifficulty.text.toString()
            val recipe = Recipe(0, recipeTitle, recipeDescription, recipeDuration, recipeDifficulty)

            val recipeId = arguments?.getInt("recipe_id")
            if(recipeTitle.isNotEmpty() && recipeId != null && recipeId >= 0 ){
                // update das jeweilige rezept
                viewModel.updateRecipe(recipeTitle,recipeDescription,recipeDuration,recipeDifficulty,recipeId)
            }
            else{
                // Weiterleitung der InputDaten vom User an ViewModel
                viewModel.insert(recipe)
            }
            onBackPressed()

        }

        binding.btnDelete.setOnClickListener {
            val recipeTitle = binding.etName.text.toString()
            val recipeId = arguments?.getInt("recipe_id")
            if(recipeTitle.isNotEmpty() && recipeId != null && recipeId >= 0 ){
                // update das jeweilige rezept
                viewModel.deleteRecipe(recipeId)
                onBackPressed()
            }
        }

        // Nur wenn RecipeID existiert, kann auf Ingrediient weitergeleitet werden
        // Recipe_ID wird IngredientList weitergeleitet
        binding.fabRecipeEdit.setOnClickListener {
            val recipeId = arguments?.getInt("recipe_id")
            if(recipeId != null && recipeId >= 0){
                findNavController().navigate(R.id.action_recipeEdit_to_ingredientList, bundleOf("recipe_id" to recipeId ))
                Log.i("RecipeEdit","${recipeId}")
            }
        }


        return binding.root
    }
    fun onBackPressed(){
        findNavController().navigate(R.id.action_recipeEdit_to_recipeList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeId = arguments?.getInt("recipe_id")
        if (recipeId != null && recipeId >= 0) {
            viewLifecycleOwner.lifecycleScope.launch {
                val recipe = viewModel.getRecipe(recipeId)
                if (recipe != null) {
                    binding.etName.setText(recipe.name)
                    binding.etDesc.setText(recipe.description)
                    binding.etDifficulty.setText(recipe.difficulty)
                    binding.etDuration.setText(recipe.duration)
                }
            }
        }
    }
}