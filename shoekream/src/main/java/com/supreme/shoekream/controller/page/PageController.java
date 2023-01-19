package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PageController {

    private final ProductApiLogicService productApiLogicService;
    private final MemberApiLogicService memberApiLogicService;
    private final AddressApiLogicService addressApiLogicService;
    private final CardApiLogicService cardApiLogicService;
    private final BuyService buyService;
    private final SellService sellService;

    //보류- 이유: 상세페이지에서 자동으로 사이즈가 선택이 되어있는 상태로 product_idx가 넘어온다
    @GetMapping(path="buyselect/{idx}")   //http://localhost:8889/buyselect
    public ModelAndView buyselect(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String id=null;
        String name = null;
        if(session== null){
            System.out.println("세션이 없습니다");
            return new ModelAndView("/order/buySelect");
        }else{
            id = (String) session.getAttribute("id");
            name = (String) session.getAttribute("name");
            System.out.println("세션이 있습니다");
            return new ModelAndView("/order/buySelect")
                    .addObject("id",id)
                    .addObject("name",name);
        }
    }

    @GetMapping(path="buycheck/{productIdx}")   //http://localhost:8889/buycheck/1
    public String buycheck(HttpServletRequest request,
                                 @PathVariable(name="productIdx",required = false) Long productIdx,
                                 ModelMap map
    ){
        Header<ProductApiResponse> product = productApiLogicService.read(productIdx);
        map.addAttribute("product",product);
        System.out.println(map);
        return "/order/buycheck";
    }

    @GetMapping(path="buy/{productIdx}")   //http://localhost:8889/buy
    public String buy(HttpServletRequest request,
                            @PathVariable(name="productIdx",required = false) Long productIdx,
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

        Header<MemberApiResponse> member = memberApiLogicService.read(1L);
        map.addAttribute("point",member.getData().getPoint());

//        Header<Address> address = addressApiLogicService.
        HttpSession session = request.getSession(false);
        String memberIdx=null;
        if(session== null){
            System.out.println("세션이 없습니다");
//            return  "/login";
            return "/order/buy";
        }else{
            memberIdx = (String) session.getAttribute("sessionId");
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails)principal;
            String email = ((UserDetails) principal).getUsername();
            System.out.println("세션이 있습니다");
            System.out.println(email);
            return "/order/buy";
        }
    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path = "buybidfinish")  //http://localhost:8889/buybidfinish
    public ModelAndView buybidfinish(){return new ModelAndView("/order/buybidFinish");}

    @GetMapping(path = "buynowfinish")  //http://localhost:8889/buynowfinish
    public ModelAndView buynowfinish(){return new ModelAndView("/order/buynowFinish");}

    @GetMapping(path="sell")   //http://localhost:8889/sell
    public ModelAndView sell(){
        return new ModelAndView("/order/sell");
    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path="sellselect")   //http://localhost:8889/sellselect
    public ModelAndView sellselect(){
        return new ModelAndView("/order/sellselect");
    }

    @GetMapping(path="sellcheck")   //http://localhost:8889/sellcheck
    public ModelAndView sellcheck(){
        return new ModelAndView("/order/sellcheck");
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
