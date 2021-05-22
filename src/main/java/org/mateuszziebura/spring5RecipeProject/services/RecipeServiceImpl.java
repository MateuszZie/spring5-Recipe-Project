package org.mateuszziebura.spring5RecipeProject.services;

import lombok.extern.slf4j.Slf4j;
import org.mateuszziebura.spring5RecipeProject.commands.RecipeCommand;
import org.mateuszziebura.spring5RecipeProject.converters.RecipeCommandToRecipe;
import org.mateuszziebura.spring5RecipeProject.converters.RecipeToRecipeCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Ingredient;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.exceptions.NotFoundException;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public RecipeCommand findCommandByUrl(String url) {
        Optional<Recipe> recipeOptional = recipeRepository.findByUrl(url);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found!. For Name "+url);
        }

        return recipeToRecipeCommand.convert(recipeOptional.get());
    }
    @Override
    public RecipeCommand findCommandById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found!");
        }

        return recipeToRecipeCommand.convert(recipeOptional.get());
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }

    @Override
    public Recipe findByUrl(String url) {
        Optional<Recipe> recipeOptional = recipeRepository.findByUrl(url);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found!. For Name "+url);
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
