package com.example.apps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController{

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