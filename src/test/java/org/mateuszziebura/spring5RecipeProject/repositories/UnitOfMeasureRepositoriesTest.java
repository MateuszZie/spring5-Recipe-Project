package org.mateuszziebura.spring5RecipeProject.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mateuszziebura.spring5RecipeProject.domain.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoriesTest {

    @Autowired
    UnitOfMeasureRepositories unitOfMeasureRepositories;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unit = unitOfMeasureRepositories.findByDescription("Tablespoon");

        assertEquals("Tablespoon",unit.orElse(null).getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> unit = unitOfMeasureRepositories.findByDescription("Cup");

        assertEquals("Cup",unit.orElse(null).getDescription());
    }
}