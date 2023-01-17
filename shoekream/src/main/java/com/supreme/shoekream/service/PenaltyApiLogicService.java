package com.supreme.shoekream.service;

import com.supreme.shoekream.repository.PenaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PenaltyApiLogicService {
    private final PenaltyRepository penaltyRepository;
}
