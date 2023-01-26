package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.dto.socialDTO.ReplyDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ReplyApiRequest;
import com.supreme.shoekream.model.network.response.BoardWithLikeListResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.BoardRepository;
import com.supreme.shoekream.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social")
@RequiredArgsConstructor
public class BoardApiController {

    private final StyleLogicService styleLogicService;
    private final BoardRepository boardRepository;

    @GetMapping("{idx}")    // http://localhost:8889/api/social (post)
    public BoardDTO read(@PathVariable(name="idx") Long idx){
        return BoardDTO.fromEntity(styleLogicService.read(idx));
    }

    @GetMapping("/comments_more/{idx}")
    public BoardDTO pop(@PathVariable(name="idx") Long idx){
        BoardDTO board = BoardDTO.fromEntity(styleLogicService.read(idx));
//        List<ReplyDTO> replies = board.replies();
        return board;
    }

//    @GetMapping("/comments_more/getSessionUser/{idx}")
    @GetMapping("/comments_more/getSessionUser")
    public MemberDTO getSessionUser(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        MemberDTO sessionUser = kreamPrincipal.toFullDto();
        return sessionUser;
    }


    @GetMapping("/likes/{idx}")
    public List<MemberDTO> likes(@PathVariable(name="idx") Long BoardIdx){
        return styleLogicService.getLikeMembers(BoardIdx);
    }

    @DeleteMapping("{idx}")
    public void delete(@PathVariable(name="idx") Long idx){
        styleLogicService.delete(idx);
    }

    @DeleteMapping("/comment_delete/{replyIdx}")
    public void comment_delete(@PathVariable(name = "replyIdx") Long replyIdx){
        styleLogicService.comment_delete(replyIdx);
    }


    @PostMapping("/comment_create")
    public Header<ReplyDTO> create(@RequestBody Header<ReplyApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        System.out.println(request.getData());
        ReplyApiRequest replyApiRequest = request.getData();
        ReplyDTO replyDTO = replyApiRequest.toDTO(kreamPrincipal.toFullDto());
        return styleLogicService.createReply(replyDTO);
    }

    @GetMapping("/trend")
    public List<BoardWithLikeListResponse> trend(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        MemberDTO member = kreamPrincipal.toFullDto();
        return styleLogicService.trendList(member);
    }

    @GetMapping("/newest")
    public List<BoardDTO> newest(){
        return styleLogicService.newest();
    }

    @GetMapping("/myprofile")
    public MemberDTO myprofile(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return kreamPrincipal.toFullDto();
    }

    @GetMapping("/isBoardExist/{memberIdx}")
    public List<BoardDTO> isBoardExist(@PathVariable(name = "memberIdx") Long memberIdx){
        return styleLogicService.isBoardExist(memberIdx);
    }

    @GetMapping("/like/{boardIdx}")
    public void like(@PathVariable(name = "boardIdx") Long boardIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        styleLogicService.like(boardIdx, kreamPrincipal);
    }

    @GetMapping("/unlike/{boardIdx}")
    public void unlike(@PathVariable(name = "boardIdx") Long boardIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        styleLogicService.unlike(boardIdx, kreamPrincipal);
    }
}
