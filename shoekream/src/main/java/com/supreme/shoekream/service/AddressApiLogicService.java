package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.AddressRepository;
import com.supreme.shoekream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class AddressApiLogicService {
    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public List<Address> list(Long memberIdx, boolean isBasic){
        List<Address> addressList = addressRepository.findByMemberIdxAndAddressBasic(memberIdx, isBasic);
        return addressList;

    }

    @Transactional
    public Header<AddressDTO> create(AddressDTO request){
        Member member = request.memberDTO().toEntity();
        Address newAddress = addressRepository.save(request.toEntity(member));
        AddressDTO addressDTO = AddressDTO.fromEntity(newAddress);
        return Header.OK(addressDTO);
    }

    @Transactional
    public Header<AddressDTO> update(AddressDTO dto, Long idx) {
        try{
            Address address = addressRepository.findByIdx(idx);
            if(dto.name() != null){address.setName(dto.name());}
            if(dto.hp() != null){address.setHp(dto.hp());}
            if(dto.zipcode() != null){address.setZipcode(dto.zipcode());}
            if(dto.address1() != null){address.setAddress1(dto.address1());}
            if(dto.address2() != null){address.setAddress2(dto.address2());}
            address.setAddressBasic(dto.addressBasic());
        }catch (EntityNotFoundException e){

        }
        return Header.OK();
    }

    @Transactional
    public void delete(Long idx) {
        Address address = addressRepository.findByIdx(idx);
        addressRepository.delete(address);
    }
}