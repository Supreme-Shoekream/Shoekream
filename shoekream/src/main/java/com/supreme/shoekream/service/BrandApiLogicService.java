package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Brand;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.BrandApiRequest;
import com.supreme.shoekream.model.network.response.BrandApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandApiLogicService extends BaseService<BrandApiRequest, BrandApiResponse, Brand> {

    @Override
    public Header<BrandApiResponse> create(Header<BrandApiRequest> request) {
        return null;
    }

    @Override
    public Header<BrandApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<BrandApiResponse> update(Header<BrandApiRequest> request) {
        return null;
    }

    @Override
    public Header<BrandApiResponse> delete(Long id) {
        return null;
    }
}
