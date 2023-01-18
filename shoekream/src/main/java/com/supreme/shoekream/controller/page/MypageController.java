package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.network.request.AddressApiRequest;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.AddressApiLogicService;
import com.supreme.shoekream.service.PointApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("my")
@RequiredArgsConstructor
public class MypageController {


    @GetMapping(path="mypage")    // http://localhost:8889/my/mypage
    public ModelAndView mypage(){
        return new ModelAndView("/my/mypage");
    }
    @GetMapping(path = "buying")
    public ModelAndView buying(){
        return new ModelAndView("/my/buying");
    }
    @GetMapping(path = "selling")
    public ModelAndView selling(){
        return new ModelAndView("/my/selling");
    }
    @GetMapping(path = "wish")
    public ModelAndView wish(){
        return new ModelAndView("/my/wish");
    }
    @GetMapping(path="profile")
    public ModelAndView profile(){
        return new ModelAndView("/my/profile");
    }

    private final AddressApiLogicService addressApiLogicService;
//    @GetMapping(path="address")
//    public String address(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal
//            , AddressApiRequest addressApiRequest){
//        map.addAttribute("address", addressApiLogicService.list(addressApiRequest.));
//        return "my/address";
//    }

    @GetMapping(path="payment")
    public ModelAndView payment(){
        return new ModelAndView("/my/payment");
    }
    @GetMapping(path="account")
    public ModelAndView account(){
        return new ModelAndView("/my/account");
    }
    @GetMapping(path="receipt")
    public ModelAndView receipt(){
        return new ModelAndView("/my/receipt");
    }

    private final PointApiLogicService pointApiLogicService;
    @GetMapping(path="point")
    public String point(ModelMap map, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userIdx = (Long)session.getAttribute("idx");
        map.addAttribute("point", pointApiLogicService.list(userIdx));
        return "/my/point";
    }

    @GetMapping(path="withdrawal")
    public ModelAndView withdrawal(){
        return new ModelAndView("/my/withdrawal");
    }

}
