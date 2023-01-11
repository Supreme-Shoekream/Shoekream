package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.service.ConclusionLogicService;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/conclusion") // http://localhost:8888/admin/conclusion
@RequiredArgsConstructor
public class ConclusionApiController {
    private final ConclusionLogicService conclusionLogicService;
    private final ProductApiLogicService productApiLogicService;
    private final ProductRepository productRepository;

//    @GetMapping("{idx}")    // http://localhost:8889/api/social (post)
//    public String conclusionList(@PathVariable(name="idx") Long idx, ModelMap map){
//        return conclusionLogicService.(idx);
//    }

    @GetMapping("")
    public ModelAndView conclusionList() throws Exception {
        ModelAndView mv = new ModelAndView("/adminpage/conclusion");

        List<Product> list = productApiLogicService.selectProductList();
        mv.addObject("list", list);

        System.out.println(list);
        System.out.println(mv);

        return mv;
    }
}
