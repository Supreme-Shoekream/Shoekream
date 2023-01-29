package com.supreme.shoekream.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {
    private static final int BAR_LENGTH = 10;    // 표시할 길이

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {    // 현재 페이지 넘버를 받는다
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);    // 페이지기능은 0부터 시작이므로 startNumber를 0으로 줌
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);
        return IntStream.range(startNumber, endNumber).boxed().toList();  // 리스트안에 숫자를 저장 , boxed를 쓰면 래퍼클래스로 변경됨 int > integer로 변경됨 boxed를 사용했기 때문에 toList만 사용해도 됨
    }

    public int currentBarLength() {
        return BAR_LENGTH;
    }
}