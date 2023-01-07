package com.supreme.shoekream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/social")
public class SocialPageController {

    @GetMapping(path="")    // http://localhost:8889/social
    public ModelAndView trending(){
        return new ModelAndView("social/trending");
    }

    @GetMapping(path = "/newest")   // http://localhost:8889/social/newest
    public ModelAndView newest(){ return new ModelAndView("social/newest"); }

    @GetMapping(path = "/following")    // http://localhost:8889/social/following
    public ModelAndView following(){ return new ModelAndView("social/following"); }

    @GetMapping(path = "/myprofile")        // http://localhost:8889/social/myprofile
    public ModelAndView myprofile(){ return new ModelAndView("social/myprofile"); }

    @GetMapping(path = "/style_profile_edit")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView style_profile_edit(){ return new ModelAndView("social/style_profile_edit"); }

    @GetMapping(path = "/social_product")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView style_profilsocial_producte_edit(){ return new ModelAndView("social/social_product"); }

    @GetMapping(path = "/users")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView users(){ return new ModelAndView("social/users"); }

    @GetMapping(path = "/upload")
    public ModelAndView upload(){ return new ModelAndView("social/upload"); }

}
