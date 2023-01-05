package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
