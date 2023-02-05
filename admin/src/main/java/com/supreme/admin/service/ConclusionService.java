package com.supreme.admin.service;

import com.supreme.admin.model.dto.BuyDTO;
import com.supreme.admin.model.dto.ConclusionDTO;
import com.supreme.admin.repository.ConclusionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ConclusionService {
    private final ConclusionRepository conclusionRepository;

    // 관리자페이지 체결내역 리스트
    @Transactional(readOnly = true)
    public Page<ConclusionDTO> searchConclusion(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return conclusionRepository.findAll(pageable).map(ConclusionDTO::fromEntity);
        }
        return conclusionRepository.findByProduct_ModelNumContainingOrProduct_NameContaining(searchKeyword, searchKeyword, pageable).map(ConclusionDTO::fromEntity);
    }
}
