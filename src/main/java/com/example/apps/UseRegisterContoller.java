package com.example.apps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.example.apps.bean.Cooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UseRegisterContoller {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //cookingテーブルとingredientsテーブルに登録クエリ実行
    @RequestMapping(path = "registdish", method = RequestMethod.POST)
    public String registDish(@RequestParam("DishName") String DishName,@RequestParam("Genre") String Genre,@RequestParam("Ingredient1") String Ingredient1,@RequestParam("Ingredient2") String Ingredient2,@RequestParam("Ingredient3") String Ingredient3,@RequestParam("Ingredient4") String Ingredient4,Model model) {//nameとgenreをhtmlから持ってくる

        final String UpdateCookingSQL = "INSERT INTO cooking(DishName,Genre) VALUES(?,?)";
        jdbcTemplate.update(UpdateCookingSQL,DishName,Genre);//cookingテーブルに名前とジャンルを登録

        final String GetLatestDishIdSQL = "SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1";
        int LatestDishId = jdbcTemplate.queryForObject(GetLatestDishIdSQL, Integer.class);//最新のDishIdを取得

        //IDを元に材料を登録
        final String UpdateIngredientsSQL = "INSERT INTO ingredients(DishId,Ingredient1,Ingredient2,Ingredient3,Ingredient4) VALUES(" + LatestDishId + ",?,?,?,?)";
        jdbcTemplate.update(UpdateIngredientsSQL,Ingredient1,Ingredient2,Ingredient3,Ingredient4);//材料登録


        System.out.println("latestid"+LatestDishId);
        List<Cooking> allIngredients = this.getAllIngredients(LatestDishId);//すべてのIDと対応する材料をcookingに入れてる

        model.addAttribute("allIngredients", allIngredients);//材料一覧をタイムリーフに渡す

        return "view";//登録完了ページ作ったらそっちに飛ばしたい
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

            //取得したデータをreturn用の変数に格納
            for(Map<String,Object> map : getIngredientList){
        
                Cooking cooking = new Cooking();
                cooking.setDishId(i);
                cooking.setDishname(DishName);
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