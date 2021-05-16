package org.mateuszziebura.spring5RecipeProject.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(String check, MultipartFile file);
}
