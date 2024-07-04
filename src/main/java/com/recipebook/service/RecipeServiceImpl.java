package com.recipebook.service;

import com.recipebook.dao.RecipeRepository;
import com.recipebook.domain.Recipe;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> getRecipeById(UUID id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void updateRecipe(UUID id, Recipe updatedRecipe) {
        Recipe existingRecipe = recipeRepository.findById(id).orElse(null);
        if (existingRecipe != null) {
            existingRecipe.setName(updatedRecipe.getName());
            // Handle ingredients and instructions update
            existingRecipe.setIngredients(updatedRecipe.getIngredients().stream()
                    .filter(ingredient -> !StringUtils.isEmpty(ingredient)) // Remove empty ingredients
                    .toList());
            existingRecipe.setInstructions(updatedRecipe.getInstructions().stream()
                    .filter(instruction -> !StringUtils.isEmpty(instruction)) // Remove empty instructions
                    .toList());
            recipeRepository.save(existingRecipe);
        }
    }

    @Override
    public void deleteRecipe(UUID id) {
        recipeRepository.deleteById(id);
    }

}
