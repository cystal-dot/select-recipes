package com.example.apps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpdateIngredientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void updateIngredients(int dishId, String ingredient1, String ingredient2, String ingredient3, String ingredient4) {
        jdbcTemplate.update("UPDATE ingredients SET Ingredient1 = ?, Ingredient2 = ?, Ingredient3 = ?, Ingredient4 = ? WHERE DishId = ?", ingredient1, ingredient2, ingredient3, ingredient4, dishId);
    }
}
