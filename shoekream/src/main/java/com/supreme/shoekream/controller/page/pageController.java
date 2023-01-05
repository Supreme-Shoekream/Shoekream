package com.supreme.shoekream.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class pageController {

    @GetMapping(path="buy")   //http://localhost:8889/buy
    public ModelAndView buy(){
        return new ModelAndView("/order/buy");
    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path="buyselect")   //http://localhost:8889/buyselect
    public ModelAndView buyselect(){
        return new ModelAndView("/order/buySelect");
    }

    @GetMapping(path="buycheck")   //http://localhost:8889/buycheck
    public ModelAndView buycheck(){
        return new ModelAndView("/order/buyCheck");
    }

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
