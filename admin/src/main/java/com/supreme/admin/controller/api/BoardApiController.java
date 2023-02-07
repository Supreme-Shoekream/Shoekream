package com.supreme.admin.controller.api;


import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.dto.socialDTO.BoardDTO;
import com.supreme.admin.model.network.response.BoardWithLikeListResponse;
import com.supreme.admin.repository.BoardRepository;
import com.supreme.admin.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social")
@RequiredArgsConstructor
public class BoardApiController {

    private final StyleLogicService styleLogicService;
    private final BoardRepository boardRepository;

    @GetMapping("{idx}")    // http://localhost:8899/api/social (post)
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
//    @GetMapping("/comments_more/getSessionUser")
//    public MemberDTO getSessionUser(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        MemberDTO sessionUser = kreamPrincipal.toFullDto();
//        return sessionUser;
//    }


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


//    @PostMapping("/comment_create")
//    public Header<ReplyDTO> create(@RequestBody Header<ReplyApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
////        System.out.println(request.getData());
//        ReplyApiRequest replyApiRequest = request.getData();
//        ReplyDTO replyDTO = replyApiRequest.toDTO(kreamPrincipal.toFullDto());
//        return styleLogicService.createReply(replyDTO);
//    }
//
//    @GetMapping("/trend")
//    public List<BoardWithLikeListResponse> trend(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        if(kreamPrincipal == null){
//            return styleLogicService.unlog_trend();
//        }
//        System.out.println("4444"+kreamPrincipal);
//        MemberDTO member = kreamPrincipal.toFullDto();
//        System.out.println("test"+member);
//        return styleLogicService.trendList(member);
//    }
//
//    @GetMapping("/newest")
//    public List<BoardWithLikeListResponse> newest(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        if(kreamPrincipal == null){
//            return styleLogicService.unlog_newest();
//        }
//        MemberDTO member = kreamPrincipal.toFullDto();
//        return styleLogicService.newest(member);
//    }
//
//    @GetMapping("/myprofile")
//    public MemberDTO myprofile(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        return kreamPrincipal.toFullDto();
//    }

    @GetMapping("/profile/{memberIdx}")
    public MemberDTO profile(@PathVariable(name = "memberIdx") Long memberIdx){
        return styleLogicService.getMember(memberIdx);
    }

//    @GetMapping("/profileCheck/{memberIdx}")
//    public ProfileLinkResponse profileCheck(@PathVariable(name = "memberIdx") Long memberIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        System.out.println("로그인"+kreamPrincipal.idx()+memberIdx+ (kreamPrincipal.idx().equals( memberIdx)) );
//        if(kreamPrincipal != null  && kreamPrincipal.idx().equals(memberIdx)){
//            return ProfileLinkResponse.of("/social/myprofile");
//        }else{
//            return ProfileLinkResponse.of("/social/profile/"+memberIdx);
//        }
//    }

    @GetMapping("/isBoardExist/{memberIdx}")
    public List<BoardWithLikeListResponse> isBoardExist(@PathVariable(name = "memberIdx") Long memberIdx){
        return styleLogicService.isBoardExist(memberIdx);
    }

//    @GetMapping("/like/{boardIdx}")
//    public void like(@PathVariable(name = "boardIdx") Long boardIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        styleLogicService.like(boardIdx, kreamPrincipal);
//    }
//
//    @GetMapping("/unlike/{boardIdx}")
//    public void unlike(@PathVariable(name = "boardIdx") Long boardIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        styleLogicService.unlike(boardIdx, kreamPrincipal);
//    }
//
//    @GetMapping("/hashtag/{hashtag}")
//    public List<BoardWithLikeListResponse> hashtag(@PathVariable(name = "hashtag") String hashtag, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        if(kreamPrincipal == null){
//            List<BoardWithLikeListResponse> feed = styleLogicService.getHashtagFeed_unlog(hashtag);
//            return feed;
//        }else{
//            List<BoardWithLikeListResponse> feed = styleLogicService.getHashtagFeed(hashtag, kreamPrincipal.toFullDto());
//            return feed;
//        }
//    }
//
//    @GetMapping("/follow/{memberIdx}")
//    public void follow(@PathVariable(name = "memberIdx") Long memberIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        styleLogicService.follow(memberIdx, kreamPrincipal.toFullDto());
//    }
//
//    @GetMapping("/unfollow/{memberIdx}")
//    public void unfollow(@PathVariable(name = "memberIdx") Long memberIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        styleLogicService.unfollow(memberIdx, kreamPrincipal.toFullDto());
//    }

//    @GetMapping("/products/mini/{productIdx}")
//    public List<BoardWithLikeListResponse> product_mini(@PathVariable(name = "productIdx") Long productIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        if(kreamPrincipal == null){
//            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx);
//            if(responses.size()<1){
//                return null;
//            }else{
//                return responses;
//            }
//        }else{
//            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx, kreamPrincipal.idx());
//            if(responses.size()<1){
//                return null;
//            }else{
//                return responses;
//            }
//        }
//    }
//
//    @GetMapping("/products/full/{productIdx}")
//    public List<BoardWithLikeListResponse> productFull(@PathVariable(name = "productIdx") Long productIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        if(kreamPrincipal == null){
//            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx);
//            return responses;
//
//        }else{
//            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx, kreamPrincipal.idx());
//            return responses;
//        }
//    }
}
