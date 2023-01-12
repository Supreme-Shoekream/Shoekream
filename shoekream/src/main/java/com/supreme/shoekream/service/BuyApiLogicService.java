package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.BuyApiRequest;
import com.supreme.shoekream.model.network.response.BuyApiResponse;
import com.supreme.shoekream.repository.BuyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class BuyApiLogicService extends BaseService<BuyApiRequest, BuyApiResponse, Buy>{

    private final BuyRepository buyRepository;


    private BuyApiResponse response(Buy buy){

return null;
    }
    @Override
    public Header<BuyApiResponse> create(Header<BuyApiRequest> request) {
        return null;
    }

    @Override
    public Header<BuyApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<BuyApiResponse> update(Header<BuyApiRequest> request) {
        return null;
    }

    @Override
    public Header<BuyApiResponse> delete(Long id) {
        return null;
    }
}
