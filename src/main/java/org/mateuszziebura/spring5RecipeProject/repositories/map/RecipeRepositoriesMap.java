package org.mateuszziebura.spring5RecipeProject.repositories.map;

import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepositories;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeRepositoriesMap implements RecipeRepositories {

    protected Map<Long, Recipe> map = new HashMap<>();

    @Override
    public Recipe findByUrl(String url){
        for(Map.Entry<Long,Recipe> recipe: map.entrySet()){
            if(recipe.getValue().getUrl().equals(url)){
                return recipe.getValue();
            }
        }
        return null;
    }
    @Override
    public <S extends org.mateuszziebura.spring5RecipeProject.domain.Recipe> S save(S s) {
        map.put(s.getId(),s);
        return s;
    }

    @Override
    public <S extends org.mateuszziebura.spring5RecipeProject.domain.Recipe> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<org.mateuszziebura.spring5RecipeProject.domain.Recipe> findById(java.lang.Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(java.lang.Long aLong) {
        return map.containsKey(aLong);
    }

    @Override
    public Iterable<org.mateuszziebura.spring5RecipeProject.domain.Recipe> findAll() {
        return map.values();
    }

    @Override
    public Iterable<org.mateuszziebura.spring5RecipeProject.domain.Recipe> findAllById(Iterable<java.lang.Long> iterable) {
        return map.values();
    }

    @Override
    public long count() {
        return map.size();
    }

    @Override
    public void deleteById(java.lang.Long aLong) {
        map.remove(aLong);
    }

    @Override
    public void delete(org.mateuszziebura.spring5RecipeProject.domain.Recipe recipe) {
        map.remove(recipe.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends org.mateuszziebura.spring5RecipeProject.domain.Recipe> iterable) {

    }

    @Override
    public void deleteAll() {
        map.clear();
    }
}
