package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Address;

public record AddressDTO(
        Long idx,
        String name,
        String hp,
        String zipcode,
        String address1,
        String address2,
        boolean addressBasic,
        Long memberIdx
) {
    // json을 of객체에 저장(Builder)
    public static AddressDTO of(
            Long idx,
            String name,
            String hp,
            String zipcode,
            String address1,
            String address2,
            boolean addressBasic,
            Long memberIdx
    ){
        return new AddressDTO(idx, name, hp, zipcode, address1, address2, addressBasic, memberIdx);
    }

    public Address toEntity(){
        return Address.of(
            name,hp,zipcode,address1,address2,addressBasic,memberIdx
        );
    }
}
