package com.supreme.shoekream.service;

import com.supreme.shoekream.repository.BlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistApiLogicService {
    private final BlacklistRepository blacklistRepository;
}

