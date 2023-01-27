package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.BuyListResponse;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.BuyService;
import com.supreme.shoekream.service.MemberApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/buying")
@RequiredArgsConstructor
public class MypageApiController extends CrudController<MemberApiRequest, MemberApiResponse, Member> {
//    private final MemberApiLogicService memberApiLogicService;
//    private final BuyService buyService;

//    @GetMapping ("")
//    public List<BuyListResponse> read(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        MemberDTO memberDTO = kreamPrincipal.toFullDto();
//        List<BuyListResponse> dto = buyService.myBuyList(memberDTO.idx());
//        System.out.println(dto);
//        return dto;
//    }
}
