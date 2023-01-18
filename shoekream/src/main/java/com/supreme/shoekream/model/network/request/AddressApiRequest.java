package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

public record AddressApiRequest(
    String name,
    String hp,
    String zipcode,
    String address1,
    String address2,
    boolean addressBasic
) {
    public static AddressApiRequest of(
            String name,
            String hp,
            String zipcode,
            String address1,
            String address2,
            boolean addressBasic
    ){
        return new AddressApiRequest(name, hp, zipcode, address1, address2, addressBasic);
    }
//    public AddressDTO toDTO(MemberDTO memberDTO){
//        return AddressDTO.of(
//
//                name,
//                hp,
//                zipcode,
//                address1,
//                address2,
//                addressBasic
//        );
//    }
}
