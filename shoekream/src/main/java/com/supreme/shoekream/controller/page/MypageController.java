package com.supreme.shoekream.controller.page;


import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

import com.supreme.shoekream.model.entity.Product;

import com.supreme.shoekream.model.enumclass.OrderStatus;


import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.BuyResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.SellResponse;
import com.supreme.shoekream.model.network.response.WishApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("my") // http://localhost:8889/my
@RequiredArgsConstructor
public class MypageController {

    private final AddressApiLogicService addressApiLogicService;
    private final CardApiLogicService cardApiLogicService;
    private final AccountApiLogicService accountApiLogicService;
    private final MemberApiLogicService memberApiLogicService;
    @Autowired BuyService buyService;
    @Autowired SellService sellService;
    @Autowired WishApiLogicService wishApiLogicService;
    @Autowired PointApiLogicService pointApiLogicService;

    @GetMapping(path="")
    public String mypage(){
        return "/my/mypage";
    }

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
        map.addAttribute("bidCount",biddings.stream().toList().size());
        map.addAttribute("proCount",progressings.stream().toList().size());
        map.addAttribute("endCount",ends.stream().toList().size());
        return ("/my/buying");
    }

    @GetMapping(path = "buying/{buyIdx}")
    public String buyingDetail(ModelMap map, @PathVariable(name="buyIdx") Long buyIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        BuyResponse buy = BuyResponse.from(buyService.buyDetail(buyIdx));
        map.addAttribute("buy",buy);
        return("/my/buying_detail");
    }

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
        map.addAttribute("bidCount",biddings.stream().toList().size());
        map.addAttribute("proCount",progressings.stream().toList().size());
        map.addAttribute("endCount",ends.stream().toList().size());
        System.out.println("입찰중"+biddings+"진행중"+progressings+"종료"+ends);
        return ("/my/selling");
    }

    @GetMapping(path = "selling/{sellIdx}")
    public String sellingDetail(ModelMap map, @PathVariable(name="sellIdx") Long sellIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        SellResponse sell = SellResponse.from(sellService.sellDetail(sellIdx));
        map.addAttribute("sell", sell);
        return("/my/selling_detail");
    }

    @GetMapping(path = "wish")
    public String wish(ModelMap modelmap, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        List<Product> wish_productList = wishApiLogicService.productList(kreamPrincipal.idx());
        modelmap.addAttribute("wish_productList", wish_productList);
        List<String> wish_productPrice = sellService.buyNowPrices(wish_productList);
        modelmap.addAttribute("wish_productPrice", wish_productPrice);
        return "my/wish";
    }

    @GetMapping(path="profile")
    public String profile(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        System.out.println(memberApiLogicService.read2(kreamPrincipal.idx()));
        map.addAttribute("profile", memberApiLogicService.read2(kreamPrincipal.idx()));
        return "/my/profile";
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
        if(kreamPrincipal == null){
            return "login/login";
        }
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        map.addAttribute("address", addressApiLogicService.list(memberDTO.idx(), false));
        map.addAttribute("basic", addressApiLogicService.list(memberDTO.idx(), true));
        return "my/address";
    }

    @GetMapping(path="payment")
    public String payment(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        map.addAttribute("basic", cardApiLogicService.list(memberDTO.idx(), true));
        map.addAttribute("other", cardApiLogicService.list(memberDTO.idx(), false));
        return "/my/payment";
    }

    @GetMapping(path="account")
    public String account(ModelMap modelmap, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        modelmap.addAttribute("account", accountApiLogicService.list(memberDTO.idx()));
        return "/my/account";
    }

    @GetMapping(path="receipt")
    public String receipt(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        return "/my/receipt";
    }

    @GetMapping(path="point")
    public String point(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        map.addAttribute("member", memberApiLogicService.readPoint(memberDTO.idx()));
        map.addAttribute("point", pointApiLogicService.list(memberDTO.idx()));
        return "my/point";
    }

    @GetMapping(path="withdrawal")
    public ModelAndView withdrawal(){
        return new ModelAndView("/my/withdrawal");
    }

}
