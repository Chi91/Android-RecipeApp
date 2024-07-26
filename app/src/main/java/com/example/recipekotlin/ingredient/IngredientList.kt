package com.example.recipekotlin.ingredient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

import android.view.View

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipekotlin.R
import com.example.recipekotlin.RecipeApplication
import com.example.recipekotlin.databinding.FragmentIngredientListBinding

class IngredientList : Fragment(R.layout.fragment_ingredient_list) {

    private lateinit var binding: FragmentIngredientListBinding

    private lateinit var adapater: IngredientListAdapater

    private var recipeId : Int? = null

    private val viewModel: IngredientListViewModel by viewModels(factoryProducer = {
        IngredientListViewModelFactory(
            (requireContext().applicationContext as RecipeApplication).ingredientRepo
        )
    })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeId = arguments?.getInt("recipe_id")
        Log.i("IngredientList","${recipeId} + recipeID")

        binding = FragmentIngredientListBinding.bind(view)
        binding.fabIngredientList.setOnClickListener {
            findNavController().navigate(R.id.action_ingredientList_to_ingredientEdit, bundleOf("recipe_id" to recipeId))
        }
        adapater = IngredientListAdapater(onIngredientClicked = {
            ingredient -> findNavController().navigate(R.id.action_ingredientList_to_ingredientEdit, bundleOf("ingredient_id" to ingredient.ingredientID))

        })

        binding.rvIngredient.adapter = adapater
        binding.rvIngredient.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        viewModel.allIngredient.observe(viewLifecycleOwner){
            ingredients -> adapater.submitList(ingredients)
        }

        binding.goBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_ingredientList_to_recipeList2)
        }
    }
}