package com.example.apps.controller;

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
public class TotalViewContoller {

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

        List<Cooking> allIngredients = this.getAllIngredients(LatestDishId);//すべてのIDと対応する材料をcookingに入れてる

        model.addAttribute("allIngredients", allIngredients);//材料一覧をタイムリーフに渡す

        return "view";//登録完了ページ作ったらそっちに飛ばしたい
    }

    //編集作業。editrecipeページに飛ばして
    @RequestMapping(value="editrecipe", method=RequestMethod.POST)
    public String EditRecipe(@RequestParam("EditId") String EditId,Model model) {
        //EditIdを元にテーブル表示してそれぞれ自由に編集させる
        //材料を取得してthymeleafに渡す(ここで初期値を表示させる.valueにingredient入れる)
        
        //材料と取得してインスタンスに入れる
        final String GetIngredientSQL = "SELECT Ingredient1,Ingredient2,Ingredient3,Ingredient4 FROM ingredients WHERE DishId =" + EditId;
        Cooking cooking = new Cooking();//渡す用のインスタンス
        List<Map<String, Object>> getIngredientList = null;//材料を一時保管するためのインスタンス
        getIngredientList = jdbcTemplate.queryForList(GetIngredientSQL);//材料取得
        //名前取得
        final String GetDishNameSQL = "SELECT Dishname FROM cooking WHERE DishId =" + EditId;
        String DishName = jdbcTemplate.queryForObject(GetDishNameSQL, String.class);
        //ジャンル取得
        final String GetGenreSQL = "SELECT Genre FROM cooking WHERE DishId =" + EditId;
        String Genre = jdbcTemplate.queryForObject(GetGenreSQL, String.class);

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