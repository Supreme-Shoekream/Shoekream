package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.ConclusionDTO;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.service.BuyService;
import com.supreme.shoekream.service.ProductApiLogicService;
import com.supreme.shoekream.service.ConclusionApiLogicService;
import com.supreme.shoekream.service.SellService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
@RequestMapping("product") //http://localhost:8889/product
@RequiredArgsConstructor
public class ProductPageController {
    private final ProductApiLogicService productApiLogicService;
    private final ConclusionApiLogicService conclusionApiLogicService;
    private final SellService sellService;
    private final BuyService buyService;
    private final ProductRepository productRepository;
    // 로그찍어 볼때 필요
    private final Logger logger = LoggerFactory.getLogger(ProductPageController.class.getName());


    @GetMapping(path="/{idx}") //http://localhost:8889/product/{idx}
    public String product(HttpServletRequest request, @PathVariable Long idx, ModelMap modelmap){

        Header<ProductApiResponse> product = productApiLogicService.read(idx);
        modelmap.addAttribute("product",product); // "product" 이름의 modelmap 객체를 view에서 사용하기위해 저장함
//        System.out.println("🟡" + modelmap);

        Long proIdx = product.getData().getIdx();
        modelmap.addAttribute("proIdx", proIdx);

        List<ConclusionDTO> conclusion = conclusionApiLogicService.con_read(idx);
        modelmap.addAttribute("conclusion", conclusion); // "conclusion" 이름의 modelmap 객체를 view에서 사용하기위해 저장함
//        System.out.println("🟡" + conclusion);

        String sell = sellService.buyNowPrice(idx);
        modelmap.addAttribute("sell", sell);
//        System.out.println("🟡" + sell);

        String buy = buyService.sellNowPrice(idx);
        modelmap.addAttribute("buy", buy);
//        System.out.println("🟡" + buy);

        return "product/product";

//        Header<ProductApiResponse> con_product = productApiLogicService.con_read(idx);
//        map.addAttribute("con_product",con_product);    //상품정보 넣어서
//        System.out.println(map);
//        HttpSession session = request.getSession(false);
//        String id = null;
//        String name = null;
//        if(session == null){
//            System.out.println("세션 없음");
////            return new ModelAndView("/login");
//            return "/product/product";
//        }else{
//            id = (String)session.getAttribute("id");
//            name = (String)session.getAttribute("name");
//            System.out.println("세션 있음");
//            return "/product/product";
//        }


    }


//    public List<ConclusionDTO> graph(@PathVariable Long idx, ModelMap modelmap) {
//        List<ConclusionDTO> conclusion = conclusionApiLogicService.con_read(idx);
//        modelmap.addAttribute("conclusion", conclusion);
//        return conclusion;
//    }

}
