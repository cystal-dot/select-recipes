package com.example.apps.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.apps.bean.Cooking;
import com.example.apps.service.GetCookingService;
import com.example.apps.service.GetIngredientService;
import com.example.apps.service.UpdateCookingService;
import com.example.apps.service.UpdateIngredientService;

@Controller
@RequestMapping()
public class TotalViewContoller {

    @Autowired
    private UpdateCookingService updateCookingService;

    @Autowired
    private UpdateIngredientService updateIngredientService;

    @Autowired
    private GetCookingService getCookingService;

    @Autowired
    private GetIngredientService getIngredientService;

    //cookingテーブルとingredientsテーブルに登録
    //登録ができない
    @RequestMapping(path = "registdish", method = RequestMethod.POST)
    public String registDish(@RequestParam("DishName") String DishName,@RequestParam("Genre") String Genre,@RequestParam("Ingredient1") String Ingredient1,@RequestParam("Ingredient2") String Ingredient2,@RequestParam("Ingredient3") String Ingredient3,@RequestParam("Ingredient4") String Ingredient4,Model model) {

        updateCookingService.insertDishNameAndGenre(DishName,Genre);//cookingテーブルに名前とジャンルを登録

        int LatestDishId = getCookingService.getLatestDishId();//最新のDishIdを取得

        updateIngredientService.InsertIngredients(LatestDishId,Ingredient1,Ingredient2,Ingredient3,Ingredient4);//材料登録

        List<Cooking> allIngredients = getIngredientService.getAllIngredients();//すべてのIDと対応する材料をcookingに入れてる

        model.addAttribute("allIngredients", allIngredients);//材料一覧をタイムリーフに渡す

        return "view";//登録完了ページ作ったらそっちに飛ばしたい
    }

    //編集作業。editrecipeページに飛ばして
    @RequestMapping(value="editrecipe", method=RequestMethod.POST)
    public String EditRecipe(@RequestParam("EditId") String EditId,Model model) {
        
        //材料と取得してインスタンスに入れる
        Cooking cooking = getCookingService.getIngredients(Integer.parseInt(EditId));//渡す用のインスタンス

        List<Map<String, Object>> getIngredientList = null;//材料を一時保管するためのインスタンス
        getIngredientList = getIngredientService.getIngredientInfo(Integer.parseInt(EditId));//材料取得

        //名前取得
        String DishName = getCookingService.getDishName(Integer.parseInt(EditId));

        //ジャンル取得
        String Genre = getCookingService.getGenre(Integer.parseInt(EditId));

        //cookingインスタンスに格納
        for(Map<String,Object> map : getIngredientList){
            cooking.setDishId(Integer.parseInt(EditId));
            cooking.setDishname(DishName);
            cooking.setGenre(Genre);
            cooking.setIngredient1((String)map.get("ingredient1"));
            cooking.setIngredient2((String)map.get("ingredient2"));
            cooking.setIngredient3((String)map.get("ingredient3"));
            cooking.setIngredient4((String)map.get("ingredient4"));
        }
        model.addAttribute(cooking);
        return "editrecipe";
    }
}