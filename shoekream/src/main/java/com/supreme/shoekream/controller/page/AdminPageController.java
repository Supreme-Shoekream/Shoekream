package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")    //http://localhost:8889/admin
@RequiredArgsConstructor
public class AdminPageController {

    @GetMapping(path="")   //http://localhost:8889/admin
    public ModelAndView index(){
        return new ModelAndView("/adminpage/index");
    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path="users")   //http://localhost:8889/admin/users
    public ModelAndView users(){
        return new ModelAndView("/adminpage/users.html");
    }

    @GetMapping(path="products")   //http://localhost:8889/admin/products
    public ModelAndView products(){
        return new ModelAndView("adminpage/products.html");
    }

    @GetMapping(path="products/create")   //http://localhost:8889/admin/products/create
    public ModelAndView productcreate(){
        return new ModelAndView("/adminpage/productcreate.html");
    }

    @GetMapping(path="login")   //http://localhost:8889/admin/login
    public ModelAndView loginadmin(){
        return new ModelAndView("/adminpage/login.html");
    }

    @GetMapping(path="register")   //http://localhost:8889/admin/register
    public ModelAndView register(){
        return new ModelAndView("/adminpage/register.html");
    }

    @GetMapping(path="brands")   //http://localhost:8889/admin/brands
    public ModelAndView brands(){
        return new ModelAndView("/adminpage/brands.html");
    }

    @GetMapping(path="buy")   //http://localhost:8889/admin/buy
    public ModelAndView buy(){
        return new ModelAndView("/adminpage/buy.html");
    }

    @GetMapping(path="sell")   //http://localhost:8889/admin/sell
    public ModelAndView sell(){
        return new ModelAndView("/adminpage/sell.html");
    }

    @GetMapping(path="conclusion")   //http://localhost:8889/admin/conclusion
    public ModelAndView conclusion(){
        return new ModelAndView("/adminpage/conclusion.html");
    }

    @GetMapping(path="notice")   //http://localhost:8889/admin/notice
    public ModelAndView notice(){
        return new ModelAndView("/adminpage/notice.html");
    }

    @GetMapping(path="event")   //http://localhost:8889/admin/event
    public ModelAndView event(){
        return new ModelAndView("/adminpage/event.html");
    }

    @GetMapping(path="brandcreate")   //http://localhost:8889/admin/brandcreate
    public ModelAndView brandcreate(){
        return new ModelAndView("/adminpage/brandcreate.html");
    }

    @GetMapping(path="statusedit")   //http://localhost:8889/admin/statusedit
    public ModelAndView statusedit(){
        return new ModelAndView("/adminpage/statusedit.html");
    }

    private final StyleLogicService styleLogicService;
    @GetMapping(path="style")   //http://localhost:8889/admin/style
    public String style(ModelMap map){
        map.addAttribute("feed", styleLogicService.list());
        System.out.println(styleLogicService.list());
        return "adminpage/style";
    }

    @GetMapping(path="admin")   //http://localhost:8889/admin/admin
    public ModelAndView admin(){
        return new ModelAndView("/adminpage/admin.html");
    }

    @GetMapping(path="vue")
    public ModelAndView vue() {return new ModelAndView("/adminpage/admin_layer/vuetest");}
}
