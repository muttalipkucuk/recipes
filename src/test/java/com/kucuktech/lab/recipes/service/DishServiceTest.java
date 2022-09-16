package com.kucuktech.lab.recipes.service;

import com.kucuktech.lab.recipes.model.Dish;
import com.kucuktech.lab.recipes.repository.DishRepository;
import com.kucuktech.lab.recipes.request.FieldType;
import com.kucuktech.lab.recipes.request.FilterRequest;
import com.kucuktech.lab.recipes.request.Operator;
import com.kucuktech.lab.recipes.request.SearchRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DishServiceTest {

    @InjectMocks
    private DishService dishService;

    @Mock
    private DishRepository dishRepository;

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
    void shouldCreateDish() {
        final Dish dish = createDish(1);
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        final Dish dishSaved = dishService.saveDish(dish);
        assertEquals(dish, dishSaved);
    }

    @Test
    void shouldSearchDish() {
        // TODO: Duplicate code
        final SearchRequest searchRequest = SearchRequest.builder()
                .filters(List.of(FilterRequest.builder()
                        .key("servings")
                        .operator(Operator.EQUAL)
                        .fieldType(FieldType.INTEGER)
                        .value("1")
                        .build()))
                .build();

        final List<Dish> dishes = List.of(createDish(1));
        when(dishRepository.findAll(any(Specification.class))).thenReturn(dishes);

        final List<Dish> dishesFound = dishService.searchDish(searchRequest);
        assertEquals(dishes, dishesFound);
    }

    @Test
    void shouldUpdateNonExistingDish() {
        final Dish dish = createDish(1);
        when(dishRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        final Dish dishUpdated = dishService.updateDish(1, dish);
        assertEquals(dish, dishUpdated);
    }

    @Test
    void shouldUpdateExistingDish() {
        final Dish dishExisting = createDish(1);
        final Dish dishNew = createDish(2);

        when(dishRepository.findById(anyInt())).thenReturn(Optional.of(dishExisting));
        when(dishRepository.save(any(Dish.class))).thenReturn(dishNew);

        final Dish dishUpdated = dishService.updateDish(1, dishNew);
        assertEquals(dishNew, dishUpdated);
    }

    private Dish createDish(int servings) {
        return Dish.builder()
                .id(1)
                .vegetarian(true)
                .servings(servings)
                .ingredients("[\"potatoes\"]")
                .instructions("Put the potatoes in the air fryer.")
                .build();

    }
}
