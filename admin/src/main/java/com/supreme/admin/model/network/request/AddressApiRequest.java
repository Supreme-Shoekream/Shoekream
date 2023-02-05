package com.supreme.admin.model.network.request;

import com.supreme.admin.model.dto.AddressDTO;
import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.entity.Member;

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
