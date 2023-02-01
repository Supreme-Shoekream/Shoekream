package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.service.MemberApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my/profile") // localhost:8889/api/my/profile
public class ProfileApiController {
    private final MemberApiLogicService memberApiLogicService;

    @PutMapping("")
    public Header<MemberApiResponse> update(@RequestBody Header<MemberApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        System.out.println(request.getData());
        MemberDTO memberDTO = request.getData().toDTO();
        return memberApiLogicService.updateProfile(memberDTO, kreamPrincipal.idx());
    }
}
