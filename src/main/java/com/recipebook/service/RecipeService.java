package com.recipebook.service;

import com.recipebook.domain.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    Optional<Recipe> getRecipeById(UUID id);

    Recipe saveRecipe(Recipe recipe);

    void updateRecipe(UUID id, Recipe updatedRecipe);

    void deleteRecipe(UUID id);

}
