package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.enumclass.SearchType;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.PaginationService;
import com.supreme.shoekream.service.SellService;
import com.supreme.shoekream.service.ShopApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class ShopController {

    private final ShopApiLogicService shopApiLogicService;
    private final PaginationService paginationService;
    private final SellService sellService;

    @GetMapping("search")
    public String products(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) List<String> searchValue,
            @PageableDefault(size = 40, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable, ModelMap map
    ) {

        Page<ProductDTO> products = shopApiLogicService.searchProduct(searchType, searchValue, pageable); // response 타입 객체로 저장 Page에 저장
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), products.getTotalPages());  // 현재 페이지를 가져올 수 있음. List<Integer> barNumbers 숫자가 들어있는 리스트
        List<ProductDTO> shoes = shopApiLogicService.categoryList("신발");
        List<String> shoeprice = sellService.buyNowPrices(products.stream().map(ProductDTO::toEntity).toList());
        //0131
        List<String> brands = shopApiLogicService.getBrands();


        map.addAttribute("products", products);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        map.addAttribute("shoeprice", shoeprice);
        map.addAttribute("brands", brands);

        return "shop/shop_search";
    }


    @GetMapping("searchs")
    public String search(
            @RequestParam(required = false) String psize,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String collection,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 20, sort = "wishCount", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal KreamPrincipal kreamPrincipal,
            ModelMap map
    ){
        Page<ProductDTO> products = shopApiLogicService.searchsProduct(psize,brand,category,collection,gender, keyword, pageable);
        List<String> prices = sellService.buyNowPrices(products.stream().map(ProductDTO::toEntity).toList());
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), products.getTotalPages());
        List<String> brands = shopApiLogicService.getBrands();
        List<Long> wishCount = shopApiLogicService.wishCount(products.stream().map(ProductDTO::toEntity).toList());
        List<Long> tagCount = shopApiLogicService.tagCount(products.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("products",products);
        map.addAttribute("prices",prices);
        map.addAttribute("barNumbers",barNumbers);
        map.addAttribute("brands", brands);
        map.addAttribute("wishCount", wishCount);
        map.addAttribute("tagCount", tagCount);
        if(kreamPrincipal!=null){
            List<Boolean> isWish = shopApiLogicService.isWish(products.stream().map(ProductDTO::toEntity).toList(),kreamPrincipal.idx());
            map.addAttribute("isWish",isWish);
            System.out.println("🤍🤍"+products+"❤❤"+prices+"🤍🤍"+barNumbers+"❤❤브랜드"+brands+"관심상품"+isWish);
        }else{ map.addAttribute("isWish", null);}
        return "shop/shop_searchs";
    }

    @GetMapping(path="brand/{brandName}") //http://localhost:8888/brand
    public String brand(@PathVariable(name="brandName") String brandName,
                        @PageableDefault(size = 20, sort = "wishCount", direction = Sort.Direction.DESC)Pageable pageable,
                        ModelMap map){
        Page<ProductDTO> products = shopApiLogicService.brand(brandName,pageable);
        List<String> prices = sellService.buyNowPrices(products.stream().map(ProductDTO::toEntity).toList());
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), products.getTotalPages());
        List<Long> wishCount = shopApiLogicService.wishCount(products.stream().map(ProductDTO::toEntity).toList());
        List<Long> tagCount = shopApiLogicService.tagCount(products.stream().map(ProductDTO::toEntity).toList());
        int count = products.stream().toList().size();
        map.addAttribute("products",products);
        map.addAttribute("prices",prices);
        map.addAttribute("barNumbers",barNumbers);
        map.addAttribute("count",count);
        map.addAttribute("wishCount", wishCount);
        map.addAttribute("tagCount", tagCount);
        map.addAttribute("brandName",products.stream().findAny().get().brand());
        return "/product/brand";
    }

}