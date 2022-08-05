package com.example.apps.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
