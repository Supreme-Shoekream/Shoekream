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

    @GetMapping(path="buying")   //http://localhost:8889/buying
    public ModelAndView buying() { return new ModelAndView("/my/buying"); }

    @GetMapping(path="buying_detail")   //http://localhost:8889/buying_detail
    public ModelAndView buying_detail() { return new ModelAndView("/my/buying_detail"); }

    @GetMapping(path="buying_end")   //http://localhost:8889/buying_end
    public ModelAndView buying_end() { return new ModelAndView("/my/buying_end"); }

    @GetMapping(path="selling")   //http://localhost:8889/selling
    public ModelAndView selling() { return new ModelAndView("/my/selling.html"); }

    @GetMapping(path="selling_detail")   //http://localhost:8889/selling_detail
    public ModelAndView selling_detail() { return new ModelAndView("/my/selling_detail.html"); }

}
