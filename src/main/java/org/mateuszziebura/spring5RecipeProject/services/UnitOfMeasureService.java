package org.mateuszziebura.spring5RecipeProject.services;

import org.mateuszziebura.spring5RecipeProject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
