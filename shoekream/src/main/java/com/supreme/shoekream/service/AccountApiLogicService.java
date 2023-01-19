package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountApiLogicService {
    private final MemberRepository memberRepository;
    @Transactional
    public Optional<Member> list(Long idx){
        Optional<Member> member = memberRepository.findByIdx(idx);
        return member;
    }
    @Transactional
    public void update(MemberDTO dto){
        try {
            Member member = memberRepository.getReferenceByIdx(dto.idx());
            if(dto.accountNumber() != null) {member.setAccountNumber(dto.accountNumber());}
        } catch (EntityNotFoundException e){

        }
    }
    @Transactional
    public void delete(Long idx){
        memberRepository.deleteByIdx(idx);
    }
}
