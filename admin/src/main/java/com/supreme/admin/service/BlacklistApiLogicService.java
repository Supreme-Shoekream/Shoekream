package com.supreme.admin.service;

import com.supreme.admin.repository.BlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistApiLogicService {
    private final BlacklistRepository blacklistRepository;
}

