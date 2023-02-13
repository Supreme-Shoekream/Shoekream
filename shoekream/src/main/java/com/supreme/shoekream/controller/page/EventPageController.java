package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.EventRepository;
import com.supreme.shoekream.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("") //http://localhost:8889/
@RequiredArgsConstructor
public class EventPageController {
    final EventApiService eventApiService;
    final ShopApiLogicService shopApiLogicService;
    final SellService sellService;
    private final EventRepository eventRepository;
    private final MemberApiLogicService memberApiLogicService;

    @GetMapping(path="/promotion") //http://localhost:8889/
    public String event(HttpServletRequest request, ModelMap map){
        EventDTO event = eventApiService.eventDetail(eventRepository.findFirstByOrderByIdxDesc().get().getIdx());
        map.addAttribute("event",event);
        return ("exhibitions/promotion");
    }
    @GetMapping(path="/promotion_detail") //http://localhost:8889/
    public String eventDetail(HttpServletRequest request, ModelMap map, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        EventDTO event = eventApiService.eventDetail(eventRepository.findFirstByOrderByIdxDesc().get().getIdx());
        map.addAttribute("isDraw",eventApiService.isDraw(kreamPrincipal.idx()));
        map.addAttribute("event",event);
        map.addAttribute("isPoint", memberApiLogicService.point(kreamPrincipal.idx()));
        return ("exhibitions/promotion_detail");
    }
    @GetMapping(path="/man") //http://localhost:8889/
    public String man(HttpServletRequest request, ModelMap map){
        List<ProductDTO> man = eventApiService.genderList("M");
        List<String> manbuynowPrices = sellService.buyNowPrices(man.stream().map(ProductDTO::toEntity).toList());
        List<Long> wishCount = shopApiLogicService.wishCount(man.stream().map(ProductDTO::toEntity).toList());
        List<Long> tagCount = shopApiLogicService.tagCount(man.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("man",man);
        map.addAttribute("manbuynowPrices",manbuynowPrices);
        map.addAttribute("wishCount", wishCount);
        map.addAttribute("tagCount", tagCount);
        return ("exhibitions/man");
    }
    @GetMapping(path="/woman") //http://localhost:8889/
    public String woman(HttpServletRequest request, ModelMap map){
        List<ProductDTO> woman = eventApiService.genderList("W");
        List<String> womanbuynowPrices = sellService.buyNowPrices(woman.stream().map(ProductDTO::toEntity).toList());
        List<Long> wishCount = shopApiLogicService.wishCount(woman.stream().map(ProductDTO::toEntity).toList());
        List<Long> tagCount = shopApiLogicService.tagCount(woman.stream().map(ProductDTO::toEntity).toList());
        map.addAttribute("woman",woman);
        map.addAttribute("womanbuynowPrices",womanbuynowPrices);
        map.addAttribute("wishCount", wishCount);
        map.addAttribute("tagCount", tagCount);
        return ("exhibitions/woman");
    }
}
