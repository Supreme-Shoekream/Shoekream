package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.PenaltyDTO;
import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Penalty;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.response.PenaltyResponse;
import com.supreme.shoekream.model.network.response.PointApiResponse;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.repository.PenaltyRepository;
import com.supreme.shoekream.repository.SellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PenaltyApiLogicService {
    private final PenaltyRepository penaltyRepository;

    private final MemberRepository memberRepository;
    private final SellRepository sellRepository;

    @Transactional(readOnly = true)
    public Page<PenaltyDTO> searchPenalty(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return penaltyRepository.findAll(pageable).map(PenaltyDTO::fromEntity);
        }
        //아직 검색구현 안됨: sell -> member hp bank??
        return null;
    }

    @Transactional(readOnly = true)
    public PenaltyDTO detail(Long penaltyIdx){
        return penaltyRepository.findById(penaltyIdx)
                .map(PenaltyDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("패널티 내역 없음"));
    }

    public Header<PenaltyDTO> create(PenaltyDTO penaltyDTO){
        Sell sell  = sellRepository.findById(penaltyDTO.sellIdx()).get();
        Penalty newPenalty = penaltyRepository.save(penaltyDTO.toEntity(sell));
        PenaltyDTO response = PenaltyDTO.fromEntity(newPenalty);
        return Header.OK(response);
    }

    public Header delete(Long idx){
        Optional<Penalty> penalty = penaltyRepository.findById(idx);
        return penalty.map(penalty1 -> {
            penaltyRepository.delete(penalty1);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }
}
