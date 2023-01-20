package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.BuyRequest;
import com.supreme.shoekream.model.network.request.SellRequest;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.SellRepository;
import com.supreme.shoekream.service.BuyService;
import com.supreme.shoekream.service.ProductApiLogicService;
import com.supreme.shoekream.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderApiController {
    private final BuyService buyService;
    private final SellService sellService;
    private final ProductApiLogicService productApiLogicService;
    private final SellRepository sellRepository;

    @PostMapping("/buy")
    public Header<BuyDTO> buy(@RequestBody Header<BuyRequest> request,
                              @PathVariable Long productIdx,
                              @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        System.out.println(request +"💌"+productIdx+"👟"+kreamPrincipal+"👩");
        BuyRequest buyRequest = request.getData();
        ProductDTO productDTO = buyService.findProduct(productIdx);
        SellDTO sellDTO = buyService.matching(productIdx, buyRequest.price());
        BuyDTO buyDTO = buyRequest.toDto(productDTO,kreamPrincipal.toDto(),sellDTO);
        return buyService.create(buyDTO);
    }

    @PostMapping("/sell")
    public Header<SellDTO> sell(@RequestBody Header<SellRequest> request,
                                @PathVariable Long productIdx,
                                @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        System.out.println(request +"💌"+productIdx+"👟"+kreamPrincipal+"👩");
        SellRequest sellRequest = request.getData();
        ProductDTO productDTO = buyService.findProduct(productIdx);
        BuyDTO buyDTO = sellService.matching(productIdx, sellRequest.price());
        SellDTO sellDTO = sellRequest.toDto(productDTO,kreamPrincipal.toDto(),buyDTO);
        return sellService.create(sellDTO);
    }
}
