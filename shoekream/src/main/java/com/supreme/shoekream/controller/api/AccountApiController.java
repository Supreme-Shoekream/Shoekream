package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.controller.CrudController;
import com.supreme.shoekream.model.entity.Account;
import com.supreme.shoekream.model.network.request.AccountApiRequest;
import com.supreme.shoekream.model.network.response.AccountApiResponse;
import com.supreme.shoekream.repository.AccountRepository;
import com.supreme.shoekream.service.AccountApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my/account")
public class AccountApiController extends CrudController<AccountApiRequest, AccountApiResponse, Account> {
    private final AccountApiLogicService accountApiLogicService;
}
