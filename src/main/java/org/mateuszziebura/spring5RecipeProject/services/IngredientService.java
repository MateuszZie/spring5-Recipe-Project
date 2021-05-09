package org.mateuszziebura.spring5RecipeProject.services;

import org.mateuszziebura.spring5RecipeProject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeUrlAndIngredientId(String url, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
