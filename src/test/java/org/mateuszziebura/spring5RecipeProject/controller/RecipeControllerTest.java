package org.mateuszziebura.spring5RecipeProject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.commands.RecipeCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.mateuszziebura.spring5RecipeProject.services.RecipeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        recipe = new Recipe();
        recipe.setId(2L);
//        recipe.setCookTime(20);
//        recipe.setPrepTime(10);
    }
    @Test
    void testMockMvc() throws Exception {
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
        @Test
        public void testGetNewRecipeForm() throws Exception {
            RecipeCommand command = new RecipeCommand();

            mockMvc.perform(get("/recipe/new"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("recipe/recipeform"))
                    .andExpect(model().attributeExists("recipe"));
        }
    @Test
    public void testPostNewRecipeForm() throws Exception {
        String test ="test";
        RecipeCommand command = new RecipeCommand();
        command.setUrl(test);

        when(repositories.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "")
//                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe?check="+test));
    }
    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setUrl("test");

        when(repositories.findCommandByUrl(anyString())).thenReturn(command);

        mockMvc.perform(get("/recipe/update?check=test"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }
    @Test
    public void testDeleteAction() throws Exception {
        when(repositories.findByUrl(anyString())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/delete?check=test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(repositories, times(1)).deleteById(anyLong());
    }
}