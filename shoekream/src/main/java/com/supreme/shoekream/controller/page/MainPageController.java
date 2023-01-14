package com.supreme.shoekream.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")    //http://localhost:8889
@RequiredArgsConstructor
public class MainPageController {

    @GetMapping(path="")   //http://localhost:8889/
    public ModelAndView index(){
        return new ModelAndView("/index");
    }   //viewName: 페이지이름이랑 같아야함
}
