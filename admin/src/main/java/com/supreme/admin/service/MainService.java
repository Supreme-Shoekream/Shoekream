package com.supreme.admin.service;

import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.Product;
import com.supreme.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MainService {
    final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> collectionList(String collection){
        return productRepository.findByCollection(collection).stream().map(ProductDTO::fromEntity).toList();
    }

}
