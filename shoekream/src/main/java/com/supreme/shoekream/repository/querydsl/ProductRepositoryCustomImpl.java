package com.supreme.shoekream.repository.querydsl;

import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    public ProductRepositoryCustomImpl() { super(Product.class); }

    @Override
    public List<String> findAllDistinctBrands() {
        QProduct product = QProduct.product;
        return from(product)
                .distinct()
                .select(product.brand)
                .where(product.brand.isNotNull())
                .fetch();
    }
}
