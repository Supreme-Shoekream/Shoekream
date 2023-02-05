package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByProductIdx(Long productIdx);

    Long countByProduct(Product product);
}
