package com.example.apps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.apps.bean.Cooking;
import com.example.apps.service.GetIngredientService;

@Controller
@RequestMapping()
public class DefaultController{

    @Autowired
    private GetIngredientService getIngredientService;

    @RequestMapping("/")
    public String start(){
        return "start";
    }

    @RequestMapping("/view")
    public String view(Model model){
        //初回訪問時に材料リスト取得できるように

        List<Cooking> ingredientsList = getIngredientService.getAllIngredients();//一覧を持ってくる
        
        model.addAttribute("allIngredients", ingredientsList);//材料一覧をタイムリーフに渡す
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