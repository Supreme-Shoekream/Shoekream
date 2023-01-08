package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.service.ProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // url 호출시 json 응답/요청 가능
@RequestMapping("/api/admin/products") // http://localhost:8888/api/admin/products
@RequiredArgsConstructor
public class ProductApiController extends CrudController<ProductApiRequest, ProductApiResponse, Product> {
    private final ProductApiLogicService productApiLogicService;

    // 생성
    @Override
    @PostMapping("create") // http://localhost:8888/api/admin/products/create
    public Header<ProductApiResponse> create(@RequestBody Header<ProductApiRequest> request) {
        return productApiLogicService.create(request);
    }


    // 리스트 출력
    @GetMapping("") // http://localhost:8888/api/admin/products
    public Header<List<ProductApiResponse>> findAll(@PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC)Pageable pageable){
        return productApiLogicService.search(pageable);
    }

    // 보기
    @GetMapping("/{idx}") // http://localhost:8888/api/admin/products/{idx}
    public Header<ProductApiResponse> read(@PathVariable(name="idx") Long idx) {
        return productApiLogicService.read(idx);
    }

    // 수정
    @PutMapping("/{idx}") // http://localhost:8888/api/admin/products/{idx}
    public Header<ProductApiResponse> update(Header<ProductApiRequest> request) {
        return productApiLogicService.update(request);
    }

    // 삭제
    @DeleteMapping("{idx}") // http://localhost:8888/api/admin/products/{idx}
    public Header<ProductApiResponse> delete(@PathVariable(name="idx") Long idx) {
        return productApiLogicService.delete(idx);
    }
}
