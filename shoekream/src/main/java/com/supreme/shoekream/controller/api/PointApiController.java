package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.service.PointApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/point")
public class PointApiController {
    private final PointApiLogicService pointApiLogicService;

    @PostMapping("")
    public String create(@RequestBody Point point){
        pointApiLogicService.create(point);
        return "";
    }

    @PutMapping("")
    public String update(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userIdx = (Long)session.getAttribute("idx");
        pointApiLogicService.update(userIdx);
        return "";
    }
}
