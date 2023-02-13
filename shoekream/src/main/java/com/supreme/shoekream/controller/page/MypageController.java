package com.supreme.shoekream.controller.page;


import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

import com.supreme.shoekream.model.entity.Product;

import com.supreme.shoekream.model.entity.Wish;
import com.supreme.shoekream.model.enumclass.OrderStatus;


import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.BuyResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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

//    @GetMapping(path="")
//    public ModelAndView mypage(){
//        return new ModelAndView("/my/mypage");
//    }

    @GetMapping(path="")
    public String mypage(ModelMap modelmap, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        List<BuyResponse> buyDisplay = buyService. myBuyList(memberDTO.idx()).stream().map(BuyResponse::from).toList();
        List<SellResponse> sellDisplay = sellService. mysellList(memberDTO.idx()).stream().map(SellResponse::from).toList();
        List<BuyResponse> biddings = buyService. myPageBuyListByStatus(memberDTO.idx(), OrderStatus.BIDDING).stream().map(BuyResponse::from).toList();
        List<BuyResponse> progressings = buyService. myPageBuyListByStatus(memberDTO.idx(), OrderStatus.PROGRESSING).stream().map(BuyResponse::from).toList();
        List<BuyResponse> ends = buyService. myPageBuyListByStatus(memberDTO.idx(), OrderStatus.END).stream().map(BuyResponse::from).toList();
        List<SellResponse> biddingsSell = sellService. myPageSellListByStatus(memberDTO.idx(), OrderStatus.BIDDING).stream().map(SellResponse::from).toList();
        List<SellResponse> progressingsSell = sellService. myPageSellListByStatus(memberDTO.idx(), OrderStatus.PROGRESSING).stream().map(SellResponse::from).toList();
        List<SellResponse> endsSell = sellService. myPageSellListByStatus(memberDTO.idx(), OrderStatus.END).stream().map(SellResponse::from).toList();
        modelmap.addAttribute("buyDisplay", buyDisplay);
        modelmap.addAttribute("sellDisplay", sellDisplay);
        modelmap.addAttribute("member", memberApiLogicService.list(memberDTO.idx()));
        modelmap.addAttribute("bidCount",biddings.stream().toList().size());
        modelmap.addAttribute("proCount",progressings.stream().toList().size());
        modelmap.addAttribute("endCount",ends.stream().toList().size());
        modelmap.addAttribute("bidCountSell",biddingsSell.stream().toList().size());
        modelmap.addAttribute("proCountSell",progressingsSell.stream().toList().size());
        modelmap.addAttribute("endCountSell",endsSell.stream().toList().size());
        return "/my/mypage";
    }

//    @Autowired PaginationService paginationService;
    @GetMapping(path = "buying")
    public String buying(ModelMap map, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            , @AuthenticationPrincipal KreamPrincipal kreamPrincipal, @RequestParam(required = false) Long month){

        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        Page<BuyResponse> biddings = buyService.myBuyListByStatus(memberDTO.idx(), OrderStatus.BIDDING, month,pageable).map(BuyResponse::from);
        Page<BuyResponse> progressings = buyService.myBuyListByStatus(memberDTO.idx(), OrderStatus.PROGRESSING, month,pageable).map(BuyResponse::from);
        Page<BuyResponse> ends = buyService.myBuyListByStatus(memberDTO.idx(), OrderStatus.END, month,pageable).map(BuyResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), biddings.getTotalPages());
        map.addAttribute("biddings", biddings);
        map.addAttribute("progressings", progressings);
        map.addAttribute("ends", ends);
        map.addAttribute("barNumbers", barNumbers);
        map.addAttribute("bidCount",biddings.getTotalElements());
        map.addAttribute("proCount",progressings.getTotalElements());
        map.addAttribute("endCount",ends.getTotalElements());
        if(month != null){
            map.addAttribute("today", LocalDateTime.now());
            map.addAttribute("before", LocalDateTime.now().minusMonths(month));
        }else{
            map.addAttribute("today", null);
            map.addAttribute("before", null);
        }
        return ("/my/buying");
    }

//        Page<Wish> wish_productList_page= wishApiLogicService.productListPage(kreamPrincipal.idx(), pageable);
//        modelmap.addAttribute("wish_productPage", wish_productList_page);
//        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),wish_productList_page.getTotalPages());
//        modelmap.addAttribute("barNumbers", barNumbers);
//        List<String> wish_price = sellService.buyNowPricesForWish(wish_productList_page);
//        modelmap.addAttribute("wish_productPrice", wish_price);


    @GetMapping(path = "buying/{buyIdx}")
    public String buyingDetail(ModelMap map, @PathVariable(name="buyIdx") Long buyIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        BuyResponse buy = BuyResponse.from(buyService.buyDetail(buyIdx));
        map.addAttribute("buy",buy);
        return("/my/buying_detail");
    }

    @GetMapping(path = "selling")
    public String selling(ModelMap map, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            , @AuthenticationPrincipal KreamPrincipal kreamPrincipal, @RequestParam(required = false) Long month){
        MemberDTO memberDTO = kreamPrincipal.toFullDto();
        Page<SellResponse> biddings =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.BIDDING, month, pageable).map(SellResponse::from);
        Page<SellResponse> progressings =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.PROGRESSING, month, pageable).map(SellResponse::from);
        Page<SellResponse> ends =sellService.mySellListByStatus(memberDTO.idx(), OrderStatus.END, month, pageable).map(SellResponse::from);
        map.addAttribute("biddings",biddings);
        map.addAttribute("progressings",progressings);
        map.addAttribute("ends",ends);
        map.addAttribute("bidCount",biddings.getTotalElements());
        map.addAttribute("proCount",progressings.getTotalElements());
        map.addAttribute("endCount",ends.getTotalElements());
        if(month != null){
            map.addAttribute("today", LocalDateTime.now());
            map.addAttribute("before", LocalDateTime.now().minusMonths(month));
        }else{
            map.addAttribute("today", null);
            map.addAttribute("before", null);
        }
        return ("/my/selling");
    }

    @GetMapping(path = "selling/{sellIdx}")
    public String sellingDetail(ModelMap map, @PathVariable(name="sellIdx") Long sellIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        SellResponse sell = SellResponse.from(sellService.sellDetail(sellIdx));
        map.addAttribute("sell", sell);
        return("/my/selling_detail");
    }


    @Autowired PaginationService paginationService;
    @GetMapping(path = "wish")
    public String wish(ModelMap modelmap, @AuthenticationPrincipal KreamPrincipal kreamPrincipal,
                       @PageableDefault(size = 10, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable){
        if(kreamPrincipal == null){
            return "login/login";
        }
        Page<Wish> wish_productList_page= wishApiLogicService.productListPage(kreamPrincipal.idx(), pageable);
        modelmap.addAttribute("wish_productPage", wish_productList_page);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),wish_productList_page.getTotalPages());
        modelmap.addAttribute("barNumbers", barNumbers);
        List<String> wish_price = sellService.buyNowPricesForWish(wish_productList_page);
        modelmap.addAttribute("wish_productPrice", wish_price);

        return "my/wish";
    }

    @GetMapping(path="profile")
    public String profile(ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            return "login/login";
        }
        System.out.println(memberApiLogicService.readProfile(kreamPrincipal.idx()));
        map.addAttribute("profile", memberApiLogicService.readProfile(kreamPrincipal.idx()));
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
