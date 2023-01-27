package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Admin;
import com.supreme.shoekream.model.entity.Follow;
import com.supreme.shoekream.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>
{
    Optional<Product> findByIdx(Long idx);

    List<Product> findByOrderByIdxDesc();
    List<Product> findByCollection(String collection);

    Page<Product> findByGender (String gender, Pageable pageable);
    Page<Product> findByFirstPrice (String firstPrice, Pageable pageable);
    Page<Product> findByCategoryIn(List<String> catetory, Pageable pageable);
    Page<Product> findByBrandIn(List<String> brand, Pageable pageable);
    Page<Product> findByCollectionIn(List<String> collection, Pageable pageable);
    Page<Product> findBySizeContainingAndBrandContainingAndCategoryContainingAndCollectionContainingAndGenderContainingAndNameContaining(String size, String brand,
                                                                                       String category, String collection,
                                                                                       String gender, String keyword, Pageable pageable);
    Page<Product> findBySize(String size, Pageable pageable);
}



//    Page<Product> findByCategoryContaining (String category, Pageable pageable);
//    Page<Product> findByBrandContaining (String brand, Pageable pageable);
//    Page<Product> findByCollectionContaining (String collection, Pageable pageable);
