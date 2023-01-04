package com.supreme.shoekream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MypageController {

    @GetMapping(path="/profile")
    public ModelAndView profile(){
        return new ModelAndView("my/profile");
    }
    @GetMapping(path="address")
    public ModelAndView address(){
        return new ModelAndView("/my/address");
    }
    @GetMapping(path="payment")
    public ModelAndView payment(){
        return new ModelAndView("/my/payment");
    }
    @GetMapping(path="account")
    public ModelAndView account(){
        return new ModelAndView("/my/aacount");
    }
    @GetMapping(path="receipt")
    public ModelAndView receipt(){
        return new ModelAndView("/my/receipt");
    }
    @GetMapping(path="point")
    public ModelAndView point(){
        return new ModelAndView("/my/point");
    }
    @GetMapping(path="withdrawal")
    public ModelAndView withdrawal(){
        return new ModelAndView("/my/withdrawal");
    }

}
