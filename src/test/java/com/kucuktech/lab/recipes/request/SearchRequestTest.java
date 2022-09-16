package com.kucuktech.lab.recipes.request;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchRequestTest {

    @Test
    void testGetFilters() {
        final List<FilterRequest> filters = List.of(FilterRequest.builder()
                .key("servings")
                .operator(Operator.EQUAL)
                .fieldType(FieldType.INTEGER)
                .value("1")
                .build());

        final SearchRequest searchRequest = SearchRequest.builder()
                .filters(filters)
                .build();

        assertEquals(filters, searchRequest.getFilters());
    }

    @Test
    void testGetNoFiltersWhenThereAreNoFilters() {
        final SearchRequest searchRequest = SearchRequest.builder()
                .filters(null)
                .build();

        assertEquals(List.of(), searchRequest.getFilters());
    }
}
