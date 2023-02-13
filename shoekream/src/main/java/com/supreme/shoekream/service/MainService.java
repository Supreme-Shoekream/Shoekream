package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.repository.ConclusionRepository;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MainService {
    final ProductRepository productRepository;
    final ConclusionRepository conclusionRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> collectionList(String collection){
        return productRepository.findByCollection(collection).stream().map(ProductDTO::fromEntity).toList();
    }
    public List<Product> bestSeller() {
        List<Product> all = productRepository.findAll();
        Map<Long, Long> dealCount = new HashMap<>();  //index, count
        List<Product> best10 = new ArrayList<>();
        all.forEach(
                product -> {
                    dealCount.put(product.getIdx(), conclusionRepository.countByProductAndCreatedAtAfter(product, LocalDateTime.now().minusMonths(12L)));
                }
        );
        // value로 내림차순 정렬 get(value1)
        List<Long> listKeySet = new ArrayList<>(dealCount.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (dealCount.get(value2).compareTo(dealCount.get(value1))));
        for(int i=0; i<10; i++) best10.add(productRepository.findByIdx(listKeySet.get(i)));
        return best10;
    }
}
