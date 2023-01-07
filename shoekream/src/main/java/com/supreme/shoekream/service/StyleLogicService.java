package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class StyleLogicService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly=true)
    public List<Board> list(){
        System.out.println(boardRepository.findAll());
        return boardRepository.findAll();
    }

}
