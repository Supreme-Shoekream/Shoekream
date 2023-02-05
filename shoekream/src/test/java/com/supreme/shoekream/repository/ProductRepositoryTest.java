package com.supreme.shoekream.repository;

import com.supreme.shoekream.ShoekreamApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest extends ShoekreamApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void read(){
//        System.out.println(productRepository.findBySizeContainingAndBrandContainingAndCategoryContainingAndCollectionContainingAndGenderContainingAndNameContaining(
//                "","","","","","", Pageable.unpaged()).stream().toList());
        List<String> brands = productRepository.findAllDistinctBrands();
        Collections.sort(brands);
        System.out.println(brands);
    }
}