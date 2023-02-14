package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Notice;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.NoticeApiRequest;
import com.supreme.shoekream.model.network.response.NoticeApiResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // url 호출시 json 응답/요청 가능
@RequestMapping("/api/shop") // http://localhost:8888/api/notice
@RequiredArgsConstructor
public class ShopApiController extends CrudController<NoticeApiRequest, NoticeApiResponse, Notice> {
    private final ProductApiLogicService productApiLogicService;


    @GetMapping(path="/searchWord")    // http://localhost:8889/searchWord?keyword=
    public Header<List<ProductApiResponse>> searchWord(
            @RequestParam(required = true) String keyword,
            @RequestParam(required = false ,defaultValue = "1" ) int page,
            @PageableDefault(size=20 , sort="idx" , direction= Sort.Direction.DESC) Pageable pageable
    ){

        return productApiLogicService.searchKeyword(keyword  , pageable);
    }

}
