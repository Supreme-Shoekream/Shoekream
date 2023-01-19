package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Account;
import com.supreme.shoekream.model.entity.Member;

public record AccountDTO(
        Long idx,
        String bank,
        String accountNumber,
        String name,
        Member member
) {
    public static AccountDTO of(
            Long idx,
            String bank,
            String accountNumber,
            String name,
            Member member
    ){
        return new AccountDTO(idx, bank, accountNumber, name, member);
    }

    public Account toEntity(Member member){
        return Account.of(idx,bank,accountNumber,name,member);
    }
}
