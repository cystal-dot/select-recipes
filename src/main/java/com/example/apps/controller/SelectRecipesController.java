package com.example.apps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.apps.bean.Cooking;
import com.example.apps.service.GetCookingService;
import com.example.apps.service.RandomSelectService;

@Controller
@RequestMapping()
public class SelectRecipesController {

    @Autowired
    GetCookingService getCookingService;

    @Autowired
    RandomSelectService randomSelectService;

    //選択する料理の数
    int selective = 7;

    //料理をランダムに7つセレクトして返す
    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String RandomSelectRecipes(Model model){
        //レシピ総数とってくる.それ以下の数字をランダムに7つ生成。生成したものをIDとして選択する。
        int LatestDishId = getCookingService.getLatestDishId();

        List<Cooking> SelectedRecipesList = randomSelectService.RandomSelectRecipes(selective, LatestDishId);

        model.addAttribute("SelectedRecipesList",SelectedRecipesList);

        return "select";
    }
}
