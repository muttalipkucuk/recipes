package com.kucuktech.lab.recipes.controller;

import com.kucuktech.lab.recipes.model.Dish;
import com.kucuktech.lab.recipes.request.SearchRequest;
import com.kucuktech.lab.recipes.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish create(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> search(@RequestBody SearchRequest request) {
        return dishService.searchDish(request);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish update(@PathVariable("id") int id, @RequestBody Dish dish) {
        return dishService.updateDish(id, dish);
    }

    @DeleteMapping(value = "/{id}")
    public Void delete(@PathVariable("id") int id) {
        dishService.deleteDish(id);
        return null;
    }
}
