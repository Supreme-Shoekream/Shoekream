package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.service.MemberApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("") // http://localhost:8889/
public class MemberPageController {

    @Autowired
    private MemberApiLogicService memberApiLogicService;

    @GetMapping(path="login")   // http://localhost:8889/login
    public ModelAndView login(){
        return new ModelAndView("/login/login.html");
    }

//    @PostMapping(path="/loginOk")   // http://localhost:8889/loginOk
//    public String loginOk(HttpServletRequest request, String email, String memberPw){
//        if(memberApiLogicService.read(email, memberPw).getData() != null){
//            HttpSession session = request.getSession();
//            Long idx = memberApiLogicService.read(email, memberPw).getData().getIdx();
//            session.setAttribute("idx", idx);
//            session.setAttribute("email", email);
//            return "redirect:/index";
//        }else{
//            return "redirect:/login";
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

//    @ResponseBody
//    @GetMapping("/kakao")
//    public void kakaoCallback(@RequestParam String code){
//        System.out.println(code);
//    }

    @GetMapping(path="join")   //http://localhost:8889/join
    public ModelAndView join(){
        return new ModelAndView("/login/join");
    }

    @GetMapping(path="joinOk")   //http://localhost:8889/join
    public ModelAndView joinOk(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String memberPw =null;
        String name = null;
        String hp = null;
        String email = null;
        String shoeSize = null;

        if(session== null){
            System.out.println("세션이 없습니다");
            return new ModelAndView("/join");
        }else{
            memberPw = (String) session.getAttribute("memberPw");
            name = (String) session.getAttribute("name");
            hp = (String) session.getAttribute("hp");
            email = (String) session.getAttribute("email");
            shoeSize = (String) session.getAttribute("shoeSize");
            System.out.println("세션이 있습니다");
            return new ModelAndView("/index")
                    .addObject("memberPw", memberPw)
                    .addObject("name", name)
                    .addObject("hp", hp)
                    .addObject("email", email)
                    .addObject("shoeSize", shoeSize);
        }
    }

    @GetMapping(path="find_email")   //http://localhost:8889/find_email
    public ModelAndView find_email(){
        return new ModelAndView("/login/find_email");
    }

    @GetMapping(path="find_password")   //http://localhost:9999/find_password
    public ModelAndView find_password(){
        return new ModelAndView("/login/find_password");
    }

//     notice controller
    @GetMapping(path="faq")   //http://localhost:9999/faq
    public ModelAndView faq(){
        return new ModelAndView("/notice/faq");
    }

    @GetMapping(path="auth_policy")   //http://localhost:9999/auth_policy
    public ModelAndView auth_policy(){
        return new ModelAndView("/notice/auth_policy");
    }

    @GetMapping(path="notice")   //http://localhost:9999/notice
    public ModelAndView notice(){
        return new ModelAndView("/notice/notice");
    }
}
