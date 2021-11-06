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
public class DefaultController{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String start(){
        return "start";
    }

    @RequestMapping("/view")
    public String view(Model model){
        //初回訪問時に材料リスト取得できるように
        final String GetLatestDishIdSQL = "SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1";
        int LatestDishId = jdbcTemplate.queryForObject(GetLatestDishIdSQL, Integer.class);//最新のDishIdを取得

        List<Cooking> allIngredients = this.getAllIngredients(LatestDishId);//すべてのIDと対応する材料をcookingに入れてる
        
        model.addAttribute("allIngredients", allIngredients);//材料一覧をタイムリーフに渡す
        return "view";
    }

    @RequestMapping("/howto")
    public String howto(){
        return "howto";
    }

    @RequestMapping(path = "age", method = RequestMethod.POST)//どのurlからもらうか.
    public String age(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "age";
    }

    @RequestMapping(path = "hello", method = RequestMethod.POST)
    public String hello(@RequestParam("name") String name, @RequestParam("age") String age, Model model) {
        model.addAttribute("name", name);//age.htmlからパラメータを2つもらっているからattributeにそれぞれ追加する
        model.addAttribute("age", age);
        return "hello";
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