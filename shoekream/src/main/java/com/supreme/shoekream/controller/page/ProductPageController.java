package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.*;
import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.BoardWithLikeListResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.repository.WishRepository;
import com.supreme.shoekream.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("product") //http://localhost:8889/product
@RequiredArgsConstructor
public class ProductPageController {
    @Autowired ProductApiLogicService productApiLogicService;
    @Autowired ConclusionApiLogicService conclusionApiLogicService;
    @Autowired SellService sellService;
    @Autowired BuyService buyService;
    @Autowired WishApiLogicService wishApiLogicService;
    @Autowired ProductRepository productRepository;
    @Autowired WishRepository wishRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductPageController.class.getName());


    @GetMapping(path="/{idx}") //http://localhost:8889/product/{idx}
    public String product(@PathVariable Long idx, ModelMap modelmap, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){

        Header<ProductApiResponse> product = productApiLogicService.read(idx);
        modelmap.addAttribute("product",product); // "product" 이름의 modelmap 객체를 view에서 사용하기위해 저장함

        Long proIdx = product.getData().getIdx();
        modelmap.addAttribute("proIdx", proIdx);

        if(kreamPrincipal == null){
            logger.info("세션이 없습니다");
        }else{
            Long memIdx = kreamPrincipal.idx();
            modelmap.addAttribute("memIdx", memIdx);

            boolean isWish = wishApiLogicService.read(memIdx, proIdx);
            modelmap.addAttribute("isWish", isWish);
        }

        List<ConclusionDTO> conclusions = conclusionApiLogicService.conclusionList(idx);
        modelmap.addAttribute("conclusion", conclusions); // "conclusion" 이름의 modelmap 객체를 view에서 사용하기위해 저장함

        List<SellDTO> sellList = sellService.sellList(idx);
        if(sellList == null){
            modelmap.addAttribute("sellList", "-");
        }
        modelmap.addAttribute("sellList", sellList);

        List<BuyDTO> buyList = buyService.buyList(idx);
        if(buyList == null){
            modelmap.addAttribute("buyList", "-");
        }
        modelmap.addAttribute("buyList", buyList);

        String sell = sellService.buyNowPrice(idx);
        modelmap.addAttribute("sell", sell);

        String buy = buyService.sellNowPrice(idx);
        modelmap.addAttribute("buy", buy);

        String brandName = product.getData().getBrand();
        List<Product> brandOtherProduct = productApiLogicService.brandOtherProduct(brandName);
        List<String> brandOtherProductSell = sellService.buyNowPrices(brandOtherProduct);
        modelmap.addAttribute("brandOtherProduct", brandOtherProduct);
        modelmap.addAttribute("brandOtherProductSell", brandOtherProductSell);

        Conclusion recentPrice = conclusionApiLogicService.recentPrice(idx);
        if (recentPrice == null) {
            modelmap.addAttribute("recentPrice", "-");
        } else {
            modelmap.addAttribute("recentPrice", recentPrice.getPrice());
        }

        return "product/product";
    }
}
