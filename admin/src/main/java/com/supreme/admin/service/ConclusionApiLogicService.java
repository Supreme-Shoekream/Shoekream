package com.supreme.admin.service;

import com.supreme.admin.model.dto.ConclusionDTO;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.repository.ConclusionRepository;
import com.supreme.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConclusionApiLogicService {
    private final ProductRepository productRepository;
    private final ConclusionRepository conclusionRepository;


    public List<ConclusionDTO> con_read(Long productIdx){
        Product product = productRepository.findById(productIdx).get();
        return conclusionRepository.findAllByProduct(product).stream()
//              .map(ConclusionDTO::fromEntity).collect(Collectors.toList());
                .map(ConclusionDTO::fromEntity).collect(Collectors.toCollection(LinkedList::new));
        // Collectors: Stream을 일반적인 List, Set등으로 변경시키는 Stream 메서드
    }


}
