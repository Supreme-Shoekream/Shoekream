package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.entity.Admin;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.AdminApiRequest;
import com.supreme.shoekream.model.network.response.AdminApiResponse;
import com.supreme.shoekream.service.AdminApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")    // http://localhost:8888/api/admin
@RequiredArgsConstructor
public class AdminApiController extends CrudController<AdminApiRequest, AdminApiResponse, Admin> {

    private final AdminApiLogicService adminApiLogicService;

    @Override
    @PostMapping("")    // http://localhost:8888/api/admin (post)
    public Header<AdminApiResponse> create(@RequestBody Header<AdminApiRequest> request) {
        return adminApiLogicService.create(request);
    }

    @Override
    @GetMapping("{idx}")     //http://localhost:8888/api/admin/{id} (get)
    public Header<AdminApiResponse> read(@PathVariable(name="idx") Long id) {
        return adminApiLogicService.read(id);
    }

    @Override
    @PutMapping("")     //http://localhost:8888/api/admin/ (put)
    public Header<AdminApiResponse> update(@RequestBody Header<AdminApiRequest> request) {
        return adminApiLogicService.update(request);
    }


    @GetMapping("")     //http://localhost:8888/api/admin?page=1 (get)      //idx 대신 변수를 넣으면 sort종류를 바꿀 수 있음
    public Header<List<AdminApiResponse>> findAll(@PageableDefault(sort={"idx"},direction= Sort.Direction.DESC) Pageable pageable){
        return adminApiLogicService.search(pageable);
    }
    @Override
    @DeleteMapping("{idx}")  //http://localhost:8888/api/admin/{idx} (delete)
    public Header<AdminApiResponse> delete(@PathVariable(name="idx") Long idx) {
        return adminApiLogicService.delete(idx);
    }

    @PostMapping("/login")  //api/admin/login
    public Header<AdminApiResponse> login(@RequestBody Header<AdminApiRequest> request) {
        return adminApiLogicService.login(request);
    }


}