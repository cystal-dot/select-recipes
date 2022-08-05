package com.example.apps.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.example.apps.bean.Cooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UseSelecterDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //ingredientsテーブルからDishIdを元に材料を抽出
    public List<Cooking> getAllIngredients(String DishId){
        final String GetIngredientsSQL = "SELECT Ingredient1,Ingredient2,Ingredient3,Ingredient4 FROM Ingredients WHERE DishId = ?";
        List<Map<String, Object>> getIngredientList = jdbcTemplate.queryForList(GetIngredientsSQL,DishId);
        System.out.println("リスト＝"+getIngredientList);
            
        //return用のリスト
        List<Cooking> ingredientsList = new ArrayList<>();

        //取得したデータをreturn用の変数に格納
        for(Map<String,Object>map : getIngredientList){
            System.out.println("Map="+map);
    
            Cooking cooking = new Cooking();
            cooking.setIngredient1((String)map.get("ingredient1"));
            cooking.setIngredient2((String)map.get("ingredient2"));
            cooking.setIngredient3((String)map.get("ingredient3"));
            cooking.setIngredient4((String)map.get("ingredient4"));
    
            //return用のListに追加
            ingredientsList.add(cooking);
        }
        return ingredientsList;
    }
}