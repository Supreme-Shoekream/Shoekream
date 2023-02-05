package com.supreme.admin.controller.api;

import com.supreme.admin.controller.CrudController;
import com.supreme.admin.model.entity.Notice;
import com.supreme.admin.model.network.request.NoticeApiRequest;
import com.supreme.admin.model.network.response.NoticeApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // url 호출시 json 응답/요청 가능
@RequestMapping("/api/notice") // http://localhost:8888/api/notice
@RequiredArgsConstructor
public class NoticeApiController extends CrudController<NoticeApiRequest, NoticeApiResponse, Notice> {

}
