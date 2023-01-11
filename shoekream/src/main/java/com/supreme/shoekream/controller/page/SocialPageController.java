package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Follow;
import com.supreme.shoekream.model.entity.Member;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/social")
@RequiredArgsConstructor
public class SocialPageController {
    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    private final FollowRepository followRepository;

//    public SocialPageController(BoardRepository boardRepository,
//                                MemberRepository memberRepository, FollowRepository followRepository) {
//        this.boardRepository = boardRepository;
//        this.memberRepository = memberRepository;
//        this.followRepository = followRepository;
//    }

    @GetMapping(path="")    // http://localhost:8889/social
    public ModelAndView trending(){
        return new ModelAndView("social/trending");
    }

    @GetMapping(path = "/newest")   // http://localhost:8889/social/newest
    public ModelAndView newest(){ return new ModelAndView("social/newest"); }

    @GetMapping(path = "/following")    // http://localhost:8889/social/following
    public String following(HttpServletRequest request, ModelMap map){
//        ✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔ 세션 아이디
//        HttpSession session = request.getSession(false);
//        String userid = null;
//
//        if(session == null){
//            System.out.println("세션이 없습니다.");
//            return "/login";
//        }else{//        ✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔ 세션 아이디
//            userid = (String)session.getAttribute("id");
//            Long sessionUserIdx = memberRepository.findByMemberId(userid).get().getIdx();
//            List<Follow> followings = followRepository.findAllByfollowerIdx(sessionUserIdx);
            List<Follow> followings = followRepository.findAllByfollowerIdx(5L);
            List<Board> feed;
            feed = new ArrayList<>();
            for(int i=0; i<followings.size(); i++){
                List<Board> sub = boardRepository.findAllByMemberIdx(followings.get(i).getFollowingIdx());
                for(int j=0; j<sub.size();j++){
                    feed.add(sub.get(j));
                }
            }
        Member sessionUser = memberRepository.getOne(5L);
            map.addAttribute("feed", feed);
            map.addAttribute("sessionUser",sessionUser);
            return "social/following";
//        }

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
