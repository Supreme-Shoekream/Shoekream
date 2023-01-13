package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class AddressApiLogicService {
    private final AddressRepository addressRepository;
    @Transactional
    public List<Address> list(){
        return addressRepository.findAll();
    }

    @Transactional
    public Header<Address> create(Header<Address> request){
        Long memberIdx = 11L; // 임시로 설정
        Address addressRequest = request.getData();
        Address address = Address.builder().name(addressRequest.getName()).hp(addressRequest.getHp())
                .zipcode(addressRequest.getZipcode()).address1(addressRequest.getAddress1())
                .address2(addressRequest.getAddress2())
                .memberIdx(memberIdx).addressBasic(addressRequest.isAddressBasic()).build();
        // Long memberIdx = session.getAttribute("idx")
        Address newAddress =addressRepository.save(address);
        return Header.OK(newAddress);
    }

    @Transactional
    public Header<Address> update(Header<Address> request) {
        Address addressRequest = request.getData();
        Optional<Address> address = addressRepository.findByIdx(11L);
        // 세션처리
        return address.map(
                        addr -> {
                            addr.setName(addressRequest.getName());
                            addr.setHp(addressRequest.getHp());
                            addr.setZipcode(addressRequest.getZipcode());
                            addr.setAddress1(addressRequest.getAddress1());
                            addr.setAddress2(addressRequest.getAddress2());
                            addr.setAddressBasic(addressRequest.isAddressBasic());
                            return addr;
                        }).map(addr -> addressRepository.save(addr))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음")
                );
    }
    @Transactional
    public Header delete(Long idx) {
        Optional<Address> address = addressRepository.findByIdx(idx);
        return address.map(addr -> {
            addressRepository.delete(addr);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }
}