package org.mateuszziebura.spring5RecipeProject.repositories.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

class RecipeRepositoriesMapTest {

    @Mock
    RecipeRepository repositories;

    Recipe recipe;

    HashSet hashSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe = new Recipe();
        hashSet = new HashSet();
        hashSet.add(recipe);
    }

    @Test
    void findAll() throws Exception{
        when(repositories.findAll()).thenReturn(hashSet);
        Iterable<Recipe> recipes= repositories.findAll();
        Set<Recipe> set = new HashSet<>();
        recipes.forEach(set::add);

        assertEquals(set.size(), 1);
        verify(repositories,times(1)).findAll();
    }

    @Test
    void findByUrl() {
        when(repositories.findByUrl("someUrl").get()).thenReturn(recipe);
        Recipe returnRecipe = repositories.findByUrl("someUrl").get();

        assertNotNull(returnRecipe);
        verify(repositories,times(1)).findByUrl(any());
    }
    @Test
    void findByUrlNull() {
        when(repositories.findByUrl("wrong").get()).thenReturn(recipe);
        Recipe returnRecipe = repositories.findByUrl("someUrl").get();

        assertNull(returnRecipe);
        verify(repositories,times(1)).findByUrl(any());
    }
}