package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.entity.Member;

public record AddressDTO(
        Long idx,
        String name,
        String hp,
        String zipcode,
        String address1,
        String address2,
        boolean addressBasic,
        MemberDTO memberDTO
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
            MemberDTO memberDTO
    ){
        return new AddressDTO(idx, name, hp, zipcode, address1, address2, addressBasic, memberDTO);
    }
    public static AddressDTO fromEntity(Address address){
        return AddressDTO.of(
                address.getIdx(),
                address.getName(),
                address.getHp(),
                address.getZipcode(),
                address.getAddress1(),
                address.getAddress2(),
                address.isAddressBasic(),
                MemberDTO.fromEntity(address.getMember())
        );
    }

    public Address toEntity(Member member){
        return Address.of(
                name,
                hp,
                zipcode,
                address1,
                address2,
                addressBasic,
                member
        );
    }
}
