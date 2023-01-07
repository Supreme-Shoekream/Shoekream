package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.service.MemberApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")    // http://localhost:9999/api/admin/users
@RequiredArgsConstructor
public class MemberApiController extends CrudController<MemberApiRequest, MemberApiResponse, Member> {
    private final MemberApiLogicService memberApiLogicService;

    @Override
    @PostMapping("create")    // http://localhost:9999/api/admin/users/create (post)
    public Header<MemberApiResponse> create(@RequestBody Header<MemberApiRequest> request) {
        return memberApiLogicService.create(request);
    }

    @GetMapping("") //
    public Header<List<MemberApiResponse>> findAll(@PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC) Pageable pageable){
        return memberApiLogicService.search(pageable);
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
}







