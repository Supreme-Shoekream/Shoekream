package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.service.AdminProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products") // http://localhost:8889/api/admin/products
@RequiredArgsConstructor
public class AdminProductsApiController extends CrudController<ProductApiRequest, ProductApiResponse, Product> {
    private final AdminProductApiLogicService adminProductApiLogicService;

    // 생성
    @Override
    @PostMapping("") // http://localhost:8889/api/admin/products
    public Header<ProductApiResponse> create(@RequestBody Header<ProductApiRequest> request) {
        return adminProductApiLogicService.create(request);
    }


    // 리스트 출력
    @GetMapping("") // http://localhost:8889/api/admin/products
    public Header<List<ProductApiResponse>> findAll(@PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC)Pageable pageable){
        return adminProductApiLogicService.search(pageable);
    }

    // 상세 보기
    @GetMapping("/{idx}") // http://localhost:8889/api/admin/products/{idx}
    public Header<ProductApiResponse> read(@PathVariable(name="idx") Long idx) {
        return adminProductApiLogicService.read(idx);
    }

    // 수정
    @PutMapping("/{idx}") // http://localhost:8889/api/admin/products/{idx}
    public Header<ProductApiResponse> update(@RequestBody Header<ProductApiRequest> request) {
        return adminProductApiLogicService.update(request);
    }

    // 삭제
    @DeleteMapping("/{idx}") // http://localhost:8889/api/admin/products/{idx}
    public Header<ProductApiResponse> delete(@PathVariable(name="idx") Long idx) {
        return adminProductApiLogicService.delete(idx);
    }
}
