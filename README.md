# RecipeApp README

## Overview

RecipeApp is an Android application that allows users to easily store and manage their favorite recipes. With this app, users can add, edit, and delete recipes, as well as view a list of all saved recipes. The app uses Room to store data locally on the device.

## Features

- **Add Recipe:** Users can input new recipes by specifying the name, duration, type, difficulty level, and individual ingredients.
- **Edit Recipe:** Users can edit existing recipes to make changes or corrections.
- **Delete Recipe:** Users can remove recipes from the list that are no longer needed.
- **Recipe List:** All saved recipes are displayed in a comprehensive list on the main page.
- **CRUD Operations:** The app supports full CRUD operations (Create, Read, Update, Delete) for recipes and ingredients.
- **Room Integration:** The app uses the Room persistence library to manage and store the database.
- **RecyclerView:** To efficiently display and manage the recipe list.
- **ListAdapter:** To efficiently manage and display the recipe list.
- **ViewModel:** To separate UI logic from data logic and better manage the lifecycle.
- **Model Classes:** Separate model classes for recipes and ingredients.

## App Structure

### Main Page

- **Recipe List:** A list of all saved recipes displaying basic information such as the recipe name.
- **Navigation Buttons:** Buttons to navigate to the pages for adding or editing recipes.

### Add/Edit Recipe

- **Form Fields:** Input fields for the recipe's name, duration, type, difficulty level, and individual ingredients.
- **Save Button:** Saves the new or edited recipe to the database.


## Installation & Start 

1. **Clone the project:**
2. **Open in Android Studio: Open the cloned project in Android Studio.**
3. **Synchronize dependencies: Make sure all dependencies are correctly synchronized.**
4. **Run the app: Launch the app on an emulator or physical device.**

## Architecture
- **Model Classes:** There are two model classes:
  - **RecipeModel:** Represents a recipe with attributes like name, duration, type, difficulty level, and ingredients.
  - **IngredientModel:** Represents an individual ingredient with attributes like name and quantity.
- **ViewModel:** Uses Android's ViewModel to manage UI data and avoid lifecycle issues.
- **RecyclerView:** A RecyclerView for efficient display of the recipe list.
- **ListAdapter:** A RecyclerView ListAdapter for efficient management of the recipe list and handling data changes.