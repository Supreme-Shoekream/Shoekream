package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.dto.socialDTO.FollowDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Follow;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.response.BoardWithLikeListResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.BoardRepository;
import com.supreme.shoekream.repository.FollowRepository;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.service.SellService;
import com.supreme.shoekream.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final ProductRepository productRepository;
    private final SellService sellService;


    @GetMapping(path="/social")    // http://localhost:8889/social
    public ModelAndView trending(ModelMap map, @PageableDefault(size = 12)Pageable pageable){
        map.addAttribute("trendHashtags", styleLogicService.trendHashtags());
        Page<BoardWithLikeListResponse> boards = styleLogicService.unlog_trend(pageable);
        int pageNum = boards.getNumber();
        map.addAttribute("pageNumber", pageNum);
        map.addAttribute("totalPages", boards.getTotalPages());
        return new ModelAndView("social/trending");
    }


    @GetMapping(path = "/social/newest")   // http://localhost:8889/social/newest
    public ModelAndView newest(ModelMap map, @PageableDefault(size = 12)Pageable pageable){
        map.addAttribute("trendHashtags", styleLogicService.trendHashtags());
        Page<BoardWithLikeListResponse> boards = styleLogicService.unlog_newest(pageable);
        int pageNum = boards.getNumber();
        map.addAttribute("pageNumber", pageNum);
        map.addAttribute("totalPages", boards.getTotalPages());
        return new ModelAndView("social/newest");
    }


    @GetMapping(path = "/social/following")    // http://localhost:8889/social/following
    public String following(@AuthenticationPrincipal KreamPrincipal kreamPrincipal, ModelMap map){
        MemberDTO sessionMember = kreamPrincipal.toFullDto();
        map.addAttribute("feed", styleLogicService.getFollowingFeeds(sessionMember.idx()));
        map.addAttribute("sessionUser",sessionMember);
        map.addAttribute("prices",sellService.buyNowTagPrices(styleLogicService.getFollowingFeeds(sessionMember.idx())));
        return "social/following";

    }

    @GetMapping(path = "/social/myprofile")        // http://localhost:8889/social/myprofile
    public ModelAndView myprofile(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        List<FollowDTO> followings = styleLogicService.countFollowing(kreamPrincipal.idx());
        List<FollowDTO> followers = styleLogicService.countFollowers(kreamPrincipal.idx());

        map.addAttribute("followings", followings);
        map.addAttribute("followers", followers);
        return new ModelAndView("social/myprofile");
    }

    @GetMapping(path = "/social/style_profile_edit")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView style_profile_edit(){ return new ModelAndView("social/style_profile_edit"); }

//    @GetMapping(path = "/social/social_product")   // http://localhost:8889/social/style_profile_edit
//    public ModelAndView style_profilsocial_producte_edit(){ return new ModelAndView("social/social_product"); }

    @GetMapping(path = "/social/users")   // http://localhost:8889/social/style_profile_edit
    public ModelAndView users(){ return new ModelAndView("social/users"); }

    @GetMapping(path = "/social/upload")
    public ModelAndView upload(){ return new ModelAndView("social/upload"); }

    @GetMapping(path = "/social/profile/{memberIdx}")
    public String profile(@PathVariable(name = "memberIdx") Long memberIdx, ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        List<FollowDTO> followings = styleLogicService.countFollowing(memberIdx);
        List<FollowDTO> followers = styleLogicService.countFollowers(memberIdx);

        map.addAttribute("member", styleLogicService.getMember(memberIdx));
        map.addAttribute("followings", followings);
        map.addAttribute("followers", followers);
        boolean flag = false;
        for(int i=0;i<followers.size();i++){
            System.out.println("flag" + kreamPrincipal.idx()+followers.get(i).follower().idx());
            if(kreamPrincipal != null && followers.get(i).follower().idx().equals(kreamPrincipal.idx())){
                flag = true;
            }
        }
        System.out.println(flag);
        map.addAttribute("flag", flag);
        return "social/profile";
    }

    @GetMapping(path = "/social/hashtag/{hash}")
    public String hashtag(@PathVariable(name = "hash") String hashtag, @AuthenticationPrincipal KreamPrincipal kreamPrincipal, ModelMap map){
        map.addAttribute("hashtag", hashtag);
        return "social/hashtag";
    }

    @GetMapping(path = "/social/details")
    public String details(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            map.addAttribute("feed", styleLogicService.unlog_newest());
            map.addAttribute("sessionUser", memberRepository.getReferenceById(100L));
        }else{
            map.addAttribute("feed", styleLogicService.newest(kreamPrincipal.toFullDto()));
            map.addAttribute("sessionUser",kreamPrincipal.toFullDto());
        }
        return "social/details";
    }

    @GetMapping(path="/social/social_product/{productIdx}")//productIdx 추가
    public String productPage(@PathVariable(name = "productIdx") Long productIdx, ModelMap map){
        map.addAttribute("product", ProductDTO.fromEntity(productRepository.findByIdx(productIdx)));
        return "social/social_product";
    }
}
