package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.PenaltyDTO;
import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Penalty;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.PenaltyResponse;
import com.supreme.shoekream.model.network.response.PointApiResponse;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.PenaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PenaltyApiLogicService {
    private final PenaltyRepository penaltyRepository;
    private final MemberRepository memberRepository;

//    public Header<PenaltyResponse> create(PenaltyDTO dto, Long idx){
//        Member member = memberRepository.getReferenceByIdx(idx);
//        penaltyRepository.save(dto.toEntity(member));
//        return Header.OK();
//    }
}
