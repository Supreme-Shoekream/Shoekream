package com.supreme.admin.repository;

import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.Admin;
import com.supreme.admin.model.entity.Buy;
import com.supreme.admin.model.entity.Follow;
import com.supreme.admin.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>
{
//    Optional<Product> findByIdx(Long idx);

    Product findByIdx(Long idx);

    List<Product> findByOrderByIdxDesc();
    List<Product> findTop40ByGenderOrderByWishCount(String gender);

    List<Product> findByCollection(String collection);
    List<Product> findByCategory(String category);
    Page<Product> findByGender (String gender, Pageable pageable);
    Page<Product> findByFirstPrice (String firstPrice, Pageable pageable);
    Page<Product> findByCategoryIn(List<String> catetory, Pageable pageable);
    Page<Product> findByBrandIn(List<String> brand, Pageable pageable);
    Page<Product> findByCollectionIn(List<String> collection, Pageable pageable);
    Page<Product> findBySizeContainingAndBrandContainingAndCategoryContainingAndCollectionContainingAndGenderContainingAndNameContaining(String size, String brand,
                                                                             String category, String collection,
                                                                                       String gender, String keyword, Pageable pageable);
    Page<Product> findBySize(String size, Pageable pageable);

    List<Product> findAllByName(String name);

    Page<Product> findByBrand(String brand, Pageable pageable);


    long countBy();
    List<Product> findTop10ByOrderByWishCountDesc();
    Page<Product> findByBrandContainingOrModelNum(String brand, String modelNum, Pageable pageable);
}



//    Page<Product> findByCategoryContaining (String category, Pageable pageable);
//    Page<Product> findByBrandContaining (String brand, Pageable pageable);
//    Page<Product> findByCollectionContaining (String collection, Pageable pageable);
