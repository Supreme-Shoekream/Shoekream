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
    @Transactional
    public List<Address> list(Long memberIdx, boolean isBasic){
        List<Address> addressList = addressRepository.findByMemberIdxAndAddressBasic(memberIdx, isBasic);
        return addressList;
    }
    @Transactional
    public List<Address> all(Long memberIdx){
        List<Address> addressList = addressRepository.findByMemberIdx(memberIdx);
        return addressList;
    }

    @Transactional
    public Header<AddressDTO> read(Long idx){
        Address address = addressRepository.findByIdx(idx);
        AddressDTO addressDTO = AddressDTO.fromEntity(address);
        return Header.OK(addressDTO);
    }

    @Transactional
    public Header<AddressDTO> create(AddressDTO request){
        Member member = request.memberDTO().toEntity();
        if(request.addressBasic()==true){
            // 기본 배송지 설정!
            List<Address> addresses = addressRepository.findByMemberIdx(member.getIdx());
            addresses.forEach(
                    address ->  {address.setAddressBasic(false);});
        }
        Address newAddress = addressRepository.save(request.toEntity(member));
        AddressDTO addressDTO = AddressDTO.fromEntity(newAddress);
        return Header.OK(addressDTO);

    }

    @Transactional
    public Header<AddressDTO> update(AddressDTO dto, Long idx) {
        if(dto.addressBasic() == true){
            List<Address> addresses = addressRepository.findByMemberIdx(dto.memberDTO().idx());
            addresses.forEach(
                    address -> {address.setAddressBasic(false);}
            );
        }
        Optional<Address> address = addressRepository.findById(idx);
        return address.map(
                        addressMap -> {
                            addressMap.setName(dto.name());
                            addressMap.setHp(dto.hp());
                            addressMap.setZipcode(dto.zipcode());
                            addressMap.setAddress1(dto.address1());
                            addressMap.setAddress2(dto.address2());
                            addressMap.setAddressBasic(dto.addressBasic());
                            return addressMap;
                        }).map(addressMap -> addressRepository.save(addressMap))
                .map(addressMap -> AddressDTO.fromEntity(addressMap))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Transactional
    public void delete(Long idx) {
        Address address = addressRepository.findByIdx(idx);
        addressRepository.delete(address);
    }
}