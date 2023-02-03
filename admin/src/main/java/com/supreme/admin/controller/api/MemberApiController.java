package com.supreme.admin.controller.api;

import com.supreme.admin.controller.CrudController;
import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.MemberApiRequest;
import com.supreme.admin.model.network.response.MemberApiResponse;
import com.supreme.admin.service.MemberApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")    // http://localhost:9999/api/admin/users
@RequiredArgsConstructor
public class MemberApiController extends CrudController<MemberApiRequest, MemberApiResponse, Member> {
    private final MemberApiLogicService memberApiLogicService;

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

}







