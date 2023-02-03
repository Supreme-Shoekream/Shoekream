package com.supreme.admin.service;

import com.supreme.admin.repository.PenaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PenaltyApiLogicService {
    private final PenaltyRepository penaltyRepository;
}
