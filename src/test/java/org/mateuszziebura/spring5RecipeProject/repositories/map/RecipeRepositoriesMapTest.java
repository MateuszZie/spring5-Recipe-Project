package org.mateuszziebura.spring5RecipeProject.repositories.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepositories;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

class RecipeRepositoriesMapTest {

    @Mock
    RecipeRepositories repositories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findAll() throws Exception{
        Recipe recipe = new Recipe();
        HashSet hashSet = new HashSet();
        hashSet.add(recipe);


        when(repositories.findAll()).thenReturn(hashSet);
        Iterable<Recipe> recipes= repositories.findAll();
        Set<Recipe> set = new HashSet<>();
        recipes.forEach(set::add);

        assertEquals(set.size(), 1);
        verify(repositories,times(1)).findAll();
    }
}