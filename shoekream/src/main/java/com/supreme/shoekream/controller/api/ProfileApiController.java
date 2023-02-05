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
    public boolean update(@RequestBody Header<MemberApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(request.getData().memberPw() == null){
            MemberDTO memberDTO = request.getData().toDTO();
            memberApiLogicService.updateProfile(memberDTO, kreamPrincipal.idx());
        }else {
            String[] password1 = request.getData().memberPw().split(","); // 기존 번호 [0] / 새로운 번호 [1]
            String password2 = memberApiLogicService.read(kreamPrincipal.idx()).getData().getMemberPw(); // 확인할 번호
            System.out.println(password1[0] + "@@@@@" + password2.substring(6));
            if (password1[0].equals(password2.substring(6))){
                MemberDTO dto = MemberDTO.of(null, password1[1], null, null, null, null,null);
                memberApiLogicService.updateProfile(dto, kreamPrincipal.idx());
                return true;
            }else {
                return false;
            }
        }
    return true;
    }
}
