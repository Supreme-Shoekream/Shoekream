package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.entity.Card;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.*;
import lombok.RequiredArgsConstructor;
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
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderPageController {

    private final ProductApiLogicService productApiLogicService;
    private final MemberApiLogicService memberApiLogicService;
    private final AddressApiLogicService addressApiLogicService;
    private final CardApiLogicService cardApiLogicService;
    private final BuyService buyService;
    private final SellService sellService;

    //보류- 이유: 상세페이지에서 자동으로 사이즈가 선택이 되어있는 상태로 product_idx가 넘어온다
    @GetMapping(path="buyselect/{idx}")   //http://localhost:8889/buyselect
    public ModelAndView buyselect(HttpServletRequest request){
        return new ModelAndView("/order/buySelect");
    }

    @GetMapping(path="buy/check/{productIdx}")   //http://localhost:8889/buycheck/1
    public String buycheck(@PathVariable(name="productIdx") Long productIdx, ModelMap map
    ){
        Header<ProductApiResponse> product = productApiLogicService.read(productIdx);
        map.addAttribute("product",product);
        return "/order/buycheck";
    }

    @GetMapping(path="buy/{productIdx}")   //http://localhost:8889/buy
    public String buy(@AuthenticationPrincipal KreamPrincipal kreamPrincipal,
                            @PathVariable(name="productIdx") Long productIdx,
                            ModelMap map){
        //가져와야하는 정보: 상품, 즉시구매가, 즉시판매가, 사용자기본배송지, 보유포인트, 사용자기본결재카드
        Header<ProductApiResponse> product = productApiLogicService.read(productIdx);
        map.addAttribute("product",product);    //상품정보 넣어서
        System.out.println(map);

        String buyNowPrice = sellService.buyNowPrice(productIdx);
        map.addAttribute("buyNowPrice", buyNowPrice);
        String sellNowPrice = buyService.sellNowPrice(productIdx);
        map.addAttribute("sellNowPrice",sellNowPrice);
        System.out.println(buyNowPrice +""+sellNowPrice);

        Long memberIdx = kreamPrincipal.idx();
        Header<MemberApiResponse> member = memberApiLogicService.read(memberIdx);
        map.addAttribute("point",member.getData().getPoint());

        List<Address> basicAddress = addressApiLogicService.list(memberIdx,true);
        System.out.println("👀"+basicAddress);
        if(basicAddress == null || basicAddress.isEmpty()){
            map.addAttribute("basicAddress",null);
        }else{
            map.addAttribute("basicAddress",basicAddress.get(0));
        }

//
        List<Card> basicCard = cardApiLogicService.list(memberIdx,true);
        if(basicCard == null || basicCard.isEmpty()){
            map.addAttribute("basicCard",null);
        }else{
            map.addAttribute("basicCard", basicCard.get(0));
        }


        return "/order/buy";

    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path = "buybidfinish")  //http://localhost:8889/buybidfinish
    public ModelAndView buybidfinish(){return new ModelAndView("/order/buybidFinish");}

    @GetMapping(path = "buynowfinish")  //http://localhost:8889/buynowfinish
    public ModelAndView buynowfinish(){return new ModelAndView("/order/buynowFinish");}



    @GetMapping(path="sellselect")   //http://localhost:8889/sellselect
    public ModelAndView sellselect(){
        return new ModelAndView("/order/sellselect");
    }

    @GetMapping(path="sell/check/{productIdx}")   //http://localhost:8889/sellcheck
    public String sellcheck(@PathVariable(name="productIdx") Long productIdx, ModelMap map){
        Header<ProductApiResponse> product = productApiLogicService.read(productIdx);
        map.addAttribute("product",product);
        return "/order/sellcheck";
    }

    @GetMapping(path="sell/{productIdx}")   //http://localhost:8889/sell
    public String sell(@AuthenticationPrincipal KreamPrincipal kreamPrincipal,
                             @PathVariable(name="productIdx") Long productIdx,
                             ModelMap map){

        //가져와야하는 정보: 상품, 즉시구매가, 즉시판매가, 판매정상계좌 ,사용자기본배송지(반송주소), 사용자기본결재카드(패널티 결제 방법)
        Header<ProductApiResponse> product = productApiLogicService.read(productIdx);
        map.addAttribute("product",product);    //상품정보 넣어서
        System.out.println(map);

        String buyNowPrice = sellService.buyNowPrice(productIdx);
        map.addAttribute("buyNowPrice", buyNowPrice);
        String sellNowPrice = buyService.sellNowPrice(productIdx);
        map.addAttribute("sellNowPrice",sellNowPrice);
        System.out.println(buyNowPrice +""+sellNowPrice);

        Optional<MemberDTO> member = memberApiLogicService.searchUser(kreamPrincipal.email());
        map.addAttribute("member",member.get());

        Long memberIdx = kreamPrincipal.idx();
        List<Address> basicAddress = addressApiLogicService.list(memberIdx,true);
        System.out.println("👀"+basicAddress);
        if(basicAddress == null || basicAddress.isEmpty()){
            map.addAttribute("basicAddress",null);
        }else{
            map.addAttribute("basicAddress",basicAddress.get(0));
        }

        List<Card> basicCard = cardApiLogicService.list(memberIdx,true);
        if(basicCard == null || basicCard.isEmpty()){
            map.addAttribute("basicCard",null);
        }else{
            map.addAttribute("basicCard", basicCard.get(0));
        }
        return "/order/sell";
    }

    @GetMapping(path = "sellbidfinish")  //http://localhost:8889/sellbidfinish
    public ModelAndView sellbidfinish(){return new ModelAndView("/order/sellbidfinish");}

    @GetMapping(path = "sellnowfinish")  //http://localhost:8889/sellnowfinish
    public ModelAndView sellnowfinish(){return new ModelAndView("/order/sellnowfinish");}

    @GetMapping(path = "penalty")
    public ModelAndView penalty(){ return new ModelAndView("/layer/layer_penalty"); }

    @GetMapping(path = "guide")
    public ModelAndView guide(){ return new ModelAndView("/layer/layer_guide"); }
}
