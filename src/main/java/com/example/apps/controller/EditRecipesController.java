package com.example.apps.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.apps.bean.Cooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class EditRecipesController { 

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //編集作業。editrecipeページに飛ばして
    @RequestMapping(value="/editinformation", method=RequestMethod.POST)
    public String EditRecipe(@RequestParam("DishId") String DishId,@RequestParam("DishName") String DishName,@RequestParam("Genre") String Genre,@RequestParam("Ingredient1") String Ingredient1,@RequestParam("Ingredient2") String Ingredient2,@RequestParam("Ingredient3") String Ingredient3,@RequestParam("Ingredient4") String Ingredient4,Model model) {

        //IDを元に材料と名前を更新
        final String UpdateDishnameSQL ="UPDATE cooking SET Dishname=?,Genre=? WHERE DishId ="+DishId;
        final String UpdateIngredientsSQL ="UPDATE ingredients SET Ingredient1=?,Ingredient2=?,Ingredient3=?,Ingredient4=? WHERE DishId =" + DishId;
        jdbcTemplate.update(UpdateDishnameSQL, DishName,Genre);//名前変更a
        jdbcTemplate.update(UpdateIngredientsSQL,Ingredient1,Ingredient2,Ingredient3,Ingredient4);//材料更新

        final String GetLatestDishIdSQL = "SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1";
        int LatestDishId = jdbcTemplate.queryForObject(GetLatestDishIdSQL, Integer.class);//最新のDishIdを取得
        List<Cooking> allIngredients = this.getAllIngredients(LatestDishId);//すべてのIDと対応する材料をcookingに入れてる

        model.addAttribute("allIngredients", allIngredients);//材料一覧をタイムリーフに渡す

        return "view";//viewに飛ばす
    }

    public List<Cooking> getAllIngredients(int DishId){//引数はingredientテーブルの要素数だからそれを元に全材料取得したい
        
        List<Cooking> ingredientsList = new ArrayList<>();//return用のリスト
        List<Map<String, Object>> getIngredientList = null;
        for(int i=1;i<DishId+1;i++){
            final String GetIngredientsSQL = "SELECT Ingredient1,Ingredient2,Ingredient3,Ingredient4 FROM ingredients WHERE DishId = "+i;
            getIngredientList = jdbcTemplate.queryForList(GetIngredientsSQL);

            //idから料理名取得
            final String GetDishNameSQL = "SELECT DishName from cooking where DishId = "+i;
            String DishName = jdbcTemplate.queryForObject(GetDishNameSQL,String.class);
            //ジャンル取得
            final String GetGenreSQL = "SELECT Genre FROM cooking WHERE DishId =" + i;
            String Genre = jdbcTemplate.queryForObject(GetGenreSQL, String.class);

            //取得したデータをreturn用の変数に格納
            for(Map<String,Object> map : getIngredientList){
        
                Cooking cooking = new Cooking();
                cooking.setDishId(i);
                cooking.setDishname(DishName);
                cooking.setGenre(Genre);
                cooking.setIngredient1((String)map.get("ingredient1"));
                cooking.setIngredient2((String)map.get("ingredient2"));
                cooking.setIngredient3((String)map.get("ingredient3"));
                cooking.setIngredient4((String)map.get("ingredient4"));

                //return用のListに追加
                ingredientsList.add(cooking);
            }
        }
        return ingredientsList;
    }
}