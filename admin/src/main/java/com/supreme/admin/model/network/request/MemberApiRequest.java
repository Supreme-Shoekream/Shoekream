package com.supreme.admin.model.network.request;

import com.supreme.admin.model.dto.MemberDTO;
import lombok.Builder;



@Builder
public record MemberApiRequest(
        String memberPw,
        String name,
        String hp,
        String email,
        String shoeSize,
        String accNumber,
        String bank
) {

    public MemberDTO toDTO(){
        return MemberDTO.of(
                memberPw,
                name,
                hp,
                email,
                shoeSize,
                bank,
                accNumber
        );
    }
}