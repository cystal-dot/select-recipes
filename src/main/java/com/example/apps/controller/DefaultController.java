package com.example.apps.controller;

import java.sql.JDBCType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.sql.DataSource;

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
    public String view(){
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
}