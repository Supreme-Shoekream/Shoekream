package com.supreme.admin.controller.api;

import com.supreme.admin.controller.CrudController;
import com.supreme.admin.model.dto.EventDTO;
import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.EventApiRequest;
import com.supreme.admin.model.network.response.EventApiResponse;
import com.supreme.admin.service.BuyService;
import com.supreme.admin.service.EventApiService;
import com.supreme.admin.service.EventMemberService;
import lombok.RequiredArgsConstructor;
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


}
