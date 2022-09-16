package com.kucuktech.lab.recipes.controller;

import com.kucuktech.lab.recipes.repository.DishRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DishControllerIT {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SqlGroup({
            @Sql(value = "classpath:create_table.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:load_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void shouldRetrieveAllDishes() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/dish/search")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(getRequestBody("/all_dishes.json")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));

        assertThat(dishRepository.findAll()).hasSize(4);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:create_table.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:load_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void shouldRetrieveAllVegetarianDishes() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/dish/search")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(getRequestBody("/all_vegetarian_dishes.json")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 4)))
                .andExpect(jsonPath("$[*].vegetarian", containsInAnyOrder(true, true)))
                .andExpect(jsonPath("$[*].servings", containsInAnyOrder(1, 4)))
                .andExpect(jsonPath("$[*].ingredients", containsInAnyOrder("[tomato, pepper, eggplant]", "[potatoes]")))
                .andExpect(jsonPath("$[*].instructions", containsInAnyOrder("Put all ingredients in the oven.", "Put the potatoes in the air fryer.")));

        assertThat(dishRepository.findAll()).hasSize(4);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:create_table.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:load_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void shouldRetrievePotatoDishesFor4Persons() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/dish/search")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(getRequestBody("/potato_dishes_for_4_persons.json")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(4)))
                .andExpect(jsonPath("$[*].vegetarian", containsInAnyOrder(true)))
                .andExpect(jsonPath("$[*].servings", containsInAnyOrder(4)))
                .andExpect(jsonPath("$[*].ingredients", containsInAnyOrder("[potatoes]")))
                .andExpect(jsonPath("$[*].instructions", containsInAnyOrder("Put the potatoes in the air fryer.")));

        assertThat(dishRepository.findAll()).hasSize(4);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:create_table.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:load_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void shouldRetrieveOvenDishesWithoutSalmon() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/dish/search")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(getRequestBody("/oven_dishes_without_salmon.json")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1)))
                .andExpect(jsonPath("$[*].vegetarian", containsInAnyOrder(true)))
                .andExpect(jsonPath("$[*].servings", containsInAnyOrder(1)))
                .andExpect(jsonPath("$[*].ingredients", containsInAnyOrder("[tomato, pepper, eggplant]")))
                .andExpect(jsonPath("$[*].instructions", containsInAnyOrder("Put all ingredients in the oven.")));

        assertThat(dishRepository.findAll()).hasSize(4);
    }

    // TODO: Move to test generator class
    private String getRequestBody(String resourceName) throws URISyntaxException, IOException {
        return Files.readString(Paths.get(getClass().getResource(resourceName).toURI()));
    }
}
