package org.mateuszziebura.spring5RecipeProject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepositories;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IndexPageControllerTest {

    IndexPageController indexPageController;

    @Mock
    Model model;

    @Mock
    RecipeRepositories repositories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexPageController= new IndexPageController(repositories);
    }
    @Test
    void getIndexPage(){
        String result = indexPageController.getIndexPage(model);
        assertEquals("index",result);
        verify(repositories,times(1)).findAll();
        verify(model,times(1)).addAttribute(eq("recipes"),anyCollection());

    }
}