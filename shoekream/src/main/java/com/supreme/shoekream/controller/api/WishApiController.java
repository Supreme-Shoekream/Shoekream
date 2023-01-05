package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Wish;
import com.supreme.shoekream.model.network.request.WishApiRequest;
import com.supreme.shoekream.model.network.response.WishApiResponse;
import com.supreme.shoekream.service.WishApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my/wish")
public class WishApiController  extends CrudController<WishApiRequest, WishApiResponse, Wish> {
    private final WishApiLogicService wishApiLogicService;
}
