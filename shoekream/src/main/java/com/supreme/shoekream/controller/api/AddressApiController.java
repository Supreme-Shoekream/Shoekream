package com.supreme.shoekream.controller.api;


import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.AddressApiRequest;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.AddressApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{idx}")
    public Header<AddressDTO> read(@PathVariable(name = "idx") Long idx){
        return addressApiLogicService.read(idx);
    }

    @PutMapping("/{idx}")
    public Header<AddressDTO> update(@RequestBody Header<AddressApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal, @PathVariable(name = "idx")Long idx) {
        AddressApiRequest addressApiRequest = request.getData();
        AddressDTO addressDTO = addressApiRequest.toDTO(kreamPrincipal.toFullDto());
        return addressApiLogicService.update(addressDTO, idx);
    }


    @PostMapping("/delete/{idx}")
    public String delete(@PathVariable(name = "idx") Long idx) {
        addressApiLogicService.delete(idx);
        return "";
    }

    @GetMapping("/basic")
    public List<Address> basic(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return addressApiLogicService.list(kreamPrincipal.idx(), true);
    }
    @GetMapping("/other")
    public List<Address> other(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return addressApiLogicService.list(kreamPrincipal.idx(), false);
    }
    @GetMapping("/all")
    public List<Address> list(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return addressApiLogicService.all(kreamPrincipal.idx());
    }
}