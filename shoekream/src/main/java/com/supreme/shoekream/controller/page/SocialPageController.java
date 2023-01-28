package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.dto.socialDTO.FollowDTO;
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
    public ModelAndView trending(ModelMap map){
        map.addAttribute("trendHashtags", styleLogicService.trendHashtags());
        return new ModelAndView("social/trending");
    }

//    @GetMapping(path="/social")    // http://localhost:8889/social
//    public String trending(ModelMap map){
//        map.addAttribute("trendHashtags", styleLogicService.trendHashtags());
//        map.addAttribute("boards", styleLogicService.trendList());
//        return "social/trending";
//    }

    @GetMapping(path = "/social/newest")   // http://localhost:8889/social/newest
    public ModelAndView newest(ModelMap map){
        map.addAttribute("trendHashtags", styleLogicService.trendHashtags());
        return new ModelAndView("social/newest");
    }


    @GetMapping(path = "/social/following")    // http://localhost:8889/social/following
    public String following(@AuthenticationPrincipal KreamPrincipal kreamPrincipal, ModelMap map){
        MemberDTO sessionMember = kreamPrincipal.toFullDto();
//        List<BoardDTO> feed = BoardDTO.fromEntity(styleLogicService.getFollowingFeeds(sessionMember.idx()));
        map.addAttribute("feed", styleLogicService.getFollowingFeeds(sessionMember.idx()));
        map.addAttribute("sessionUser",sessionMember);
        return "social/following";

    }

    @GetMapping(path = "/social/myprofile")        // http://localhost:8889/social/myprofile
    public ModelAndView myprofile(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        List<FollowDTO> followings = styleLogicService.countFollowing(kreamPrincipal);
        List<FollowDTO> followers = styleLogicService.countFollowers(kreamPrincipal);

        System.out.println(followings + "테스트 !!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(followers);
        map.addAttribute("followings", followings);
        map.addAttribute("followers", followers);
        return new ModelAndView("social/myprofile");
    }

    @GetMapping(path = "/social/style_profile_edit")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView style_profile_edit(){ return new ModelAndView("social/style_profile_edit"); }

    @GetMapping(path = "/social/social_product")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView style_profilsocial_producte_edit(){ return new ModelAndView("social/social_product"); }

    @GetMapping(path = "/social/users")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView users(){ return new ModelAndView("social/users"); }

    @GetMapping(path = "/social/upload")
    public ModelAndView upload(){ return new ModelAndView("social/upload"); }

}
