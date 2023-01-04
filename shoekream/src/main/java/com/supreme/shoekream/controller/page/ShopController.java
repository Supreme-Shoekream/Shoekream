package com.supreme.shoekream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ShopController {

    @GetMapping(path="shop_search")   //http://localhost:8889/shop_search
    public ModelAndView shop_search() { return new ModelAndView("/shop/shop_search"); }



}
