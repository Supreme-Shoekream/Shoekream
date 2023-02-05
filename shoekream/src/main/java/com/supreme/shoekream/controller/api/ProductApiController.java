package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.ProductApiLogicService;
import com.supreme.shoekream.service.WishApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product") // http://localhost:8889/api/product
public class ProductApiController {
    private final ProductApiLogicService productApiLogicService;
    private final WishApiLogicService wishApiLogicService;


    // 관심상품 생성
    @PostMapping("") // http://localhost:8889/api/product
    public boolean create(@RequestBody WishDTO wishRequest, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
        boolean saved = wishApiLogicService.create(wishRequest, kreamPrincipal);
        return saved;
    }


    // 관심상품 삭제
    @DeleteMapping("/{idx}") // http://localhost:8889/api/product/{idx}
    public boolean delete(@PathVariable Long idx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
        boolean deleted =  wishApiLogicService.delete(idx, kreamPrincipal.idx());
        return deleted;
    }
}
