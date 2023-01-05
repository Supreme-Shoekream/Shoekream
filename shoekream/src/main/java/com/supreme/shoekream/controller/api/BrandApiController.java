package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Brand;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.AdminApiRequest;
import com.supreme.shoekream.model.network.request.BrandApiRequest;
import com.supreme.shoekream.model.network.response.BrandApiResponse;
import com.supreme.shoekream.service.BrandApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brand")   //http://localhost:8889/brand
@RequiredArgsConstructor
public class BrandApiController extends CrudController < BrandApiRequest, BrandApiResponse, Brand> {

    private final BrandApiLogicService brandApiLogicService;

    @GetMapping("") //http://localhost:8889 get호출 read하기
    public Header<BrandApiResponse> read(@PathVariable(name = "id") Long id) { return brandApiLogicService.read(id); }
}


