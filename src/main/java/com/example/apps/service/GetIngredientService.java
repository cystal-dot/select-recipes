package com.example.apps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apps.DAO.CookingInfoDAO;
import com.example.apps.DAO.IngredientInfoDAO;
import com.example.apps.bean.Cooking;

@Service
public class GetIngredientService {

    @Autowired
    private IngredientInfoDAO IngredientDAO;

    @Autowired
    private CookingInfoDAO CookingDAO;

    final String GetLatestDishIdSQL = "SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1";
    
    public List<Cooking> getAllIngredients(){//引数はingredientテーブルの要素数だからそれを元に全材料取得したい

        int LatestDishId = CookingDAO.getLatestDishId();//最新のDishIdを取得
        List<Cooking> ingredientsList = new ArrayList<>();//return用のリスト 
        List<Map<String, Object>> getIngredientList = null;

        for(int i=1;i<LatestDishId+1;i++){
            getIngredientList = IngredientDAO.GetIngredients(i);

            //idから料理名取得
            String DishName = CookingDAO.getDishName(i);

            //ジャンル取得
            String Genre = CookingDAO.getGenreName(i);

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
