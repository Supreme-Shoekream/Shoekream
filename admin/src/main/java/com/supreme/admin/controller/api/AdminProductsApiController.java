package com.supreme.admin.controller.api;

import com.supreme.admin.controller.CrudController;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.ProductApiRequest;
import com.supreme.admin.model.network.response.ProductApiResponse;
import com.supreme.admin.service.AdminProductApiLogicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products") // http://localhost:8899/api/admin/products
public class AdminProductsApiController extends CrudController<ProductApiRequest, ProductApiResponse, Product> {
    @Autowired AdminProductApiLogicService adminProductApiLogicService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 생성
    @PostMapping("") // http://localhost:8899/api/admin/products
    public Header<ProductApiResponse> create(@RequestBody Header<ProductApiRequest> request) {
        return adminProductApiLogicService.create(request);
    }

    // 리스트 출력
    @GetMapping("") // http://localhost:8899/api/admin/products
    public Header<List<ProductApiResponse>> findAll(@PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC)Pageable pageable){
        return adminProductApiLogicService.search(pageable);
    }
//    @GetMapping("") // http://localhost:8899/api/admin/products
//    public String findAll(
//            @RequestParam(required = false) Long searchKeyword,
//            @RequestParam(required = false) String searchKeyword2,
//            @PageableDefault(size = 10, sort = "idx", direction = Sort.Direction.DESC)Pageable pageable,
//            ModelMap modelMap, HttpServletRequest request){
//        Page<ProductApiResponse> productes = adminProductApiLogicService.search(searchKeyword2, searchKeyword, pageable).map(ProductApiResponse::from);
//        return adminProductApiLogicService.search(pageable);
//    }

    // 상세 보기
    @GetMapping("/{idx}") // http://localhost:8899/api/admin/products/{idx}
    public Header<ProductApiResponse> read(@PathVariable(name="idx") Long idx) {
        return adminProductApiLogicService.read(idx);
    }

    // 수정
    @PutMapping("/{idx}") // http://localhost:8899/api/admin/products/{idx}
    public Header<ProductApiResponse> update(@RequestBody Header<ProductApiRequest> request) {
        return adminProductApiLogicService.update(request);
    }

    // 삭제
    @DeleteMapping("/{idx}") // http://localhost:8899/api/admin/products/{idx}
    public Header<ProductApiResponse> delete(@PathVariable(name="idx") Long idx) {
        return adminProductApiLogicService.delete(idx);
    }

    // 첨부 파일 업로드(생성)
    @PostMapping("/uploadFile") // 파일 1개 업로드 //http://localhost:8899/api/admin/products/uploadFile
    public String uploadAjaxActionPOST(@RequestParam(value = "uploadFile", required = false)MultipartFile uploadFile) {
        logger.info("⚠️uploadAjaxActionPOST..........");
        logger.info("⚠️파일 이름 : " + uploadFile.getOriginalFilename());
        logger.info("⚠️파일 타입 : " + uploadFile.getContentType());
        logger.info("⚠️파일 크기 : " + uploadFile.getSize());
        // 파일 저장 폴더 경로
        String uploadFilePath = "/Users/oyun-yeong/Desktop/Shoekream/publising/Shoekream/shoekream/src/main/resources/static/img/product/";
        String local = "/Users/oyun-yeong/img";
        // 폴더 생성
        File uploadPath = new File(local);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String uploadFileName = uploadFile.getOriginalFilename(); // 파일 이름
//        uploadFileName = uploadFileName.replace(" ", "_"); // 파일 이름에 띄어쓰기가 있으면 언더바로 변경하기
//        System.out.println("🔵" + uploadFileName);
//        File saveFile = new File(uploadPath, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
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
