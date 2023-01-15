package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.ProductResponse;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.supreme.shoekream.model.entity.QProduct.product;

@Controller
@RequestMapping("product") //http://localhost:8888/product
@RequiredArgsConstructor
public class ProductPageController {
    private final ProductApiLogicService productApiLogicService;
    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductPageController.class.getName());
    // 모든사이즈 상품 상세 보기
    @GetMapping(path="/{idx}") //http://localhost:8888/product/{idx}
    public String product(HttpServletRequest request, @PathVariable Long idx, Model model){


//        Product product = productRepository.getOne(idx);
//        map.addAttribute("product", product);
//        System.out.println(map);


        Header<ProductApiResponse> product = productApiLogicService.read(idx);
        model.addAttribute("product",product);
//        System.out.println("❌❌❌❌❌❌❌❌"+map);
        logger.info("msg : {}", model);
        logger.info("msg : {}", );



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

        return "product/product";

    }

    // 사이즈별 상품 상세 보기
//    @GetMapping(path="/{idx}") //http://localhost:8888/product/modelNum/{idx}
//    public String productdetail(@PathVariable Long idx){
//        ProductResponse productResponse = ProductResponse.from(productApiLogicService.getProduct(idx));
//        return "product/detail";
//    }


}
