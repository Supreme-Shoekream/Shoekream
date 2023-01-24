package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Member;

public record AddressApiResponse(
        Long idx,
        String name,
        String hp,
        String zipcode,
        String address1,
        String address2,
        boolean addressBasic
) {
    public static AddressApiResponse of(
            Long idx,
            String name,
            String hp,
            String zipcode,
            String address1,
            String address2,
            boolean addressBasic
    ){
        return new AddressApiResponse(idx,name, hp, zipcode, address1, address2, addressBasic);
    }
    public AddressDTO toDTO(Member member){
        return AddressDTO.of(
                idx,
                name,
                hp,
                zipcode,
                address1,
                address2,
                addressBasic,
                MemberDTO.fromEntity(member)
        );
    }
}
