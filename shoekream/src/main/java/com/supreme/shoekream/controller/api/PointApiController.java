package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.PointApiRequest;
import com.supreme.shoekream.model.network.response.AdminApiResponse;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.model.network.response.PointApiResponse;
import com.supreme.shoekream.service.AdminApiLogicService;
import com.supreme.shoekream.service.MemberApiLogicService;
import com.supreme.shoekream.service.PointApiLogicService;
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

//    @PutMapping("")
//    public String update(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        Long userIdx = (Long)session.getAttribute("idx");
//        pointApiLogicService.update(userIdx);
//        return "";
//    }
}
