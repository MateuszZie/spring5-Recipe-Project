package org.mateuszziebura.spring5RecipeProject.controller;

import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {

    private final RecipeRepositories recipeRepositories;

    public IndexPageController(RecipeRepositories recipeRepositories) {
        this.recipeRepositories = recipeRepositories;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeRepositories.findAll());
        return "index";
    }
}
