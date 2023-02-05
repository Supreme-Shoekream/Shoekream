package com.supreme.admin.controller.page;


import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.Conclusion;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.model.network.Pagination;
import com.supreme.admin.model.network.response.BuyResponse;
import com.supreme.admin.model.network.response.SellResponse;
import com.supreme.admin.repository.ConclusionRepository;
import com.supreme.admin.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("")    //http://localhost:8889/admin
@RequiredArgsConstructor
public class AdminPageController {
    private final PaginationService paginationService;
    private final AdminApiLogicService adminApiLogicService;
    private final DashboardService dashboardService;

    @PostMapping(path="/loginOk")   //http://localhost:8889/loginOk
    public String loginOk(HttpServletRequest request, String userid, String userpw){
        if(adminApiLogicService.read(userid, userpw).getData() != null){
            HttpSession session = request.getSession();
            String name = adminApiLogicService.read(userid, userpw).getData().getName();
            session.setAttribute("id", userid);
            session.setAttribute("name", name);
            return "redirect:/";
        }else{
            return "redirect:/login";
        }
    }
    private String sessionCheck(HttpServletRequest request  ){
        HttpSession session = request.getSession(false);
        String id=null;
        String name = null;
        if(session== null){
            System.out.println("세션이 없습니다");
            return null;
        }else{
            id = (String) session.getAttribute("id");
            name = (String) session.getAttribute("name");
            System.out.println("세션이 있습니다 id=" +id+ ", name="+name );
            return id+"("+name+")";
        }
    }

    @GetMapping(path="")   //http://localhost:8889/
    public String index(HttpServletRequest request, ModelMap map){
        String session = sessionCheck(request);
        if(session == null) return "redirect:/login";
//        List<Product> products = dashboardService.popularProducts();
        List<Product> products = dashboardService.bestSeller();
        List<Long> totalDealPrice = dashboardService.totalDealPrice(products);
        List<Long> totalDealCount = dashboardService.totalDealCount(products);
        System.out.println("🍒🍒"+products +"🍕🍕"+ totalDealPrice+"🍔🍔"+totalDealCount);
        map.addAttribute("sessionInfo",session);
        map.addAttribute("userCount",dashboardService.userCount());
        map.addAttribute("productCount",dashboardService.productCount());
        map.addAttribute("feedCount",dashboardService.feedCount());
        map.addAttribute("conclusionCount",dashboardService.conclusionCount());
        map.addAttribute("products",products);
        map.addAttribute("totalDealPrice",totalDealPrice);
        map.addAttribute("totalDealCount",totalDealCount);
        return "/adminpage/index";
    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path="users")   //http://localhost:8889//users
    public ModelAndView users(HttpServletRequest request){
        String session = sessionCheck(request);
        if(session == null)  return new ModelAndView( "/adminpage/login.html");
        return new ModelAndView("/adminpage/users.html").addObject("sessionInfo",session);
    }

    @GetMapping(path="products")   //http://localhost:8889//products
    public ModelAndView products(HttpServletRequest request){
        String session = sessionCheck(request);
        if(session == null)  return new ModelAndView( "/adminpage/login.html");
        return new ModelAndView("/adminpage/products.html").addObject("sessionInfo",session);
    }


    private final BuyService buyService;
    @GetMapping(path="buy")   //http://localhost:8889/buy
    public String buy(@RequestParam(required = false) String searchKeyword,
                      @PageableDefault(size = 10, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                      ModelMap map,HttpServletRequest request){
        Page<BuyResponse> buys = buyService.searchBuy(searchKeyword, pageable).map(BuyResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),buys.getTotalPages());
        String session = sessionCheck(request);
        if(session == null) return "redirect:/login";
        map.addAttribute("sessionInfo",session);
        map.addAttribute("buys",buys);
        map.addAttribute("barNumbers",barNumbers);
        return("/adminpage/buy");
    }

    private final SellService sellService;
    @GetMapping(path="sell")   //http://localhost:8889/sell
    public String sell(
            @RequestParam(required = false) String searchKeyword,
            @PageableDefault(size = 10, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map,HttpServletRequest request){
        Page<SellResponse> sells = sellService.searchSell(searchKeyword, pageable).map(SellResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),sells.getTotalPages());
        String session = sessionCheck(request);
        if(session == null) return "redirect:/login";
        map.addAttribute("sessionInfo",session);
        map.addAttribute("sells", sells);
        map.addAttribute("barNumbers",barNumbers);
        return("adminpage/sell");
    }


    private final ConclusionRepository conclusionRepository;
    @GetMapping(path="conclusion")   //http://localhost:8889/conclusion
    public ModelAndView conclusion(ModelMap modelMap,HttpServletRequest request){
        List<Conclusion> list = conclusionRepository.findAll();
        modelMap.addAttribute("list", list);

        String session = sessionCheck(request);
        if(session == null)  return new ModelAndView( "/adminpage/login.html");
        modelMap.addAttribute("sessionInfo",session);
//        System.out.println(list);
        return new ModelAndView("/adminpage/conclusion.html");
    }

    @GetMapping(path="notice")   //http://localhost:8889/notice
    public ModelAndView notice(HttpServletRequest request){
        return new ModelAndView("/adminpage/notice.html");
    }

    @GetMapping(path="event")   //http://localhost:8889/event
    public ModelAndView event(HttpServletRequest request){
        return new ModelAndView("/adminpage/event.html");
    }


    private final StyleLogicService styleLogicService;
    @GetMapping(path="style")   //http://localhost:8889/style
    public String style(ModelMap map,HttpServletRequest request){
        map.addAttribute("feed", styleLogicService.list());
        System.out.println(styleLogicService.list());
        return "adminpage/style";
    }
    @GetMapping(path="admin")   //http://localhost:8889/admin
    public ModelAndView admin(HttpServletRequest request){
        return new ModelAndView("/adminpage/admin.html");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("adminpage/login");
    }

}