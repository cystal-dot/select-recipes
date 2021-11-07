package com.example.apps.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class EditRecipesController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    //編集作業。editrecipeページに飛ばして
    @RequestMapping(value="editinformation", method=RequestMethod.POST)
    public String EditRecipe(@RequestParam("DishName") String DishName,@RequestParam("Ingredient1") String Ingredient1,@RequestParam("Ingredient2") String Ingredient2,@RequestParam("Ingredient3") String Ingredient3,@RequestParam("Ingredient4") String Ingredient4) {

        return "editrecipe";
    }
}