package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.repository.BoardRepository;
import com.supreme.shoekream.repository.FollowRepository;
import com.supreme.shoekream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/social")
//@RequiredArgsConstructor
public class SocialPageController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private FollowRepository followRepository;

    public SocialPageController(BoardRepository boardRepository,
                                MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping(path="")    // http://localhost:8889/social
    public ModelAndView trending(){
        return new ModelAndView("social/trending");
    }

    @GetMapping(path = "/newest")   // http://localhost:8889/social/newest
    public ModelAndView newest(){ return new ModelAndView("social/newest"); }

    @GetMapping(path = "/following")    // http://localhost:8889/social/following
    public String following( ModelMap map){
//        ✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔ 세션 아이디로 수정 필요
        List<Long> followings = followRepository.findAllByfollowerIdx(1L);
//        List<Board> feed =  boardRepository.findAllByMemberIdx(1L);
//        map.addAttribute("feed", feed);
        return "social/following";
    }

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
