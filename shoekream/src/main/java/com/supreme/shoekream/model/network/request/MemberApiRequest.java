package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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