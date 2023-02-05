package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.CardDTO;
import com.supreme.shoekream.model.entity.Card;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.CardApiRequest;
import com.supreme.shoekream.model.network.response.CardApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.CardApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/my/payment")
public class CardApiController  extends CrudController<CardApiRequest, CardApiResponse, Card> {
    private final CardApiLogicService cardApiLogicService;

    @PostMapping("")
    public Header<CardDTO> create(@RequestBody Header<CardApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        CardApiRequest cardApiRequest = request.getData();
        CardDTO cardDTO = cardApiRequest.toDTO(kreamPrincipal.toFullDto());
        return cardApiLogicService.create(cardDTO);
    }

    @PutMapping("/{idx}")
    public void update(@PathVariable Long idx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        cardApiLogicService.update(idx, kreamPrincipal.toFullDto());
    }

    @PostMapping("/{idx}")
    public void deleteCard(@PathVariable Long idx){
        cardApiLogicService.delete(idx);
    }

    @GetMapping("")
    public List<Card> list(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return cardApiLogicService.listAll(kreamPrincipal.idx());
    }
}
