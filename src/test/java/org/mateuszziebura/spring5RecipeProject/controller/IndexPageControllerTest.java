package org.mateuszziebura.spring5RecipeProject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


class IndexPageControllerTest {

    IndexPageController indexPageController;

    @Mock
    Model model;

    @Mock
    RecipeRepository repositories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexPageController= new IndexPageController(repositories);
    }
    @Test
    void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexPageController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    @Test
    void getIndexPage(){

        Map<Long,Recipe> map = new HashMap<>();
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        map.put(1L,recipe);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        map.put(2L,recipe2);

        when(repositories.findAll()).thenReturn(map.values());

        ArgumentCaptor<Iterable<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Collection.class);

        String result = indexPageController.getIndexPage(model);
        assertEquals("index",result);
        verify(repositories,times(1)).findAll();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Map<Long,Recipe> recipeMap = new HashMap<>();
        argumentCaptor.getValue().forEach(recipe1 -> {
            recipeMap.put(recipe1.getId(),recipe1);
        });
        assertEquals(2, recipeMap.size());

    }
}