package com.supreme.admin.service;

import com.supreme.admin.model.dto.PenaltyDTO;
import com.supreme.admin.model.dto.SellDTO;
import com.supreme.admin.model.entity.Penalty;
import com.supreme.admin.model.entity.Sell;
import com.supreme.admin.model.enumclass.Reason;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.repository.PenaltyRepository;
import com.supreme.admin.repository.SellRepository;
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
    private final SellRepository sellRepository;

    @Transactional(readOnly = true)
    public Page<PenaltyDTO> searchPenalty(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return penaltyRepository.findAll(pageable).map(PenaltyDTO::fromEntity);
        }
        return penaltyRepository.findBySell_Member_Email(searchKeyword,pageable).map(PenaltyDTO::fromEntity);

    }

    @Transactional(readOnly = true)
    public PenaltyDTO detail(Long penaltyIdx){
        return penaltyRepository.findById(penaltyIdx)
                .map(PenaltyDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("패널티 내역 없음"));
    }

    public Header<PenaltyDTO> create(Long sellIdx, Reason reason){
        Sell sell  = sellRepository.findById(sellIdx).get();
        PenaltyDTO penaltyDTO = PenaltyDTO.of(null,reason, SellDTO.fromEntity(sell),null);
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
