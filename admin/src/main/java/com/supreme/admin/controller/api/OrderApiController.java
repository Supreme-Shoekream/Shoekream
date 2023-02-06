package com.supreme.admin.controller.api;

import com.supreme.admin.model.dto.BuyDTO;
import com.supreme.admin.model.dto.SellDTO;
import com.supreme.admin.model.enumclass.Progress;
import com.supreme.admin.model.enumclass.SellProgress;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.BuyRequest;
import com.supreme.admin.model.network.request.SellRequest;
import com.supreme.admin.model.network.response.BuyResponse;
import com.supreme.admin.model.network.response.SellResponse;
import com.supreme.admin.repository.BuyRepository;
import com.supreme.admin.repository.SellRepository;
import com.supreme.admin.service.BuyService;
import com.supreme.admin.service.ProductApiLogicService;
import com.supreme.admin.service.SellService;
import lombok.RequiredArgsConstructor;
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

}
