package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.ConclusionDTO;
import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.repository.ConclusionRepository;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConclusionApiLogicService {
    private final ProductRepository productRepository;
    private final ConclusionRepository conclusionRepository;


    public List<ConclusionDTO> conclusionList(Long productIdx){
        Product product = productRepository.findById(productIdx).get();
        return conclusionRepository.findAllByProductOrderByCreatedAtDesc(product).stream()
        // .map(ConclusionDTO::fromEntity).collect(Collectors.toList());
                .map(ConclusionDTO::fromEntity).collect(Collectors.toCollection(LinkedList::new));
        // Collectors: Stream을 일반적인 List, Set등으로 변경시키는 Stream 메서드
    }

    public Conclusion recentPrice(Long productIdx){
        return conclusionRepository.findTop1ByProductIdxOrderByCreatedAtDesc(productIdx);
    }

}
