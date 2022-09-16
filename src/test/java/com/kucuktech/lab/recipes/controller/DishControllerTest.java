package com.kucuktech.lab.recipes.controller;

import com.kucuktech.lab.recipes.model.Dish;
import com.kucuktech.lab.recipes.request.FieldType;
import com.kucuktech.lab.recipes.request.FilterRequest;
import com.kucuktech.lab.recipes.request.Operator;
import com.kucuktech.lab.recipes.request.SearchRequest;
import com.kucuktech.lab.recipes.service.DishService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DishControllerTest {

    @InjectMocks
    private DishController dishController;

    @Mock
    private DishService dishService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void initMocks() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreate() {
        final Dish dish = Dish.builder()
                .id(1)
                .vegetarian(true)
                .servings(2)
                .ingredients("[\"potatoes\"]")
                .instructions("Put the potatoes in the air fryer.")
                .build();

        dishController.create(dish);
        verify(dishService, times(1)).saveDish(eq(dish));
    }

    @Test
    void testSearch() {
        final SearchRequest searchRequest = SearchRequest.builder()
                .filters(List.of(FilterRequest.builder()
                        .key("servings")
                        .operator(Operator.EQUAL)
                        .fieldType(FieldType.INTEGER)
                        .value("1")
                        .build()))
                .build();

        dishController.search(searchRequest);
        verify(dishService, times(1)).searchDish(eq(searchRequest));
    }

    @Test
    void testUpdate() {
        final Dish dish = Dish.builder()
                .id(1)
                .vegetarian(true)
                .servings(2)
                .ingredients("[\"potatoes\"]")
                .instructions("Put the potatoes in the air fryer.")
                .build();

        dishController.update(1, dish);
        verify(dishService, times(1)).updateDish(eq(1), eq(dish));
    }

    @Test
    void testDelete() {
        dishController.delete(1);
        verify(dishService, times(1)).deleteDish(eq(1));
    }
}
