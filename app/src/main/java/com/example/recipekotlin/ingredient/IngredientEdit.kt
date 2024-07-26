package com.example.recipekotlin.ingredient

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.recipekotlin.R
import com.example.recipekotlin.RecipeApplication
import com.example.recipekotlin.database.ingredient.Ingredient
import com.example.recipekotlin.databinding.FragmentIngredientEditBinding
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

private const val REQUEST_CODE = 42
class IngredientEdit : Fragment(R.layout.fragment_ingredient_edit) {

    private lateinit var binding: FragmentIngredientEditBinding

    private val viewModel: IngredientEditViewModel by viewModels(factoryProducer = {
        IngredientEditViewModelFactory(
            (requireContext().applicationContext as RecipeApplication).ingredientRepo
        )
    })

    var ingredientPic: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentIngredientEditBinding.bind(view)

        binding.fotoBtn.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        }


        binding.saveBtn.setOnClickListener {

            val recipeOwnerId = arguments?.getInt("recipe_id")
            val ingredientAmount = binding.etAmount.text.toString()
            val ingredientVolume = binding.etVolume.text.toString()
            val ingredientName = binding.etNameOfIngredient.text.toString()
            val ingredient = Ingredient(0,ingredientName,ingredientAmount,ingredientVolume, recipeOwnerId?: 0,ingredientPic)


            val ingredientID = arguments?.getInt("ingredient_id")

            if(ingredientName.isNotEmpty() && ingredientID != null && ingredientID >0){

                viewModel.updateIngredient(ingredientName,ingredientAmount,ingredientAmount,ingredientID)
            }
            else{ viewModel.insert(ingredient)

            }
            onBackPressed()
        }

        binding.ingredientDeleteBtn.setOnClickListener {
            val ingredientName = binding.etNameOfIngredient.text.toString()
            val ingredientID = arguments?.getInt("ingredient_id")
            if(ingredientName.isNotEmpty() && ingredientID != null && ingredientID >=0){
                viewModel.deleteIngredient(ingredientID)
                onBackPressed()
            }
        }

        val ingredientID = arguments?.getInt("ingredient_id")
        if(ingredientID != null && ingredientID >=0){
            viewLifecycleOwner.lifecycleScope.launch {
                val ingredient = viewModel.getIngredient(ingredientID)
                if(ingredient != null){
                   binding.etAmount.setText(ingredient.amount)
                   binding.etNameOfIngredient.setText(ingredient.name)
                   binding.etVolume.setText(ingredient.volume)
                }
            }
        }
    }
    fun onBackPressed(){
        val recipeOwnerId = arguments?.getInt("recipe_id")
        findNavController().navigate(R.id.action_ingredientEdit_to_ingredientList, bundleOf("recipe_id" to recipeOwnerId))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenImage= data?.extras?.get("data") as Bitmap

            val baos = ByteArrayOutputStream()
            takenImage.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val b = baos.toByteArray()
            val imageString: String = Base64.encodeToString(b, Base64.DEFAULT)
            ingredientPic = imageString

            binding.takenPic.setImageBitmap(takenImage)

        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}