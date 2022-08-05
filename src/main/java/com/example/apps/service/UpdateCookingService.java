package com.example.apps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpdateCookingService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updateCooking(int dishId, String dishName, String genre) {
        jdbcTemplate.update("UPDATE cooking SET DishName = ?, Genre = ? WHERE DishId = ?", dishName, genre, dishId);
    }

}
