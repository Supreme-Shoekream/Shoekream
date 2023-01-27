package com.supreme.shoekream.controller.page;


import com.supreme.shoekream.model.dto.MemberDTO;

import com.supreme.shoekream.model.entity.Product;

import com.supreme.shoekream.model.enumclass.OrderStatus;


import com.supreme.shoekream.model.network.response.SellResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final AddressApiLogicService addressApiLogicService;


    @GetMapping(path="")    // http://localhost:8889/my
    public ModelAndView mypage(){
        return new ModelAndView("/my/mypage");
    }

    private final BuyService buyService;
    @GetMapping(path = "buying")
    public String buying(ModelMap map, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            , @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        Page<BuyResponse> biddings = buyService.myBuyListByStatus(memberDTO.idx(), OrderStatus.BIDDING, pageable).map(BuyResponse::from);
        Page<BuyResponse> progressings = buyService.myBuyListByStatus(memberDTO.idx(), OrderStatus.PROGRESSING, pageable).map(BuyResponse::from);
        Page<BuyResponse> ends = buyService.myBuyListByStatus(memberDTO.idx(), OrderStatus.END, pageable).map(BuyResponse::from);
        map.addAttribute("biddings", biddings);
        map.addAttribute("progressings", progressings);
        map.addAttribute("ends", ends);
        return ("/my/buying");
    }

//    @GetMapping(path = "buying")
//    public ModelAndView buying(){
//        return new ModelAndView("/my/buying");
//    }
    private final SellService sellService;
    @GetMapping(path = "selling")
    public String selling(ModelMap map, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            , @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        Page<SellResponse> biddings =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.BIDDING, pageable).map(SellResponse::from);
        Page<SellResponse> progressings =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.PROGRESSING, pageable).map(SellResponse::from);
        Page<SellResponse> ends =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.END, pageable).map(SellResponse::from);
        map.addAttribute("biddings",biddings);
        map.addAttribute("progressings",progressings);
        map.addAttribute("ends",ends);
        System.out.println("입찰중"+biddings+"진행중"+progressings+"종료"+ends);
        return ("/my/selling");
    }

     @GetMapping(path = "selling/{sellIdx}")
    public String sellingDetail(ModelMap map, @PathVariable(name="sellIdx") Long sellIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return("/my/selling_detail");
    }

    private final WishApiLogicService wishApiLogicService;
    @GetMapping(path = "wish/{idx}")
    public String wish(@PathVariable Long idx, ModelMap modelmap, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
        // 🔴 idx 말고 로그인한 세션값을 넣어줘야함 -> 크림프린시펄 사용?
        List<Product> wish_productList = wishApiLogicService.productList(idx);
        modelmap.addAttribute("wish_productList", wish_productList);
        List<String> wish_productPrice = sellService.buyNowPrices(wish_productList);
        modelmap.addAttribute("wish_productPrice", wish_productPrice);
        return "my/wish";
    }

    @GetMapping(path="profile")
    public ModelAndView profile(){
        return new ModelAndView("/my/profile");
    }
    @GetMapping(path="buying_detail")
    public ModelAndView buying_detail(){
        return new ModelAndView("/my/buying_detail");
    }
    @GetMapping(path="buying_end")
    public ModelAndView buying_end(){
        return new ModelAndView("/my/buying_end");
    }
    @GetMapping(path="selling_detail")
    public ModelAndView selling_detail(){
        return new ModelAndView("/my/selling_detail");
    }



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
