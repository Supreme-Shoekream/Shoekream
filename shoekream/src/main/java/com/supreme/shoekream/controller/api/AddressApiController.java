package com.supreme.shoekream.controller.api;


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
    public String create(@RequestBody Header<Address> address){
        addressApiLogicService.create(address);
        return "redirect:/my/address";
    }


    @PutMapping("")
    public Header<Address> update(@RequestBody Header<Address> request) {
        return addressApiLogicService.update(request);
    }


    @PostMapping("/delete/{idx}")
    public String delete(@PathVariable(name = "idx") Long idx) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        addressApiLogicService.delete(11L);
        return "redirect:/my/address";
    }
}
