package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.request.WishApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.WishApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/product") // http://localhost:8889/api/product
public class ProductApiController {



    @Autowired ProductApiLogicService productApiLogicService;

    // 생성
//    @PostMapping("") // http://localhost:8889/api/product
//    public Boolean create(HttpServletRequest request) {
//        request.getParameter("data");
//        request.get
////        log.info("~~~~~~~~~~~~~~~~~~~ :{} ",s);
//        return productApiLogicService.create();
//    }
//    @PostMapping("") // http://localhost:8889/api/product
//    public Header<WishApiResponse> create(@RequestBody Header<WishApiRequest> request) {
//        return productApiLogicService.create(request);
//    }
    @PostMapping("") // http://localhost:8889/api/product
    public Boolean create(@RequestBody WishDTO request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
        Long idx = request.idx();
        Long proIdx = request.productIdx();
        Long memIdx = kreamPrincipal.idx();
        boolean save = productApiLogicService.create(request, kreamPrincipal);
        return save;
    }

    // 삭제
    @DeleteMapping("/{idx}") // http://localhost:8889/api/product/{idx}
    public Header<ProductApiResponse> delete(@PathVariable(name="idx") Long idx) {
        return productApiLogicService.delete(idx);
    }
}
