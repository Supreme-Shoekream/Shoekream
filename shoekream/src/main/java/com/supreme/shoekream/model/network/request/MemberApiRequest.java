package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
public record MemberApiRequest(
        String memberPw,
        String name,
        String nickname,
        String hp,
        String email,
        String shoeSize,
        String accNumber,
        String bank,
        String imgUrl

) {

    public MemberDTO toDTO(){
        return MemberDTO.of(
                memberPw,
                name,
                nickname,
                hp,
                email,
                shoeSize,
                bank,
                accNumber,
                imgUrl

        );
    }
}