package org.mateuszziebura.spring5RecipeProject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.mateuszziebura.spring5RecipeProject.services.RecipeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class RecipeControllerTest {

    @Mock
    RecipeService repositories;

    @InjectMocks
    RecipeController recipeController;

    MockMvc mockMvc;

    @Mock
    Model model;

    Recipe recipe;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe = new Recipe();
//        recipe.setCookTime(20);
//        recipe.setPrepTime(10);
    }
    @Test
    void testMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(repositories.findByUrl("result")).thenReturn(recipe);

        mockMvc.perform(get("/recipe?check=result"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe"))
                .andExpect(model().attribute("recipe",recipe))
                .andExpect(model().attribute("total",""));

    }
    @Test
    void recipe() {
        when(repositories.findByUrl("result")).thenReturn(recipe);
        String result = recipeController.recipe("result",model);
        assertEquals("recipe/recipe",result);
        verify(model).addAttribute("recipe",recipe);
        verify(repositories).findByUrl("result");
//        verify(model).addAttribute("total",recipe.getPrepTime()+recipe.getCookTime());
    }
}