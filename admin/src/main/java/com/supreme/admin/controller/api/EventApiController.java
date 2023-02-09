package com.supreme.admin.controller.api;

import com.supreme.admin.controller.CrudController;
import com.supreme.admin.model.dto.EventDTO;
import com.supreme.admin.model.dto.NoticeDTO;
import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.EventApiRequest;
import com.supreme.admin.model.network.request.NoticeApiRequest;
import com.supreme.admin.model.network.response.EventApiResponse;
import com.supreme.admin.repository.EventMemberRepository;
import com.supreme.admin.service.BuyService;
import com.supreme.admin.service.EventApiService;
import com.supreme.admin.service.EventMemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/event")
public class EventApiController extends CrudController<EventApiRequest, EventDTO, EventProduct> {
    private final EventApiService eventApiService;
    private final EventMemberService eventMemberService;

    private final BuyService buyService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @PostMapping("")

    public Header<EventDTO> create(@RequestBody Header<EventApiRequest> request){
        EventApiRequest eventApiRequest = request.getData();
        ProductDTO productDTO = buyService.findProduct(eventApiRequest.productIdx());
        EventDTO eventDTO = eventApiRequest.toDTO(productDTO);
        return eventApiService.create(eventDTO);
    }

    @GetMapping("/{idx}")
    public EventApiResponse eventRead(@PathVariable(name="idx")Long idx){
        EventDTO event = eventApiService.eventDetail(idx);
        return EventApiResponse.from(event);
    }

    @PutMapping("/{idx}")
    public void update(@PathVariable(name="idx")Long idx , @RequestBody Header<EventApiRequest> eventRequest){
        eventApiService.update(idx, eventRequest);
    }

    @DeleteMapping("/{idx}")
    public void deleteCard(@PathVariable Long idx){
        eventApiService.delete(idx);
    }

    @PostMapping("/{idx}")
    public void draw(@RequestBody Header<EventApiRequest> request){
        Long idx = request.getData().idx();
        eventApiService.draw(idx);
    }

    // 첨부 파일 업로드
    @PostMapping("/uploadFile")  //http://localhost:8899/api/admin/event/uploadFile
    public String uploadAjaxActionPOST(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile) {
        logger.info("⚠️uploadAjaxActionPOST..........");
        logger.info("⚠️파일 이름 : " + uploadFile.getOriginalFilename());
        logger.info("⚠️파일 타입 : " + uploadFile.getContentType());
        logger.info("⚠️파일 크기 : " + uploadFile.getSize());
        // 파일 저장 폴더 경로
        String local = "/Users/oyun-yeong/img";
        // 폴더 생성
        File uploadPath = new File(local);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String uploadFileName = uploadFile.getOriginalFilename(); // 파일 이름

        File saveFile = new File(local, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
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
