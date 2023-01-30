package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.MemberApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/my/account")
public class AccountApiController {
    private final MemberApiLogicService memberApiLogicService;
    @PutMapping("")     //http://localhost:8989/api/my/account (put)
    public Header<MemberApiResponse> update(@RequestBody Header<MemberApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
        MemberApiRequest memberApiRequest = request.getData();
        MemberDTO memberDTO = memberApiRequest.toDTO();
        return memberApiLogicService.updateAccount(memberDTO, kreamPrincipal.idx());
    }
}
