package com.supreme.shoekream.repository;

import com.supreme.shoekream.ShoekreamApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest extends ShoekreamApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void read(){
        System.out.println(productRepository.findBySizeContainingAndBrandContainingAndCategoryContainingAndCollectionContainingAndGenderContainingAndNameContaining(
                "","","","","","", Pageable.unpaged()).stream().toList());
    }
}