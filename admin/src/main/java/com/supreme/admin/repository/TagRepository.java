package com.supreme.admin.repository;

import com.supreme.admin.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByProductIdx(Long productIdx);
}
