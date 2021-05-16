package org.mateuszziebura.spring5RecipeProject.controller;

import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.services.ImageService;
import org.mateuszziebura.spring5RecipeProject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/image")
    public String showUploadForm(@RequestParam String check, Model model){
        model.addAttribute("recipe", recipeService.findCommandByUrl(check));

        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/image")
    public String handleImagePost(@RequestParam String check, @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(check, file);

        return "redirect:/recipe?check=" +check;
    }
}
