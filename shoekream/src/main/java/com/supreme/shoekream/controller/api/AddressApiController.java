package com.supreme.shoekream.controller.api;


import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.service.AddressApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/my/address")
public class AddressApiController {
    private final AddressApiLogicService addressApiLogicService;

    @PostMapping("")
    public Header<Address> create(@RequestBody Header<AddressDTO> address){
        return addressApiLogicService.create(address);
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
