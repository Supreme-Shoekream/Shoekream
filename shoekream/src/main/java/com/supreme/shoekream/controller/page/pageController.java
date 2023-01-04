package com.supreme.shoekream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class pageController {

    @GetMapping(path="buy")   //http://localhost:8889/buy
    public ModelAndView buy(){
        return new ModelAndView("/buy/buy");
    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path="buyselect")   //http://localhost:8889/buyselect
    public ModelAndView buyselect(){
        return new ModelAndView("/buy/buySelect");
    }
}
