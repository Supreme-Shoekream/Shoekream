package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByIdx(Long idx);
    Optional<Address> findByName(String name);
    List<Address> findByMemberIdx(Long memberIdx);
    List<Address> findByMemberIdxAndAddressBasic(Long memberIdx, boolean isBasic);
    List<Address> deleteByMemberIdx(Long idx);
}
