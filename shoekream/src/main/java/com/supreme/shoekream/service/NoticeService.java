package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.NoticeDTO;
import com.supreme.shoekream.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<NoticeDTO> list (Pageable pageable){
        return noticeRepository.findAll(pageable).map(NoticeDTO::fromEntity);
    }
    public NoticeDTO read (Long idx){
        return NoticeDTO.fromEntity(noticeRepository.findById(idx).get());
    }
}
