package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // url 호출시 json 응답/요청 가능
@RequestMapping("/api/admin/product") // http://localhost:8888/api/admin/product?
@RequiredArgsConstructor
public class ProductApiController extends CrudController<ProductApiRequest, ProductApiResponse, Product> {
    private final ProductApiLogicService productApiLogicService;

    @Override
    @PostMapping("") // http://localhost:8888/api/admin/product -> post방식
    public Header<ProductApiResponse> create(@RequestBody Header<ProductApiRequest> request) {
        return productApiLogicService.create(request);
    }
}
