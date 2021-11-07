package com.example.apps.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UseSelectController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //cookingテーブルの全データ抽出クエリ実行
    @RequestMapping(path = "/dishes",method=RequestMethod.GET)
    public String dishesAll() {
        return jdbcTemplate.queryForList("SELECT * FROM cooking").toString();
    }

    //cookingテーブルのID抽出
    @RequestMapping(path="/dishes/{id}",method=RequestMethod.GET)
    public String dishMatchId(@PathVariable String id){
        List<Map<String,Object>> list;
        list = jdbcTemplate.queryForList("SELECT * FROM cooking WHERE id = ?",id);
        return list.toString();
    }

    //cookingテーブルの最後尾のDishIdを取得
    public String LatestDishId() {
        return jdbcTemplate.queryForList("SELECT * FROM cooking ORDER BY DishId DESC LIMIT 1").toString();
    }

}
