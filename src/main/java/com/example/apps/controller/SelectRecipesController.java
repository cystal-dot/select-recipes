package com.example.apps.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
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
    public String RandomSelectRecipes(){
        //レシピ総数とってくる.それ以下の数字をランダムに7つ生成。生成したものをIDとして選択する。
        //総数とってくる
        final String GetLatestDishIdSQL = "SELECT DishId FROM cooking ORDER BY DishId DESC LIMIT 1";
        int LatestDishId = jdbcTemplate.queryForObject(GetLatestDishIdSQL, Integer.class);//最新のDishIdを取得

        //LatestDishId以下の数字をランダムに7つ生成してリストに入れる
        List<Integer> SelectiveList= new ArrayList<Integer>();//ランダムに生成したID収納用リスト
        Random random = new Random();
        for(int i=0;i<selective;i++){
            int randomValue = random.nextInt(LatestDishId);
            if(!SelectiveList.contains(randomValue)){//被らなければリストに加える
                SelectiveList.add(randomValue);
            }else{
                i--;
            }
        }
        for(int i=0;i<selective;i++){
            System.out.println(SelectiveList.get(i));
        }
        return "select";
    }
}
