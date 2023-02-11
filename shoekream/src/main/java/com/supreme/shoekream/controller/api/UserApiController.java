package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.MailDTO;
import com.supreme.shoekream.service.MemberApiLogicService;
import com.supreme.shoekream.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("") //http://localhost:8889/
@RequiredArgsConstructor
public class UserApiController {
    private final MemberApiLogicService memberApiLogicService;
    private final SendEmailService sendEmailService;
    @GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> pw_find(String email, String name){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = memberApiLogicService.userEmailCheck(email,name);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }
    @GetMapping("/check/findId")
    public @ResponseBody Map<String, String> id_find(String hp){
        Map<String,String> json = new HashMap<>();
        String resultEmail = memberApiLogicService.userHpCheck(hp);

        System.out.println(resultEmail);

        json.put("resultEmail", resultEmail);
        return json;
    }
    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
    @PostMapping("/check/findPw/sendEmail")
    public @ResponseBody void sendEmail(String email, String name){
        MailDTO dto = sendEmailService.createMailAndChangePassword(email, name);
        sendEmailService.mailSend(dto);

    }
}
