package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.service.AdminProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products") // http://localhost:8889/api/admin/products
public class AdminProductsApiController extends CrudController<ProductApiRequest, ProductApiResponse, Product> {
    @Autowired AdminProductApiLogicService adminProductApiLogicService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 생성
    @PostMapping("") // http://localhost:8889/api/admin/products
    public Header<ProductApiResponse> create(@RequestBody Header<ProductApiRequest> request) {
        return adminProductApiLogicService.create(request);
    }

    // 리스트 출력
    @GetMapping("") // http://localhost:8889/api/admin/products
    public Header<List<ProductApiResponse>> findAll(@PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC)Pageable pageable){
        return adminProductApiLogicService.search(pageable);
    }

    // 상세 보기
    @GetMapping("/{idx}") // http://localhost:8889/api/admin/products/{idx}
    public Header<ProductApiResponse> read(@PathVariable(name="idx") Long idx) {
        return adminProductApiLogicService.read(idx);
    }

    // 수정
    @PutMapping("/{idx}") // http://localhost:8889/api/admin/products/{idx}
    public Header<ProductApiResponse> update(@RequestBody Header<ProductApiRequest> request) {
        return adminProductApiLogicService.update(request);
    }

    // 삭제
    @DeleteMapping("/{idx}") // http://localhost:8889/api/admin/products/{idx}
    public Header<ProductApiResponse> delete(@PathVariable(name="idx") Long idx) {
        return adminProductApiLogicService.delete(idx);
    }

    // 첨부 파일 업로드(생성)
    @PostMapping("/uploadFile") // 파일 1개 업로드 //http://localhost:8889/api/admin/products/uploadFile
    public String uploadAjaxActionPOST(MultipartFile uploadFile) {
        logger.info("⚠️uploadAjaxActionPOST..........");
        logger.info("⚠️파일 이름 : " + uploadFile.getOriginalFilename());
        logger.info("⚠️파일 타입 : " + uploadFile.getContentType());
        logger.info("⚠️파일 크기 : " + uploadFile.getSize());
        // 파일 저장 폴더 경로
        String uploadFilePath = "/Users/oyun-yeong/Desktop/Shoekream/publising/Shoekream/shoekream/src/main/resources/static/img/product/";
        // 폴더 생성
        File uploadPath = new File(uploadFilePath);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String uploadFileName = uploadFile.getOriginalFilename(); // 파일 이름
//        uploadFileName = uploadFileName.replace(" ", "_"); // 파일 이름에 띄어쓰기가 있으면 언더바로 변경하기
//        System.out.println("🔵" + uploadFileName);
//        File saveFile = new File(uploadPath, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
        File saveFile = new File(uploadFilePath, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
        try { // 파일 저장
            uploadFile.transferTo(saveFile);
            logger.info("🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢");
            logger.info("이미지 파일 저장 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadFileName;
//        return uploadFile;
    }
}
