package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Point;
import com.supreme.shoekream.model.network.request.PointApiRequest;
import com.supreme.shoekream.model.network.response.PointApiResponse;
import com.supreme.shoekream.service.PointApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my/point")
public class PointApiController  extends CrudController<PointApiRequest, PointApiResponse, Point> {
    private final PointApiLogicService pointApiLogicService;
}
