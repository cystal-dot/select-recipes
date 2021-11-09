package com.example.apps.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.apps.bean.Cooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class SelectRecipesController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //選択する料理の数
    int selective = 7;

    //料理をランダムに7つセレクトして返す
    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String RandomSelectRecipes(Model model){
        //レシピ総数とってくる.それ以下の数字をランダムに7つ生成。生成したものをIDとして選択する。
        //総数とってくる
        final String GetLatestDishIdSQL = "SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1";
        int LatestDishId = jdbcTemplate.queryForObject(GetLatestDishIdSQL, Integer.class);//最新のDishIdを取得

        //LatestDishId以下の数字をランダムに7つ生成してリストに入れる
        List<Integer> SelectiveList= new ArrayList<Integer>();//ランダムに生成したID収納用リスト
        Random random = new Random();
        for(int i=0;i<selective;i++){
            int randomValue = random.nextInt(LatestDishId)+1;
            if(!SelectiveList.contains(randomValue)){//被らなければリストに加える
                SelectiveList.add(randomValue);
            }else{
                i--;
            }
        }

        //IDからレシピを取得
        List<Cooking> SelectedRecipesList = new ArrayList<>();//Listにレシピをいれる
        for(int i=0;i<SelectiveList.size();i++){//レシピリストを作成
            SelectedRecipesList.add(this.getIngredients(SelectiveList.get(i)));
        }

        model.addAttribute("SelectedRecipesList",SelectedRecipesList);

        return "select";
    }

    public Cooking getIngredients(int DishId){//引数はidだからそれを元に全材料取得したい
        
        List<Map<String, Object>> getIngredientList = null;
        Cooking cooking = new Cooking();
        final String GetIngredientsSQL = "SELECT Ingredient1,Ingredient2,Ingredient3,Ingredient4 FROM ingredients WHERE DishId = "+DishId;
        getIngredientList = jdbcTemplate.queryForList(GetIngredientsSQL);

        //idから料理名取得
        final String GetDishNameSQL = "SELECT DishName from cooking where DishId = "+DishId;
        String DishName = jdbcTemplate.queryForObject(GetDishNameSQL,String.class);
        //ジャンル取得
        final String GetGenreSQL = "SELECT Genre FROM cooking WHERE DishId =" + DishId;
        String Genre = jdbcTemplate.queryForObject(GetGenreSQL, String.class);

        //取得したデータをreturn用の変数に格納
        for(Map<String,Object> map : getIngredientList){
            cooking.setDishId(DishId);
            cooking.setDishname(DishName);
            cooking.setGenre(Genre);
            cooking.setIngredient1((String)map.get("ingredient1"));
            cooking.setIngredient2((String)map.get("ingredient2"));
            cooking.setIngredient3((String)map.get("ingredient3"));
            cooking.setIngredient4((String)map.get("ingredient4"));
        }
        return cooking;
    }
}
