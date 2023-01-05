package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
