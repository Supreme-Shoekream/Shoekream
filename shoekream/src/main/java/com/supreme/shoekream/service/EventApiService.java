package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.EventDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Address;
import com.supreme.shoekream.model.entity.EventProduct;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.repository.AddressRepository;
import com.supreme.shoekream.repository.EventRepository;
import com.supreme.shoekream.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class EventApiService {
    final EventRepository eventRepository;
    @Transactional
    public List<EventDTO> list(Long idx){
        return null;

    }

    @Transactional
    public Header<EventProduct> create(Header<EventDTO> request){
        return null;

    }

    @Transactional
    public Header<EventProduct> update(Header<EventProduct> request) {
        return null;

    }
    @Transactional
    public Header delete(Long idx) {
        return null;

    }

}
