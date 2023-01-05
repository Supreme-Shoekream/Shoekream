package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Admin;
import com.supreme.shoekream.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
