package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.BlacklistDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.enumclass.Status;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.BlacklistResponse;
import com.supreme.shoekream.repository.BlacklistRepository;
import com.supreme.shoekream.repository.MemberRepository;
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

