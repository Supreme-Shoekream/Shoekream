package com.supreme.admin.service;

import com.supreme.admin.model.dto.*;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class EventApiService {
    final EventRepository eventRepository;
    @Transactional
    public Optional<EventDTO> list(Long idx){
        return eventRepository.findByIdx(idx)
                .map(EventDTO::fromEntity);
    }

    @Transactional
    public Header<EventProduct> create(Header<EventDTO> request){
        EventDTO dto = request.getData();
        eventRepository.save(dto.toEntity(dto.productDTO().toEntity()));
        return Header.OK();

    }

    @Transactional
    public Header<EventProduct> update(Header<EventProduct> request, Long idx) {
        EventProduct eventProductRequest = request.getData();
        Optional<EventProduct> eventProduct = eventRepository.findByIdx(idx);
        // 세션처리
        return eventProduct.map(
                        ep -> {
                            ep.setTitle(eventProductRequest.getTitle());
                            ep.setImg(eventProductRequest.getImg());
                            ep.setStartTime(eventProductRequest.getStartTime());
                            ep.setEndTime(eventProductRequest.getEndTime());
                            return ep;
                        }).map(ep -> eventRepository.save(ep))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음")
                );

    }
    @Transactional
    public Header delete(Long idx) {
        Optional<EventProduct> eventProduct = eventRepository.findByIdx(idx);
        return eventProduct.map(ep -> {
            eventRepository.delete(ep);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

}
