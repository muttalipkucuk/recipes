package com.kucuktech.lab.recipes.repository;

import com.kucuktech.lab.recipes.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer>, JpaSpecificationExecutor<Dish> {
}
