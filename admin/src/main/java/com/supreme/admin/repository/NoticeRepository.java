package com.supreme.admin.repository;

import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
