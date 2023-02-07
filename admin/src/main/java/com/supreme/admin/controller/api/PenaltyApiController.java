package com.supreme.admin.controller.api;

import com.supreme.admin.model.dto.PenaltyDTO;
import com.supreme.admin.model.dto.SellDTO;
import com.supreme.admin.model.enumclass.Reason;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.PenaltyRequest;
import com.supreme.admin.model.network.response.PenaltyResponse;
import com.supreme.admin.service.PenaltyApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/penalty")
@RequiredArgsConstructor
public class PenaltyApiController {
    private final PenaltyApiLogicService penaltyApiLogicService;

    @PostMapping("")
    public Header<PenaltyDTO> create(@RequestBody Header<PenaltyRequest> request){
        Integer reasonIdx = request.getData().reasonIdx();
        Reason reason = null;
        switch (reasonIdx){
            case 0 -> reason=Reason.DELAYED_SHIPMENT;
            case 1 -> reason=Reason.REFUSED;
            case 2 -> reason=Reason.OUT_OF_STOCK;
            case 3 -> reason=Reason.INSPECTION_FAILED;
        }

        return penaltyApiLogicService.create(request.getData().sellIdx(), reason);
    }

    @GetMapping("{idx}")
    public PenaltyResponse read(@PathVariable(name="idx") Long idx){
        return PenaltyResponse.from(penaltyApiLogicService.detail(idx));
    }

    @DeleteMapping("{idx}")
    public Header delete(@PathVariable(name="idx") Long idx){
        return penaltyApiLogicService.delete(idx);
    }
}
