package org.mateuszziebura.spring5RecipeProject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.commands.IngredientCommand;
import org.mateuszziebura.spring5RecipeProject.converters.IngredientCommandToIngredient;
import org.mateuszziebura.spring5RecipeProject.converters.IngredientToIngredientCommand;
import org.mateuszziebura.spring5RecipeProject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Ingredient;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.IngredientRepository;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.mateuszziebura.spring5RecipeProject.repositories.UnitOfMeasureRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientCommandToIngredient, ingredientToIngredientCommand, recipeRepository, unitOfMeasureRepository, ingredientRepository);
    }

    @Test
    void findByRecipeUrlAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setUrl("test");
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setRecipe(recipe);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);
        ingredient2.setRecipe(recipe);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setRecipe(recipe);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findByUrl(anyString())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeUrlAndIngredientId("test", 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findByUrl(anyString());
    }
    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);
        when(ingredientCommandToIngredient.convert(command)).thenReturn(new Ingredient());
        //when

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    void deleteIngredient() {
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        recipe.getIngredients().add(ingredient);

        ingredientService.deleteIngredient(ingredient,recipe);

        verify(ingredientRepository).delete(any());
        assertEquals(0,recipe.getIngredients().size());


    }
}