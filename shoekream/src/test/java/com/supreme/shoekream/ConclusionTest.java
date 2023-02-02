package com.supreme.shoekream;

import com.supreme.shoekream.config.JpaConfig;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.repository.ConclusionRepository;
import com.supreme.shoekream.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(JpaConfig.class)
@DisplayName("conclusionRepository 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConclusionTest {

    private final ConclusionRepository conclusionRepository;
    @Autowired
    private ProductRepository productRepository;

    public ConclusionTest (@Autowired ConclusionRepository conclusionRepository){
        this.conclusionRepository = conclusionRepository;
    }

    @Test
    void conTest1(){
        Long proudctIdx = 106L;
        conclusionRepository.findByProductIdx(proudctIdx);
    }

    @Test
    void conTest2(){
        Long proudctIdx = 106L;
        Product product = productRepository.findByIdx(proudctIdx);
        conclusionRepository.findAllByProduct(product);
    }
}
