package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.BoardStyleApiRequest;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.BoardStyleApiResponse;
import com.supreme.shoekream.model.network.response.BuyListResponse;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.BuyService;
import com.supreme.shoekream.service.MemberApiLogicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MypageApiController {
    private final MemberApiLogicService memberApiLogicService;
//    private final MemberApiLogicService memberApiLogicService;
//    private final BuyService buyService;

//    @GetMapping ("")
//    public List<BuyListResponse> read(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        MemberDTO memberDTO = kreamPrincipal.toFullDto();
//        List<BuyListResponse> dto = buyService.myBuyList(memberDTO.idx());
//        System.out.println(dto);
//        return dto;
//    }

//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    // 첨부 파일 업로드(생성)
//    @PostMapping("/my/imgUpload") // http://localhost:8889/api/my/imgUpload
//    public String uploadAjaxActionPOST(@RequestParam(value = "imgUpload", required = false) MultipartFile uploadFile) {
//        logger.info("⚠️uploadAjaxActionPOST..........");
//        logger.info("⚠️파일 이름 : " + uploadFile.getOriginalFilename());
//        logger.info("⚠️파일 타입 : " + uploadFile.getContentType());
//        logger.info("⚠️파일 크기 : " + uploadFile.getSize());
//        // 파일 저장 폴더 경로
////        String uploadFilePath = "/Users/oyun-yeong/Desktop/Shoekream/publising/Shoekream/shoekream/src/main/resources/static/img/styleImg/";
//        String uploadFilePath = "D:/YJH/Shoekream/shoekream/src/main/resources/static/img/profileImg/";
//        // 폴더 생성
//        File uploadPath = new File(uploadFilePath);
//        if(!uploadPath.exists()) {
//            uploadPath.mkdirs();
//        }
//        String uploadFileName = uploadFile.getOriginalFilename(); // 파일 이름
//        File saveFile = new File(uploadFilePath, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
//        try { // 파일 저장
//            uploadFile.transferTo(saveFile);
//            logger.info("🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢");
//            logger.info("이미지 파일 저장 완료");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return uploadFileName;
//    }

    @PostMapping("/my/changeUpload") // http://localhost:8889/api/my/changeUpload
    public Header<MemberApiResponse> profileImgUpdate(@RequestBody Header<MemberApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
        MemberApiRequest memberApiRequest = request.getData();
        MemberDTO memberDTO = memberApiRequest.toDTO();
        return memberApiLogicService.updateProfileImg(memberDTO, kreamPrincipal.idx());
    }
}
