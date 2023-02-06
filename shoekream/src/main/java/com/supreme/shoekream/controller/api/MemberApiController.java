package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.BuyService;
import com.supreme.shoekream.service.MemberApiLogicService;
import com.supreme.shoekream.service.SellService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")    // http://localhost:9999/api/admin/users
@RequiredArgsConstructor
public class MemberApiController extends CrudController<MemberApiRequest, MemberApiResponse, Member> {
    private final MemberApiLogicService memberApiLogicService;
    private final BuyService buyService;
    private final SellService sellService;

//    @Override
//    @PostMapping("join")    // http://localhost:9999/api/admin/users/create (post)
//    public Header<MemberApiResponse> create(@RequestBody Header<MemberApiRequest> request) {
//        return memberApiLogicService.create(request);
//    }

    @Override
    @GetMapping("/admin/users/{idx}")     //http://localhost:9999/api/admin/users/{id} (get)
    public Header<MemberApiResponse> read(@PathVariable(name="idx") Long id) {
        return memberApiLogicService.read(id);
    }

    @GetMapping("/admin/users") //
    public Header<List<MemberApiResponse>> findAll(@PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC) Pageable pageable){
        return memberApiLogicService.search(pageable);
    }

    @Override
    @PostMapping("/join")    // http://localhost:9999/api/join (post)
    public Header<MemberApiResponse> create(@RequestBody Header<MemberApiRequest> request) {
        return memberApiLogicService.create(request);
    }

    @PostMapping("/member/delete")
    public boolean delete(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        boolean buyCheck = buyService.buyListCheck(kreamPrincipal.idx());
        boolean sellCheck = sellService.sellListCheck(kreamPrincipal.idx());
        if(buyCheck && sellCheck){
            buyService.deleteMember(kreamPrincipal.idx());
            sellService.deleteMember(kreamPrincipal.idx());
            memberApiLogicService.delete(kreamPrincipal.idx());
            System.out.println("@@");
            return true;
        }else{
            System.out.println("123123");
            return false;
        }
    }

//    @Override
//    @GetMapping("{idx}") //http://localhost:9999/member/{idx} get호출 read하기
//    public Header<MemberApiResponse> read(@PathVariable(name = "idx") Long idx) {
//        return memberApiLogicService.read(idx);
//    }

//    @Override
//    @PutMapping("")     //http://localhost:9999/member  (put)
//    public Header<MemberApiResponse> update(@RequestBody Header<MemberApiRequest> request) {
//        return memberApiLogicService.update(request);
//    }
//
//    @GetMapping("")     //http://localhost:9999/member?page=1 (get)
//    public Header<List<MemberApiResponse>> findAll(@PageableDefault(sort={"idx"},direction= Sort.Direction.DESC) Pageable pageable){
//        return memberApiLogicService.search(pageable);
//    }

//    @Override
//    @DeleteMapping("{id}")  //http://localhost:9999/member/{id} (delete)
//    public Header<MemberApiResponse> delete(Long id) {
//        return memberApiLogicService.delete(id);
//    }

//    @PostMapping("/login")  //http://localhost:9999/member/login
//    public Header<MemberApiResponse> login(@RequestBody Header<MemberApiRequest> request) {
//        return memberApiLogicService.login(request);
//    }

//    @GetMapping("/check/findPw")
//    public @ResponseBody Map<String, Boolean> pw_find(String userEmail, String userName){
//        Map<String,Boolean> json = new HashMap<>();
//        boolean pwFindCheck = memberApiLogicService.userEmailCheck(userEmail,userName);
//
//        System.out.println(pwFindCheck);
//        json.put("check", pwFindCheck);
//        return json;
//    }
//    private final SendEmailService sendEmailService;

    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
//    @PostMapping("/check/findPw/sendEmail")
//    public @ResponseBody void sendEmail(String userEmail, String userName){
//        MailDTO dto = sendEmailService.createMailAndChangePassword(userEmail, userName);
//        sendEmailService.mailSend(dto);
//
//    }
}







