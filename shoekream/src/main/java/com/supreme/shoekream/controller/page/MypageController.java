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
    @GetMapping(path="buying")   //http://localhost:8889/my/buying
    public ModelAndView buying() { return new ModelAndView("/my/buying"); }
    @GetMapping(path="buying_detail")   //http://localhost:8889/my/buying_detail
    public ModelAndView buying_detail() { return new ModelAndView("/my/buying_detail"); }
    @GetMapping(path="buying_end")   //http://localhost:8889/my/buying_end
    public ModelAndView buying_end() { return new ModelAndView("/my/buying_end"); }
    @GetMapping(path="selling")   //http://localhost:8889/my/selling
    public ModelAndView selling() { return new ModelAndView("/my/selling.html"); }
    @GetMapping(path="selling_detail")   //http://localhost:8889/my/selling_detail
    public ModelAndView selling_detail() { return new ModelAndView("/my/selling_detail.html"); }
}
