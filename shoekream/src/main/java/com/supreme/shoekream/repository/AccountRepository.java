package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByIdx(Long idx);

    Account getReferenceByIdx(Long idx);
    Optional<Account> deleteByIdx(Long idx);
}
