package com.example.apps.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GetIngredientInfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getLatestDishId() {
        return jdbcTemplate.queryForObject("SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1", Integer.class);
    }

    public List<Map<String, Object>> GetIngredients(int DishId) {
        final String GetIngredientsSQL = "SELECT Ingredient1,Ingredient2,Ingredient3,Ingredient4 FROM ingredients WHERE DishId = "+DishId;
        return jdbcTemplate.queryForList(GetIngredientsSQL);
    }
}
