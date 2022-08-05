package com.example.apps.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.apps.bean.Cooking;

@Repository
public class CookingInfoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getLatestDishId() {
        return jdbcTemplate.queryForObject("SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1", Integer.class);
    }

    public String getDishName(int DishId) {
        return jdbcTemplate.queryForObject("SELECT DishName from cooking where DishId = "+DishId, String.class);
    }

    public String getGenreName(int DishId) {
        return jdbcTemplate.queryForObject("SELECT Genre from cooking where DishId = "+DishId, String.class);
    }

    public Cooking getCookingInfo(int dishId) {
        Cooking cooking = new Cooking();
        cooking.setDishId(dishId);
        cooking.setDishname(getDishName(dishId));
        cooking.setGenre(getGenreName(dishId));
        cooking.setIngredient1(jdbcTemplate.queryForObject("SELECT Ingredient1 from ingredients where DishId = "+dishId, String.class));
        cooking.setIngredient2(jdbcTemplate.queryForObject("SELECT Ingredient2 from ingredients where DishId = "+dishId, String.class));
        cooking.setIngredient3(jdbcTemplate.queryForObject("SELECT Ingredient3 from ingredients where DishId = "+dishId, String.class));
        cooking.setIngredient4(jdbcTemplate.queryForObject("SELECT Ingredient4 from ingredients where DishId = "+dishId, String.class));
        return cooking;
    }
}
