package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.service.AddressApiLogicService;
import com.supreme.shoekream.service.CardApiLogicService;
import com.supreme.shoekream.service.MemberApiLogicService;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
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
public class pageController {

    private final ProductApiLogicService productApiLogicService;
    private final MemberApiLogicService memberApiLogicService;
    private final AddressApiLogicService addressApiLogicService;
    private final CardApiLogicService cardApiLogicService;

    //보류- 이유: 상세페이지에서 자동으로 사이즈가 선택이 되어있는 상태로 product_idx가 넘어온다
    @GetMapping(path="buyselect/{idx}")   //http://localhost:8889/buyselect
    public ModelAndView buyselect(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String id=null;
        String name = null;
        if(session== null){
            System.out.println("세션이 없습니다");
//            return new ModelAndView("/login");
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
        map.addAttribute("product",product);    //상품정보 넣어서
        System.out.println(map);
        //서비스로 데이터를 매개변수로 보낸다..?
        HttpSession session = request.getSession(false);
        String idx=null;
        String email = null;
        if(session== null){
            System.out.println("세션이 없습니다");
//            return  "/login";
            return "/order/buycheck";
        }else{
            idx = (String) session.getAttribute("idx");
            email = (String) session.getAttribute("email");
            System.out.println("세션이 있습니다");
            return "/order/buycheck";
        }
    }

    @GetMapping(path="buy/{productIdx}")   //http://localhost:8889/buy
    public String buy(HttpServletRequest request,
                            @PathVariable(name="productIdx",required = false) Long productIdx,
                            ModelMap map){
        Header<ProductApiResponse> product = productApiLogicService.read(productIdx);
        map.addAttribute("product",product);    //상품정보 넣어서
        System.out.println(map);
        //세션값을 가정
        Header<MemberApiResponse> member = memberApiLogicService.read(1L);
        map.addAttribute("member",member);
        System.out.println(member);
//        Header<Address> address = addressApiLogicService.
        HttpSession session = request.getSession(false);
        String idx=null;
        String email = null;
        if(session== null){
            System.out.println("세션이 없습니다");
//            return  "/login";
            return "/order/buy";
        }else{
            idx = (String) session.getAttribute("idx");
            email = (String) session.getAttribute("email");
            System.out.println("세션이 있습니다");
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
