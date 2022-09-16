package com.kucuktech.lab.recipes.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {
    private static final long serialVersionUID = 7588475689729952848L;

    private String key;
    private Operator operator;
    private FieldType fieldType;
    private transient Object value;

    /* // TODO:
    private transient Object valueTo;
    private transient List<Object> values;
     */
}