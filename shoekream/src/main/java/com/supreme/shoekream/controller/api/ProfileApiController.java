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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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

    @PostMapping("delete")
    public Header<MemberApiResponse> deleteProfileImg(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return memberApiLogicService.deleteProfile(kreamPrincipal.idx());
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 첨부 파일 업로드
    @PostMapping("/modify_img") // http://localhost:8889/api/my/profile/modify_img
    public String uploadAjaxActionPOST(@RequestParam(value = "modify_img", required = false) MultipartFile uploadFile) {
        logger.info("⚠️uploadAjaxActionPOST..........");
        logger.info("⚠️파일 이름 : " + uploadFile.getOriginalFilename());
        logger.info("⚠️파일 타입 : " + uploadFile.getContentType());
        logger.info("⚠️파일 크기 : " + uploadFile.getSize());
        // 파일 저장 폴더 경로
        String uploadFilePath = "/Users/oyun-yeong/img"; // 로컬주소 -> img폴더 생성한것
        // 폴더 생성
        File uploadPath = new File(uploadFilePath);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String uploadFileName = uploadFile.getOriginalFilename(); // 파일 이름
        File saveFile = new File(uploadFilePath, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
        try { // 파일 저장
            uploadFile.transferTo(saveFile);
            logger.info("🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢");
            logger.info("이미지 파일 저장 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadFileName;
    }
}
