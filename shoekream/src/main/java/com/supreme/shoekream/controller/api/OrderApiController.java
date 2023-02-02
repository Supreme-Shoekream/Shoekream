package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.enumclass.Progress;
import com.supreme.shoekream.model.enumclass.SellProgress;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.BuyRequest;
import com.supreme.shoekream.model.network.request.SellRequest;
import com.supreme.shoekream.model.network.response.BuyResponse;
import com.supreme.shoekream.model.network.response.SellResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.BuyRepository;
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
    private final BuyRepository buyRepository;

    @PostMapping("/buy")
    public Header<BuyDTO> buy(@RequestBody Header<BuyRequest> request,
                              @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        System.out.println(request +"💌"+kreamPrincipal+"👩");
        BuyRequest buyRequest = request.getData();
        ProductDTO productDTO = buyService.findProduct(buyRequest.productIdx());
        SellDTO sellDTO = buyService.matching(buyRequest.productIdx(), buyRequest.price());
        BuyDTO buyDTO = buyRequest.toDto(productDTO,kreamPrincipal.toFullDto(),sellDTO);
        System.out.println("❤❤"+buyDTO);
        return buyService.create(buyDTO);
    }

    @PostMapping("/sell")
    public Header<SellDTO> sell(@RequestBody Header<SellRequest> request,
                                @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        System.out.println(request +"💌"+kreamPrincipal+"👩");
        SellRequest sellRequest = request.getData();
        ProductDTO productDTO = buyService.findProduct(sellRequest.productIdx());
        BuyDTO buyDTO = sellService.matching(sellRequest.productIdx(), sellRequest.price());
        SellDTO sellDTO = sellRequest.toDto(productDTO,kreamPrincipal.toFullDto(),buyDTO);
        System.out.println("❤❤"+sellDTO);
        return sellService.create(sellDTO);
    }

    @GetMapping("buy/{idx}")
    public BuyResponse buyRead(@PathVariable(name="idx")Long idx){
        BuyDTO buy = buyService.buyDetail(idx);
        return BuyResponse.from(buy);
    }

    @DeleteMapping("buy/{idx}")
    public Header<BuyDTO> buyDelete(@PathVariable(name="idx")Long idx){
        return buyService.delete(idx);
    }

    @PutMapping("buy/{idx}")
    public Header<BuyDTO> buyUpdate(@PathVariable(name="idx")Long idx
            , @RequestBody Header<BuyRequest> request){
        int progressNum = request.getData().progressNum();
        Progress progress = null;
        switch (progressNum){
            case 0 -> progress=Progress.SHIPMENT_COMPLETE;
            case 1 -> progress=Progress.RECEIVING_COMPLETE;
            case 2 -> progress=Progress.EXAMINATION_PASS;
            case 3 -> progress=Progress.DELIVERY_COMPLETE;
        }
        return buyService.update(idx, progress);
    }

    @GetMapping("sell/{idx}")
    public SellResponse sellRead(@PathVariable(name="idx")Long idx){
        SellDTO sell = sellService.sellDetail(idx);
        return SellResponse.from(sell);
    }

    @DeleteMapping("sell/{idx}")
    public Header<SellDTO> sellDelete(@PathVariable(name="idx")Long idx){
        return sellService.delete(idx);
    }


    @PutMapping("sell/{idx}")
    public Header<SellDTO> sellUpdate(@PathVariable(name="idx")Long idx
            , @RequestBody Header<SellRequest> request){
        int progressNum = request.getData().progressNum();
        SellProgress sellProgress = null;
        switch (progressNum){
            case 0 -> sellProgress=SellProgress.SHIPMENT_REQUEST;
            case 1 -> sellProgress=SellProgress.RECEIVING_COMPLETE;
            case 2 -> sellProgress=SellProgress.EXAMINATION_PASS;
            case 3 -> sellProgress=SellProgress.CALCULATE_COMPLETE;
        }
        return sellService.update(idx, sellProgress);
    }

}
