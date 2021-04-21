package org.mateuszziebura.spring5RecipeProject.repositories.map;

import org.mateuszziebura.spring5RecipeProject.domain.Category;
import org.mateuszziebura.spring5RecipeProject.repositories.CategoryRepositories;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryRepositoriesMap implements CategoryRepositories {

    protected Map<Long, Category> map = new HashMap<>();

    @Override
    public <S extends Category> S save(S s) {
        map.put(s.getId(),s);
        return s;
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return map.containsKey(aLong);
    }

    @Override
    public Iterable<Category> findAll() {
        return map.values();
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Long> iterable) {
        return map.values();
    }

    @Override
    public long count() {
        return map.size();
    }

    @Override
    public void deleteById(Long aLong) {
        map.remove(aLong);
    }

    @Override
    public void delete(Category recipe) {
        map.remove(recipe.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends Category> iterable) {

    }

    @Override
    public void deleteAll() {
        map.clear();
    }

    @Override
    public Optional<Category> findByDescription(String description) {
        return Optional.empty();
    }
}
