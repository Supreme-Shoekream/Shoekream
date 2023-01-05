package com.supreme.shoekream.controller.api;


import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.network.request.AddressApiRequest;
import com.supreme.shoekream.model.network.response.AddressApiResponse;
import com.supreme.shoekream.service.AddressApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my/address")
public class AddressApiController extends CrudController<AddressApiRequest, AddressApiResponse, Address> {
    private final AddressApiLogicService addressApiLogicService;
}
