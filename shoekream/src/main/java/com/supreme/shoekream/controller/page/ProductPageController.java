package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.ProductResponse;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("product") //http://localhost:8888/product
@RequiredArgsConstructor
public class ProductPageController {
    private final ProductApiLogicService productApiLogicService;

    // 모든사이즈 상품 상세 보기
    @GetMapping(path="") //http://localhost:8888/product/modelNum
    public ModelAndView product(){
        return new ModelAndView("product/product.html");
    }

    // 사이즈별 상품 상세 보기
//    @GetMapping(path="/{idx}") //http://localhost:8888/product/modelNum/{idx}
//    public String productdetail(@PathVariable Long idx){
//        ProductResponse productResponse = ProductResponse.from(productApiLogicService.getProduct(idx));
//        return "product/detail";
//    }


}
