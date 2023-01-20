package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Follow;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.BoardRepository;
import com.supreme.shoekream.repository.FollowRepository;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class SocialPageController {
    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    private final FollowRepository followRepository;
    private final StyleLogicService styleLogicService;


    @GetMapping(path="/social")    // http://localhost:8889/social
    public ModelAndView trending(){
        return new ModelAndView("social/trending");
    }

    @GetMapping(path = "/social/newest")   // http://localhost:8889/social/newest
    public ModelAndView newest(){ return new ModelAndView("social/newest"); }


    @GetMapping(path = "/social/following")    // http://localhost:8889/social/following
    public String following(@AuthenticationPrincipal KreamPrincipal kreamPrincipal, ModelMap map){
//        if(kreamPrincipal == null){
//            System.out.println("==========================");
//            this.login();
//        }
        MemberDTO sessionMember = kreamPrincipal.toFullDto();
        List<Board> feed = styleLogicService.getFollowingFeeds(sessionMember.idx());
        map.addAttribute("feed", feed);
        map.addAttribute("sessionUser",sessionMember);
        return "social/following";

    }
//    public ModelAndView login(){
//        return new ModelAndView("/login");
//    }

    @GetMapping(path = "/social/myprofile")        // http://localhost:8889/social/myprofile
    public ModelAndView myprofile(){ return new ModelAndView("social/myprofile"); }

    @GetMapping(path = "/social/style_profile_edit")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView style_profile_edit(){ return new ModelAndView("social/style_profile_edit"); }

    @GetMapping(path = "/social/social_product")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView style_profilsocial_producte_edit(){ return new ModelAndView("social/social_product"); }

    @GetMapping(path = "/social/users")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView users(){ return new ModelAndView("social/users"); }

    @GetMapping(path = "/social/upload")
    public ModelAndView upload(){ return new ModelAndView("social/upload"); }

}
