package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")    //http://localhost:8889
@RequiredArgsConstructor
public class MainPageController {
    final MainService mainService;
    @GetMapping(path="")   //http://localhost:8889/
    public String index(ModelMap map){
        List<ProductDTO> justDrop = mainService.collectionList("JUST_DROP");
        List<ProductDTO> mostPopular = mainService.collectionList("MOST_POPULAR");
        List<ProductDTO> newIn = mainService.collectionList("NEW_IN");
        List<ProductDTO> shoppingWisely = mainService.collectionList("SHOPPIING_WISELY");
        List<ProductDTO> brandMdPicks = mainService.collectionList("BRAND_MD_PICK");
        List<ProductDTO> simpleChic = mainService.collectionList("SIMPLE_CHIC");
        List<ProductDTO> bestApprelCollabs = mainService.collectionList("BEST_APPAREL_COLLABS");
        List<ProductDTO> newLowestAsks = mainService.collectionList("NEW_LOWEST_ASKS");
        List<ProductDTO> newHighestBids = mainService.collectionList("NEW_HIGHEST_BIDS");
        List<ProductDTO> upcomingRelease = mainService.collectionList("UPCOMING_RELEASE");
        List<ProductDTO> bestCollectibles = mainService.collectionList("BEST_COLLECTIBLES");
        List<ProductDTO> bestJewelryWatches = mainService.collectionList("BEST_JEWELRY_WATCHES");
        List<ProductDTO> bestCampingGear = mainService.collectionList("BEST_CAMPING_GEAR");
        map.addAttribute("justDrop",justDrop);
        map.addAttribute("mostPopular",mostPopular);
        map.addAttribute("newIn",newIn);
        map.addAttribute("shoppingWisely",shoppingWisely);
        map.addAttribute("brandMdPicks",brandMdPicks);
        map.addAttribute("simpleChic",simpleChic);
        map.addAttribute("bestApprelCollabs",bestApprelCollabs);
        map.addAttribute("newLowestAsks",newLowestAsks);
        map.addAttribute("newHighestBids",newHighestBids);
        map.addAttribute("upcomingRelease",upcomingRelease);
        map.addAttribute("bestCollectibles",bestCollectibles);
        map.addAttribute("bestJewelryWatches",bestJewelryWatches);
        map.addAttribute("bestCampingGear",bestCampingGear);

        return "index";
    }   //viewName: 페이지이름이랑 같아야함

}
