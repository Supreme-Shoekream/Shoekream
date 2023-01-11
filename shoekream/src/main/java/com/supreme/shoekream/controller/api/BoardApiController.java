package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.repository.BoardRepository;
import com.supreme.shoekream.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/social")
@RequiredArgsConstructor
public class BoardApiController {

    private final StyleLogicService styleLogicService;
    private final BoardRepository boardRepository;

    @GetMapping("{idx}")    // http://localhost:8889/api/social (get)
    public Board read(@PathVariable(name="idx") Long idx){
        return styleLogicService.read(idx);
    }


    @DeleteMapping("{idx}")
    public void delete(@PathVariable(name="idx") Long idx){
         styleLogicService.delete(idx);
    }
}
