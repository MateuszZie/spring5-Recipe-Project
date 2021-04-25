package org.mateuszziebura.spring5RecipeProject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category= new Category();
    }

    @Test
    void getId() {
        Long check = 4L;
        category.setId(check);
        assertEquals(check,category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}