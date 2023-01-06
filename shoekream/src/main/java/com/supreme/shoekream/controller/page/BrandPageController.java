package com.supreme.shoekream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("brand") //http://localhost:8888/brand
public class BrandPageController {

    @GetMapping(path="") //http://localhost:8888/brand
    public ModelAndView brand(){
        return new ModelAndView("/brand.html");
    }


}
