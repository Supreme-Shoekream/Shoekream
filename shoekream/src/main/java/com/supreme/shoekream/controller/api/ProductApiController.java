package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.WishDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.request.WishApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.ProductWithWishResponse;
import com.supreme.shoekream.model.network.response.WishApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.ProductApiLogicService;
import com.supreme.shoekream.service.WishApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/product") // http://localhost:8889/api/product
public class ProductApiController {
    @Autowired ProductApiLogicService productApiLogicService;
    @Autowired WishApiLogicService wishApiLogicService;

    // 관심상품 존재하는지 확인
//    @GetMapping("")
//    public List<ProductWithWishResponse> isWish(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        if(kreamPrincipal == null){
//            return wishApiLogicService.isWishFalse();
//        }
//        MemberDTO member = kreamPrincipal.toFullDto();
//        return wishApiLogicService.isWishTrue(member);
//    }


    // 관심상품 생성
    @PostMapping("") // http://localhost:8889/api/product
    public Boolean create(@RequestBody WishDTO wishRequest, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
//        Long idx = request.idx();
//        Long proIdx = request.productIdx();
//        Long memIdx = kreamPrincipal.idx();

        boolean save = wishApiLogicService.create(wishRequest, kreamPrincipal);
        return save;
    }


    // 관심상품 삭제
    @DeleteMapping("/{idx}") // http://localhost:8889/api/product/{idx}
    public Header<ProductApiResponse> delete(@PathVariable Long idx) {
        return wishApiLogicService.delete(idx);
    }
}
