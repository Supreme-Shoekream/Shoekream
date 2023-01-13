package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.service.PointApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
