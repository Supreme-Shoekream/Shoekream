package com.supreme.shoekream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MemberPageController {
    @GetMapping(path="login")   //http://localhost:8889/login
    public ModelAndView login(){
        return new ModelAndView("/login/login");
    }

    @GetMapping(path="join")   //http://localhost:8889/join
    public ModelAndView join(){
        return new ModelAndView("/login/join");
    }

    @GetMapping(path="find_email")   //http://localhost:8889/find_email
    public ModelAndView find_email(){
        return new ModelAndView("/login/find_email");
    }

    @GetMapping(path="find_password")   //http://localhost:8889/find_password
    public ModelAndView find_password(){
        return new ModelAndView("/login/find_password");
    }



    // notice controller
    @GetMapping(path="faq")   //http://localhost:8889/faq
    public ModelAndView faq(){
        return new ModelAndView("/notice/faq");
    }

    @GetMapping(path="auth_policy")   //http://localhost:8889/auth_policy
    public ModelAndView auth_policy(){
        return new ModelAndView("/notice/auth_policy");
    }

    @GetMapping(path="notice")   //http://localhost:8889/notice
    public ModelAndView notice(){
        return new ModelAndView("/notice/notice");
    }
}
