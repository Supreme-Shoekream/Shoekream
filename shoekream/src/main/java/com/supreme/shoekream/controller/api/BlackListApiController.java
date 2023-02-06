package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.BlacklistDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.Blacklist;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.BlacklistRequest;
import com.supreme.shoekream.model.network.request.PointApiRequest;
import com.supreme.shoekream.model.network.response.BlacklistResponse;
import com.supreme.shoekream.model.network.response.PointApiResponse;
import com.supreme.shoekream.repository.BlacklistRepository;
import com.supreme.shoekream.service.BlacklistApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
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
