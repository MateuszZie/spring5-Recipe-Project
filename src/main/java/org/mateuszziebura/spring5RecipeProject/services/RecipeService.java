package org.mateuszziebura.spring5RecipeProject.services;

import org.mateuszziebura.spring5RecipeProject.commands.RecipeCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    Recipe findByUrl(String url);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
