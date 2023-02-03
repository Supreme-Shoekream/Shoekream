package com.supreme.admin.controller.api;

import com.supreme.admin.model.dto.PointDTO;
import com.supreme.admin.model.entity.Point;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.PointApiRequest;
import com.supreme.admin.model.network.response.AdminApiResponse;
import com.supreme.admin.model.network.response.MemberApiResponse;
import com.supreme.admin.model.network.response.PointApiResponse;
import com.supreme.admin.service.AdminApiLogicService;
import com.supreme.admin.service.MemberApiLogicService;
import com.supreme.admin.service.PointApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/my/point")
public class PointApiController {
    private final PointApiLogicService pointApiLogicService;

    @PostMapping("/{idx}")
    public Header<PointApiResponse> create(@RequestBody Header<PointApiRequest> request,@PathVariable(name = "idx") Long idx){
        PointApiRequest pointApiRequest = request.getData();
        PointDTO pointDTO = pointApiRequest.toDTO();
        pointApiLogicService.update(pointDTO, idx);
        return pointApiLogicService.create(pointDTO, idx);
    }

}
