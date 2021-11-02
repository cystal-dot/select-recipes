package com.example.apps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

import com.example.apps.DAO.UseSelecterDAO;
import com.example.apps.bean.Cooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UseRegisterContoller {

    private UseSelecterDAO useSelecterDAO = new UseSelecterDAO();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //cookingテーブルとingredientsテーブルに登録クエリ実行
    @RequestMapping(path = "registdish", method = RequestMethod.POST)
    public String registDish(@RequestParam("DishName") String DishName,@RequestParam("Genre") String Genre,@RequestParam("Ingredient1") String Ingredient1,@RequestParam("Ingredient2") String Ingredient2,@RequestParam("Ingredient3") String Ingredient3,@RequestParam("Ingredient4") String Ingredient4,Model model) {//nameとgenreをhtmlから持ってくる

        final String UpdateCookingSQL = "INSERT INTO cooking(DishName,Genre) VALUES(?,?)";
        jdbcTemplate.update(UpdateCookingSQL,DishName,Genre);//cookingテーブルに名前とジャンルを登録

        final String GetLatestDishIdSQL = "SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1";
        int LatestDishId = jdbcTemplate.queryForObject(GetLatestDishIdSQL, Integer.class);//最新のDishIdのリストを取得
        final String UpdateIngredientsSQL = "INSERT INTO ingredients(DishId,Ingredient1,Ingredient2,Ingredient3,Ingredient4) VALUES(" + LatestDishId + ",?,?,?,?)";

        jdbcTemplate.update(UpdateIngredientsSQL,Ingredient1,Ingredient2,Ingredient3,Ingredient4);//材料登録

        // String DishId = Integer.toString(LatestDishId);無限リロードの原因ここ
        // List<Cooking> allIngredients = useSelecterDAO.getAllIngredients("1");//とりあえず最新のレシピ。並べるならここを各dishidごとに取得して並べる。
        // System.out.println("check"+allIngredients);

        // model.addAttribute("allIngredients", allIngredients);//材料一覧

        return "view";//登録完了ページ作ったらそっちに飛ばしたい
    }
}
