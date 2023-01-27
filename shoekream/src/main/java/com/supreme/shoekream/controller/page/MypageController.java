package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Wish;

import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.enumclass.OrderStatus;

import com.supreme.shoekream.model.network.request.AddressApiRequest;
import com.supreme.shoekream.model.network.response.AddressApiResponse;
import com.supreme.shoekream.model.network.response.SellResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.AddressApiLogicService;
import com.supreme.shoekream.service.PointApiLogicService;
import com.supreme.shoekream.service.SellService;

import com.supreme.shoekream.service.WishApiLogicService;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("my")
@RequiredArgsConstructor
public class MypageController {

    @GetMapping(path="")    // http://localhost:8889/my
    public ModelAndView mypage(){
        return new ModelAndView("/my/mypage");
    }
    @GetMapping(path = "buying")
    public ModelAndView buying(){
        return new ModelAndView("/my/buying");
    }
    private final SellService sellService;
    @GetMapping(path = "selling")
    public String selling(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        List<SellResponse> biddings =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.BIDDING).stream().map(SellResponse::from).toList();
        List<SellResponse> progressings =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.PROGRESSING).stream().map(SellResponse::from).toList();
        List<SellResponse> ends =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.END).stream().map(SellResponse::from).toList();
        map.addAttribute("bidding",biddings);
        map.addAttribute("progressing",progressings);
        map.addAttribute("end",ends);
        System.out.println("입찰중"+biddings+"진행중"+progressings+"종료"+ends);
        return ("/my/selling");
    }


    private final WishApiLogicService wishApiLogicService;
    @GetMapping(path = "wish/{idx}")
    public String wish(@PathVariable Long idx, ModelMap modelmap, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        // 🔴 idx 말고 로그인한 세션값을 넣어줘야함 -> 크림프린시펄 사용?
        List<Product> wish_productList = wishApiLogicService.productList(idx);
        modelmap.addAttribute("wish_productList", wish_productList);
        List<String> wish_productPrice = sellService.buyNowPrices(wish_productList);
        modelmap.addAttribute("wish_productPrice", wish_productPrice);
        return "my/wish";
    }

//    @GetMapping(path = "wish")
//    public ModelAndView wish(){
//        return new ModelAndView("my/wish");
//    }

    @GetMapping(path="profile")
    public ModelAndView profile(){
        return new ModelAndView("/my/profile");
    }

    private final AddressApiLogicService addressApiLogicService;
    @GetMapping(path="address")
    public String address(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        map.addAttribute("address", addressApiLogicService.list(memberDTO.idx(), false));
        map.addAttribute("basic", addressApiLogicService.list(memberDTO.idx(), true));
        return "my/address";
    }



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
