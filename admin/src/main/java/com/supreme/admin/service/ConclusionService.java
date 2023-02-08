package com.supreme.admin.service;

import com.supreme.admin.model.dto.BuyDTO;
import com.supreme.admin.model.dto.ConclusionDTO;
import com.supreme.admin.model.entity.Conclusion;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.repository.ConclusionRepository;
import com.supreme.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ConclusionService {
    @Autowired ConclusionRepository conclusionRepository;
    @Autowired ProductRepository productRepository;

    // 관리자페이지 체결내역 리스트
    @Transactional(readOnly = true)
    public Page<ConclusionDTO> searchConclusion(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return conclusionRepository.findAll(pageable).map(ConclusionDTO::fromEntity);
        }
        // 검색 키워드: 상품명 또는 모델번호
        return conclusionRepository.findByProduct_ModelNumContainingOrProduct_NameContaining(searchKeyword, searchKeyword, pageable).map(ConclusionDTO::fromEntity);
    }

    // 관리자페이지 대시보드 체결내역 그래프
    public List<ConclusionDTO> conclusionList(){
        return conclusionRepository.findAll().stream()
                .map(ConclusionDTO::fromEntity).collect(Collectors.toCollection(LinkedList::new));
    }



}
