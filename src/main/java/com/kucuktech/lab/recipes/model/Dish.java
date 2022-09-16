package com.kucuktech.lab.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Dish implements Serializable {
    private static final long serialVersionUID = -14991838991283827L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean vegetarian;
    private int servings;
    private String ingredients;   // TODO: Check
    private String instructions;
}
