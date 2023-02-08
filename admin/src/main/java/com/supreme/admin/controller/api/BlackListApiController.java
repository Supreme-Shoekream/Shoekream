package com.supreme.admin.controller.api;
import com.supreme.admin.model.dto.BlacklistDTO;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.BlacklistRequest;
import com.supreme.admin.model.network.response.BlacklistResponse;
import com.supreme.admin.service.BlacklistApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blacklist")
public class BlackListApiController {
    private final BlacklistApiLogicService blacklistApiLogicService;
    @PostMapping("/{idx}")
    public Header<BlacklistResponse> create(@RequestBody Header<BlacklistRequest> request, @PathVariable(name="idx") Long idx){
        BlacklistRequest blacklistRequest = request.getData();
        BlacklistDTO blacklistDTO = blacklistRequest.toDTO();
        blacklistApiLogicService.update(idx);
        return blacklistApiLogicService.create(blacklistDTO, idx);
    }

}
