package com.supreme.admin.service;

import com.supreme.admin.model.dto.BlacklistDTO;
import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.enumclass.Status;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.response.BlacklistResponse;
import com.supreme.admin.repository.BlacklistRepository;
import com.supreme.admin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlacklistApiLogicService {
    private final BlacklistRepository blacklistRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Header<BlacklistResponse> create(BlacklistDTO dto, Long idx){
        Member member = memberRepository.getReferenceByIdx(idx);
        blacklistRepository.save(dto.toEntity(member));
        return Header.OK();
    }

    @Transactional
    public Header<MemberDTO> update(Long idx){
        Member member = memberRepository.findByIdx(idx);
        member.setStatus(Status.RESTRICTED);
        return Header.OK();
    }
}

