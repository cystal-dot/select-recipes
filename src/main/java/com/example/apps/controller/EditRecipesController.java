package com.example.apps.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.apps.bean.Cooking;
import com.example.apps.service.GetIngredientService;
import com.example.apps.service.UpdateCookingService;
import com.example.apps.service.UpdateIngredientService;

@Controller
@RequestMapping()
public class EditRecipesController { 

    @Autowired
    private UpdateCookingService updateCookingService;

    @Autowired
    private UpdateIngredientService updateIngredientService;

    @Autowired
    private GetIngredientService getIngredientService;

    DefaultController defaultController;
    
    //編集作業.editrecipeページに飛ばして
    @RequestMapping(value="/editinformation", method=RequestMethod.POST)
    public String EditRecipe(@RequestParam("DishId") String DishId,@RequestParam("DishName") String DishName,@RequestParam("Genre") String Genre,@RequestParam("Ingredient1") String Ingredient1,@RequestParam("Ingredient2") String Ingredient2,@RequestParam("Ingredient3") String Ingredient3,@RequestParam("Ingredient4") String Ingredient4,Model model) {

        //IDを元に材料と名前を更新
        updateCookingService.updateDishNameAndGenreAndDishId(Integer.parseInt(DishId), DishName, Genre);
        updateIngredientService.updateIngredients(Integer.parseInt(DishId), Ingredient1, Ingredient2, Ingredient3, Ingredient4);

        List<Cooking> allingredientsList = getIngredientService.getAllIngredients();
        model.addAttribute("allIngredients", allingredientsList);

        return "view";
    }
}