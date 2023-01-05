package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Card;
import com.supreme.shoekream.model.network.request.CardApiRequest;
import com.supreme.shoekream.model.network.response.CardApiResponse;
import com.supreme.shoekream.service.CardApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my/payment")
public class CardApiController  extends CrudController<CardApiRequest, CardApiResponse, Card> {
    private final CardApiLogicService cardApiLogicService;
}
