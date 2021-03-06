package org.mateuszziebura.spring5RecipeProject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.commands.RecipeCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.exceptions.NotFoundException;
import org.mateuszziebura.spring5RecipeProject.services.RecipeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

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
        when(recipeService.findByUrl("result")).thenReturn(recipe);

        mockMvc.perform(get("/recipe?check=result"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe"))
                .andExpect(model().attribute("recipe",recipe))
                .andExpect(model().attribute("total",""));

    }
    @Test
    void recipe() {
        when(recipeService.findByUrl("result")).thenReturn(recipe);
        String result = recipeController.recipe("result",model);
        assertEquals("recipe/recipe",result);
        verify(model).addAttribute("recipe",recipe);
        verify(recipeService).findByUrl("result");
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

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
                .param("description", "some string")
                .param("directions", "some directions")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe?check="+test));
    }
    @Test
    public void testPostNewRecipeFormValidationFail() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setUrl("test");

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")

        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipeform"));
    }
    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setUrl("test");

        when(recipeService.findCommandByUrl(anyString())).thenReturn(command);

        mockMvc.perform(get("/recipe/update?check=test"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }
    @Test
    public void testDeleteAction() throws Exception {
        when(recipeService.findByUrl(anyString())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/delete?check=test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }
    @Test
    public void testGetRecipeNotFound() throws Exception {

        when(recipeService.findByUrl(anyString())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe?check=result"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));

    }
}