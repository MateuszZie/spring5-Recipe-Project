package org.mateuszziebura.spring5RecipeProject.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mateuszziebura.spring5RecipeProject.commands.RecipeCommand;
import org.mateuszziebura.spring5RecipeProject.converters.RecipeCommandToRecipe;
import org.mateuszziebura.spring5RecipeProject.converters.RecipeToRecipeCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.exceptions.NotFoundException;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    void findById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void findByURL() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findByUrl(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findByUrl("test");

        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findByUrl(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipeCommandByUrlTest() {
        Recipe recipe = new Recipe();
        recipe.setUrl("test");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findByUrl(anyString())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setUrl("test");

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandByUrl = recipeService.findCommandByUrl("test");

        assertNotNull(commandByUrl);
        verify(recipeRepository, times(1)).findByUrl(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        Long idToDelete = 2L;

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
    @Test
    public void getRecipeByIdTestNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findByUrl(anyString())).thenReturn(recipeOptional);

        NotFoundException notFoundException = assertThrows(
                NotFoundException.class, () -> recipeService.findByUrl("test")
        );
        assertTrue(notFoundException.getMessage().contains("Recipe Not Found"));
        //should go boom
    }
}