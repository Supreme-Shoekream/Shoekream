package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Notice;
import com.supreme.shoekream.model.network.request.NoticeApiRequest;
import com.supreme.shoekream.model.network.response.NoticeApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // url 호출시 json 응답/요청 가능
@RequestMapping("/api/notice") // http://localhost:8888/api/notice
@RequiredArgsConstructor
public class NoticeApiController extends CrudController<NoticeApiRequest, NoticeApiResponse, Notice> {

}
