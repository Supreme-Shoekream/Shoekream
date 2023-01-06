package com.supreme.shoekream.repository;

import com.supreme.shoekream.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
