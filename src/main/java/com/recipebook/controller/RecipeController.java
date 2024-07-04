package com.recipebook.controller;

import com.recipebook.domain.Recipe;
import com.recipebook.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "index";
    }

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "add_recipe";
    }

    @PostMapping("/add")
    public String addRecipe(@ModelAttribute("recipe") Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditRecipeForm(@PathVariable("id") UUID id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        model.addAttribute("recipe", recipe);
        return "edit_recipe";
    }

    @PostMapping("/update/{id}")
    public String updateRecipe(@PathVariable("id") UUID id, @ModelAttribute("recipe") Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id") UUID id) {
        recipeService.deleteRecipe(id);
        return "redirect:/";
    }
}
