package com.supreme.shoekream.controller.api;


import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.AddressApiRequest;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.AddressApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/my/address")
public class AddressApiController {
    private final AddressApiLogicService addressApiLogicService;

    @PostMapping("")
    public Header<AddressDTO> create(@RequestBody Header<AddressApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        AddressApiRequest addressApiRequest = request.getData();
        AddressDTO addressDTO = addressApiRequest.toDTO(kreamPrincipal.toFullDto());
        return addressApiLogicService.create(addressDTO);
    }


    @PutMapping("")
    public Header<Address> update(@RequestBody Header<Address> request) {
        return addressApiLogicService.update(request);
    }


    @PostMapping("/delete/{idx}")
    public String delete(@PathVariable(name = "idx") Long idx) {
        addressApiLogicService.delete(idx);
        return "";
    }
}
