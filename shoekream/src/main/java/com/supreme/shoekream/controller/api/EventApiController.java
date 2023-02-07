package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.dto.EventMemberDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.EventProduct;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.EventApiRequest;
import com.supreme.shoekream.model.network.response.EventApiResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.service.BuyService;
import com.supreme.shoekream.service.EventApiService;
import com.supreme.shoekream.service.EventMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/event")
public class EventApiController extends CrudController<EventApiRequest, EventDTO, EventProduct> {
    private final EventApiService eventApiService;
    private final EventMemberService eventMemberService;
    private  final BuyService buyService;
   @PostMapping("")
    public Header<EventDTO> create(@RequestBody Header<EventApiRequest> request){
        EventApiRequest eventApiRequest = request.getData();
        ProductDTO productDTO = buyService.findProduct(eventApiRequest.productIdx());
        EventDTO eventDTO = eventApiRequest.toDTO(productDTO);
        return eventApiService.create(eventDTO);
    }
    @GetMapping("/{idx}")
    public EventApiResponse eventRead(@PathVariable(name="idx")Long idx){
        EventDTO event = eventApiService.eventDetail(idx);
        return EventApiResponse.from(event);
    }


    @PutMapping("/{idx}")
    public void update(@PathVariable(name="idx")Long idx , @RequestBody Header<EventApiRequest> eventRequest){
        eventApiService.update(idx, eventRequest);
    }

    @DeleteMapping("/{idx}")
    public void deleteCard(@PathVariable Long idx){
        eventApiService.delete(idx);
    }

    @PostMapping("/draw")
    public Header<EventMemberDTO> draw(@RequestBody Header<EventMemberDTO> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
       EventMemberDTO eventMemberDTO = request.getData();
       return eventMemberService.create(eventMemberDTO, kreamPrincipal.idx());
    }
}
