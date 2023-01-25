package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Member;

public record AddressApiRequest(
    Long idx,
    String name,
    String hp,
    String zipcode,
    String address1,
    String address2,
    boolean addressBasic
) {
    public static AddressApiRequest of(
            Long idx,
            String name,
            String hp,
            String zipcode,
            String address1,
            String address2,
            boolean addressBasic

    ){
        return new AddressApiRequest(idx,name, hp, zipcode, address1, address2, addressBasic);
    }
    public AddressDTO toDTO(MemberDTO memberDTO){
        return AddressDTO.of(
                idx,
                name,
                hp,
                zipcode,
                address1,
                address2,
                addressBasic,
                memberDTO
        );
    }
}
