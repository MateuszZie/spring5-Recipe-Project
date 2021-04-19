package org.mateuszziebura.spring5RecipeProject.controller;

import org.mateuszziebura.spring5RecipeProject.domain.Category;
import org.mateuszziebura.spring5RecipeProject.domain.UnitOfMeasure;
import org.mateuszziebura.spring5RecipeProject.repositories.CategoryRepositories;
import org.mateuszziebura.spring5RecipeProject.repositories.UnitOfMeasureRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexPageController {

    private final CategoryRepositories categoryRepositories;
    private final UnitOfMeasureRepositories unitOfMeasureRepositories;

    public IndexPageController(CategoryRepositories categoryRepositories, UnitOfMeasureRepositories unitOfMeasureRepositories) {
        this.categoryRepositories = categoryRepositories;
        this.unitOfMeasureRepositories = unitOfMeasureRepositories;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(){
        Optional<Category> optionalCategory = categoryRepositories.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepositories.findById(2L);
        System.out.println("Cat id = " + optionalCategory.get().getId());
        System.out.println("Uni id = " + unitOfMeasure.get().getDescription());
        return "index";
    }
}
