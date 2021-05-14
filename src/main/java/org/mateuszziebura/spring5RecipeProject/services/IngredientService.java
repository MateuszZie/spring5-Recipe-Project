package org.mateuszziebura.spring5RecipeProject.services;

import org.mateuszziebura.spring5RecipeProject.commands.IngredientCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Ingredient;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;

public interface IngredientService {
    IngredientCommand findByRecipeUrlAndIngredientId(String url, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteIngredient(Ingredient ingredient, Recipe recipe);
}
