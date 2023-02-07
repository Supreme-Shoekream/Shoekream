package com.supreme.shoekream.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.QProduct;
import com.supreme.shoekream.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends
        JpaRepository<Product, Long>,
        ProductRepositoryCustom,
        QuerydslPredicateExecutor<Product>,
        QuerydslBinderCustomizer<QProduct>
{
//    Optional<Product> findByIdx(Long idx);

    Product findByIdx(Long idx);

    List<Product> findByOrderByIdxDesc();
    List<Product> findByCollection(String collection);
    List<Product> findTop40ByGenderOrderByWishCount(String gender);
    List<Product> findByCategory(String category);
    Page<Product> findByGender (String gender, Pageable pageable);
    Page<Product> findByFirstPrice (String firstPrice, Pageable pageable);
    Page<Product> findByCategoryIn(List<String> catetory, Pageable pageable);
    Page<Product> findByBrandIn(List<String> brand, Pageable pageable);
    Page<Product> findByCollectionIn(List<String> collection, Pageable pageable);
    Page<Product> findBySizeContainingAndBrandContainingAndCategoryContainingAndCollectionContainingAndGenderContainingAndNameKorContaining(String size, String brand,
                                                                                       String category, String collection,
                                                                                       String gender, String keyword, Pageable pageable);
    Page<Product> findBySizeContaining(String size, Pageable pageable);
    List<Product> findAllByName(String name);
    Page<Product> findByBrand(String brand, Pageable pageable);


    List<Product> findBrandByBrand(String brand);

    Page<Product> findByBrandContainingOrNameKorContaining(String searchWord , String searchWord2, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings, QProduct root){
    bindings.excludeUnlistedProperties(true);
        bindings.including(root.size, root.brand, root.category, root.collection, root.name);
        bindings.bind(root.size).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.brand).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.category).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.collection).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
    }

}

