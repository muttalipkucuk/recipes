package com.kucuktech.lab.recipes.service;

import com.kucuktech.lab.recipes.model.Dish;
import com.kucuktech.lab.recipes.repository.DishRepository;
import com.kucuktech.lab.recipes.request.SearchRequest;
import com.kucuktech.lab.recipes.request.SearchSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public List<Dish> searchDish(SearchRequest request) {
        SearchSpecification<Dish> specification = new SearchSpecification<>(request);
        return dishRepository.findAll(specification);
    }

    public Dish updateDish(Integer id, Dish dish) {
        final Dish dishToUpdate = dishRepository.findById(id)
                .map(dishFound -> {
                    dishFound.setVegetarian(dish.isVegetarian());
                    dishFound.setServings(dish.getServings());
                    dishFound.setIngredients(dish.getIngredients());
                    dishFound.setInstructions(dish.getInstructions());
                    return dishFound;
                }).orElse(dish);
        return dishRepository.save(dishToUpdate);
    }

    public void deleteDish(Integer id) {
        dishRepository.deleteById(id);
    }
}
