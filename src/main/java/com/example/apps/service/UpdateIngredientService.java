package com.example.apps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apps.DAO.IngredientInfoDAO;

@Service
public class UpdateIngredientService {

    @Autowired
    private IngredientInfoDAO ingredientInfoDAO;
    
    public void updateIngredients(int dishId, String ingredient1, String ingredient2, String ingredient3, String ingredient4) {
        ingredientInfoDAO.updateIngredients(dishId, ingredient1, ingredient2, ingredient3, ingredient4);
    }

    public void InsertIngredients(int latestDishId, String ingredient1, String ingredient2, String ingredient3,
            String ingredient4) {
        ingredientInfoDAO.InsertIngredients(latestDishId, ingredient1, ingredient2, ingredient3, ingredient4);
    }
}
