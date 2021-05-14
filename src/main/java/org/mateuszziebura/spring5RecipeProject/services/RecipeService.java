package org.mateuszziebura.spring5RecipeProject.services;

import org.mateuszziebura.spring5RecipeProject.commands.RecipeCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Ingredient;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    Recipe findByUrl(String url);

    RecipeCommand findCommandByUrl(String url);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    Recipe saveRecipe(Recipe recipe);

    void deleteById(Long id);

}
