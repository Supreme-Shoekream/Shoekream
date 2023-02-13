package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.response.BoardWithLikeListResponse;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.SellRepository;
import com.supreme.shoekream.service.MainService;
import com.supreme.shoekream.service.SellService;
import com.supreme.shoekream.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")    //http://localhost:8889
@RequiredArgsConstructor
public class MainPageController {
    final MainService mainService;
    final StyleLogicService styleLogicService;
    final MemberRepository memberRepository;
    final SellService sellService;
    @GetMapping(path="")   //http://localhost:8889/
    public String index(HttpServletRequest request, ModelMap map){
        List<ProductDTO> justDrop = mainService.collectionList("JUST_DROP");
//        List<ProductDTO> mostPopular = mainService.collectionList("MOST_POPULAR");
        List<Product> mostPopular = mainService.bestSeller();
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

        List<BoardWithLikeListResponse> stylemost = styleLogicService.findTopSevenStylePick();
        map.addAttribute("stylemost",stylemost);

        List<String> justDroptbuynowPrices = sellService.buyNowPrices(justDrop.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("justDroptbuynowPrices",justDroptbuynowPrices);
        List<String> mostPopularbuynowPrices = sellService.buyNowPrices(mostPopular);
        map.addAttribute("mostPopularbuynowPrices",mostPopularbuynowPrices);
        List<String> newInbuynowPrices = sellService.buyNowPrices(newIn.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("newInbuynowPrices",newInbuynowPrices);
        List<String> shoppingWiselybuynowPrices = sellService.buyNowPrices(shoppingWisely.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("shoppingWiselybuynowPrices",shoppingWiselybuynowPrices);
        List<String> brandMdPicksbuynowPrices = sellService.buyNowPrices(brandMdPicks.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("brandMdPicksbuynowPrices",brandMdPicksbuynowPrices);
        List<String> simpleChicbuynowPrices = sellService.buyNowPrices(simpleChic.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("simpleChicbuynowPrices",simpleChicbuynowPrices);
        List<String> bestApprelCollabsbuynowPrices = sellService.buyNowPrices(bestApprelCollabs.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("bestApprelCollabsbuynowPrices",bestApprelCollabsbuynowPrices);
        List<String> newLowestAsksbuynowPrices = sellService.buyNowPrices(newLowestAsks.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("newLowestAsksbuynowPrices",newLowestAsksbuynowPrices);
        List<String> newHighestBidsbuynowPrices = sellService.buyNowPrices(newHighestBids.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("newHighestBidsbuynowPrices",newHighestBidsbuynowPrices);
        List<String> upcomingReleasebuynowPrices = sellService.buyNowPrices(upcomingRelease.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("upcomingReleasebuynowPrices",upcomingReleasebuynowPrices);
        List<String> bestCollectiblesbuynowPrices = sellService.buyNowPrices(bestCollectibles.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("bestCollectiblesbuynowPrices",bestCollectiblesbuynowPrices);
        List<String> bestJewelryWatchesbuynowPrices = sellService.buyNowPrices(bestJewelryWatches.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("bestJewelryWatchesbuynowPrices",bestJewelryWatchesbuynowPrices);
        List<String> bestCampingGearbuynowPrices = sellService.buyNowPrices(bestCampingGear.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("bestCampingGearbuynowPrices",bestCampingGearbuynowPrices);

        return "index";
    }   //viewName: 페이지이름이랑 같아야함

}
