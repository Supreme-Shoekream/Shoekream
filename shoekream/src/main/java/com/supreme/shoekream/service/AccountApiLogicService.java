package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.AccountDTO;
import com.supreme.shoekream.model.entity.Account;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountApiLogicService {
    private final AccountRepository accountRepository;
    @Transactional
    public Optional<Account> list(Long idx){
        Optional<Account> account = accountRepository.findByIdx(idx);
        return account;
    }
    @Transactional
    public Header<Account> create(Header<AccountDTO> request){
        AccountDTO dto = request.getData();
        accountRepository.save(dto.toEntity(dto.member()));
        return Header.OK();
    }
    @Transactional
    public void update(AccountDTO dto){
        try{
            Account account = accountRepository.getReferenceByIdx(dto.idx());
            if(dto.bank() != null) {account.setBank(dto.bank());}
            if(dto.accountNumber() != null) {account.setAccountNumber(dto.accountNumber());}
            if(dto.name() != null) {account.setName(dto.name());}
        } catch (EntityNotFoundException e){
            System.out.println("error~");
        }
    }
    @Transactional
    public void delete(Long idx){
        accountRepository.deleteByIdx(idx);
    }
}
