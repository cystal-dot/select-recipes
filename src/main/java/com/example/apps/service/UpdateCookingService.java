package com.example.apps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apps.DAO.CookingInfoDAO;

@Service
public class UpdateCookingService {
    @Autowired
    private CookingInfoDAO cookingInfoDAO;


    public void updateDishNameAndGenreAndDishId(int dishId, String dishName, String genre) {
        cookingInfoDAO.updateDishNameAndGenreAndDishId(dishId, dishName, genre);
    }


    public void insertDishNameAndGenre(String dishName, String genre) {
        cookingInfoDAO.insertDishNameAndGenre(dishName, genre);
    }

}
