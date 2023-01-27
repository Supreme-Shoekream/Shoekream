package com.supreme.shoekream.controller.page;


import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.response.BuyResponse;
import com.supreme.shoekream.model.network.response.SellResponse;
import com.supreme.shoekream.repository.ConclusionRepository;
import com.supreme.shoekream.service.BuyService;
import com.supreme.shoekream.service.PaginationService;
import com.supreme.shoekream.service.SellService;
import com.supreme.shoekream.service.StyleLogicService;
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

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("admin")    //http://localhost:8889/admin
@RequiredArgsConstructor
public class AdminPageController {
    private final PaginationService paginationService;

    @GetMapping(path="")   //http://localhost:8889/admin
    public ModelAndView index(){
        return new ModelAndView("/adminpage/index");
    }   //viewName: 페이지이름이랑 같아야함

    @GetMapping(path="users")   //http://localhost:8889/admin/users
    public ModelAndView users(){
        return new ModelAndView("/adminpage/users.html");
    }

    @GetMapping(path="users/create")   //http://localhost:8889/admin/users/create
    public ModelAndView usercreate(){
        return new ModelAndView("/adminpage/admin_layer/layer_user_create.html");
    }

    @GetMapping(path="products")   //http://localhost:8889/admin/products
    public ModelAndView products(){
        return new ModelAndView("adminpage/products.html");
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /* 첨부 파일 업로드 */
    @PostMapping("products") // 파일 1개 업로드 //http://localhost:8889/admin/products
    public void uploadAjaxActionPOST(MultipartFile uploadFile) {
        logger.info("⚠️uploadAjaxActionPOST..........");
        logger.info("⚠️파일 이름 : " + uploadFile.getOriginalFilename());
        logger.info("⚠️파일 타입 : " + uploadFile.getContentType());
        logger.info("⚠️파일 크기 : " + uploadFile.getSize());
        // 저장 폴더 경로
        String uploadFolder = "/Users/oyun-yeong/Desktop/Shoekream/publising/Shoekream/shoekream/src/main/resources/static/img/product/";
//        // 폴더 생성
        File uploadPath = new File(uploadFolder);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
////        logger.info("-----------------------------------------------");
        String uploadFileName = uploadFile.getOriginalFilename(); // 파일 이름
////        uploadFileName = uploadFileName.replace(" ", "_"); // 파일 이름에 띄어쓰기가 있으면 언더바로 변경하기
////        System.out.println("🔵" + uploadFileName);
       File saveFile = new File(uploadPath, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
        try { // 파일 저장
            uploadFile.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return uploadFileName;
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


    private final BuyService buyService;
    @GetMapping(path="buy")   //http://localhost:8889/admin/buy
    public String buy(@RequestParam(required = false) String searchKeyword,
                      @PageableDefault(size = 10, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                      ModelMap map){
        Page<BuyResponse> buys = buyService.searchBuy(searchKeyword, pageable).map(BuyResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),buys.getTotalPages());
        map.addAttribute("buys",buys);
        map.addAttribute("barNumbers",barNumbers);
        return("/adminpage/buy");
    }

    private final SellService sellService;
    @GetMapping(path="sell")   //http://localhost:8889/admin/sell
    public String sell(
            @RequestParam(required = false) String searchKeyword,
            @PageableDefault(size = 10, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable, ModelMap map
    ){
        Page<SellResponse> sells = sellService.searchSell(searchKeyword, pageable).map(SellResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),sells.getTotalPages());
        map.addAttribute("sells", sells);
        map.addAttribute("barNumbers",barNumbers);
        return("adminpage/sell");
    }

//    public ModelAndView sell(){return new ModelAndView("/adminpage/sell.html");}

    private final ConclusionRepository conclusionRepository;
    @GetMapping(path="conclusion")   //http://localhost:8889/admin/conclusion
    public ModelAndView conclusion(ModelMap modelMap){
        List<Conclusion> list = conclusionRepository.findAll();
        modelMap.addAttribute("list", list);
//        System.out.println(list);
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
