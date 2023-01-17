package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.ConclusionDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.model.entity.Follow;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ConclusionResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.ProductWithConclusionApiResponse;
import com.supreme.shoekream.repository.ConclusionRepository;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConclusionApiLogicService {
    private final ProductRepository productRepository;
    private final ConclusionRepository conclusionRepository;


    // 관리자페이지 체결내역 리스트 출력
    public List<ConclusionDTO> con_read(Long productIdx){
        Product product = productRepository.findById(productIdx).get();
        return conclusionRepository.findAllByProduct(product).stream()
//              .map(ConclusionDTO::fromEntity).collect(Collectors.toList());
                .map(ConclusionDTO::fromEntity).collect(Collectors.toCollection(LinkedList::new));
        // Collectors: Stream을 일반적인 List, Set등으로 변경시키는 Stream 메서드
    }


}
