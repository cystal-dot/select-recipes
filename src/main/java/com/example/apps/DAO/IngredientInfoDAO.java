package com.example.apps.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientInfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getLatestDishId() {
        return jdbcTemplate.queryForObject("SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1", Integer.class);
    }

    public List<Map<String, Object>> GetIngredients(int DishId) {
        // final String GetIngredientsSQL = "SELECT Ingredient1,Ingredient2,Ingredient3,Ingredient4 FROM ingredients WHERE DishId = "+DishId;
        // return jdbcTemplate.queryForList(GetIngredientsSQL);
        return jdbcTemplate.queryForList("SELECT Ingredient1,Ingredient2,Ingredient3,Ingredient4 FROM ingredients WHERE DishId = "+DishId);
    }

    public void updateIngredients(int dishId, String ingredient1, String ingredient2, String ingredient3,
            String ingredient4) {
        jdbcTemplate.update("UPDATE ingredients SET Ingredient1 = ?, Ingredient2 = ?, Ingredient3 = ?, Ingredient4 = ? WHERE DishId = ?", ingredient1, ingredient2, ingredient3, ingredient4, dishId);
    }

    public void InsertIngredients(int latestDishId, String ingredient1, String ingredient2, String ingredient3,
            String ingredient4) {
        jdbcTemplate.update("INSERT INTO ingredients (DishId, Ingredient1, Ingredient2, Ingredient3, Ingredient4) VALUES (?, ?, ?, ?, ?)", latestDishId, ingredient1, ingredient2, ingredient3, ingredient4);
    }
}
