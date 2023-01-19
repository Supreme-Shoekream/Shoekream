package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

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
    public AddressDTO toDTO(MemberDTO memberDTO){
        return AddressDTO.of(
                idx,
                memberDTO,
                name,
                hp,
                zipcode,
                address1,
                address2,
                addressBasic,
                memberDTO.idx()
        );
    }
}
