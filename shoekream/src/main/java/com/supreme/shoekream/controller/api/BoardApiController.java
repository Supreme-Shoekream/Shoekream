package com.supreme.shoekream.controller.api;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.dto.socialDTO.ReplyDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.BoardStyleApiRequest;
import com.supreme.shoekream.model.network.request.ReplyApiRequest;
import com.supreme.shoekream.model.network.response.BoardStyleApiResponse;
import com.supreme.shoekream.model.network.response.BoardWithLikeListResponse;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.model.network.response.ProfileLinkResponse;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.BoardRepository;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.service.MemberApiLogicService;
import com.supreme.shoekream.service.StyleLogicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/social")
@RequiredArgsConstructor
public class BoardApiController {

    private final StyleLogicService styleLogicService;
    private final BoardRepository boardRepository;

    @GetMapping("{idx}")    // http://localhost:8889/api/social
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
//        MemberDTO sessionUser = kreamPrincipal.toFullDto();
        MemberDTO sessionUser = MemberDTO.fromEntity(memberRepository.getReferenceById(kreamPrincipal.idx()));
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
    public Header<ReplyDTO> comment_create(@RequestBody Header<ReplyApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
//        System.out.println(request.getData());
        ReplyApiRequest replyApiRequest = request.getData();
        ReplyDTO replyDTO = replyApiRequest.toDTO(kreamPrincipal.toFullDto());
        return styleLogicService.createReply(replyDTO);
    }

    @GetMapping("/trend")
    public Page<BoardWithLikeListResponse> trend(@AuthenticationPrincipal KreamPrincipal kreamPrincipal, @PageableDefault(size = 12)Pageable pageable){
        if(kreamPrincipal == null){
            return styleLogicService.unlog_trend(pageable);
        }
        MemberDTO member = kreamPrincipal.toFullDto();
        return styleLogicService.trendList(member, pageable);
    }

    @GetMapping("/newest")
    public Page<BoardWithLikeListResponse> newest(@AuthenticationPrincipal KreamPrincipal kreamPrincipal, @PageableDefault(size = 12)Pageable pageable){
        if(kreamPrincipal == null){
            return styleLogicService.unlog_newest(pageable);
        }
        MemberDTO member = kreamPrincipal.toFullDto();
        return styleLogicService.newest(member, pageable);
    }

    @GetMapping("/myprofile")
    public MemberDTO myprofile(@AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return MemberDTO.fromEntity(memberRepository.getReferenceById(kreamPrincipal.idx()));
    }

    @GetMapping("/profile/{memberIdx}")
    public MemberDTO profile(@PathVariable(name = "memberIdx") Long memberIdx){
        return styleLogicService.getMember(memberIdx);
    }

    @GetMapping("/profileCheck/{memberIdx}")
    public ProfileLinkResponse profileCheck(@PathVariable(name = "memberIdx") Long memberIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        System.out.println("로그인"+kreamPrincipal.idx()+memberIdx+ (kreamPrincipal.idx().equals( memberIdx)) );
        if(kreamPrincipal != null  && kreamPrincipal.idx().equals(memberIdx)){
            return ProfileLinkResponse.of("/social/myprofile");
        }else{
            return ProfileLinkResponse.of("/social/profile/"+memberIdx);
        }
    }

    @GetMapping("/isBoardExist/{memberIdx}")
    public List<BoardWithLikeListResponse> isBoardExist(@PathVariable(name = "memberIdx") Long memberIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        return styleLogicService.isBoardExist(memberIdx, kreamPrincipal.idx());
    }

    @GetMapping("/like/{boardIdx}")
    public void like(@PathVariable(name = "boardIdx") Long boardIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        styleLogicService.like(boardIdx, kreamPrincipal);
    }

    @GetMapping("/unlike/{boardIdx}")
    public void unlike(@PathVariable(name = "boardIdx") Long boardIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        styleLogicService.unlike(boardIdx, kreamPrincipal);
    }

    @GetMapping("/hashtag/{hashtag}")
    public List<BoardWithLikeListResponse> hashtag(@PathVariable(name = "hashtag") String hashtag, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            List<BoardWithLikeListResponse> feed = styleLogicService.getHashtagFeed_unlog(hashtag);
            return feed;
        }else{
            List<BoardWithLikeListResponse> feed = styleLogicService.getHashtagFeed(hashtag, kreamPrincipal.toFullDto());
            return feed;
        }
    }

    @GetMapping("/follow/{memberIdx}")
    public void follow(@PathVariable(name = "memberIdx") Long memberIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        styleLogicService.follow(memberIdx, kreamPrincipal.toFullDto());
    }

    @GetMapping("/unfollow/{memberIdx}")
    public void unfollow(@PathVariable(name = "memberIdx") Long memberIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        styleLogicService.unfollow(memberIdx, kreamPrincipal.toFullDto());
    }

    @GetMapping("/products/mini/{productIdx}")
    public List<BoardWithLikeListResponse> product_mini(@PathVariable(name = "productIdx") Long productIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx);
            if(responses.size()<1){
                return null;
            }else{
                return responses;
            }
        }else{
            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx, kreamPrincipal.idx());
            if(responses.size()<1){
                return null;
            }else{
                return responses;
            }
        }
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MemberRepository memberRepository;

    // 첨부 파일 업로드(생성)
    @PostMapping("/imgUpload") // http://localhost:8889/api/social/imgUpload
    public String uploadAjaxActionPOST(@RequestParam(value = "imgUpload", required = false) MultipartFile uploadFile) {
        logger.info("⚠️uploadAjaxActionPOST..........");
        logger.info("⚠️파일 이름 : " + uploadFile.getOriginalFilename());
        logger.info("⚠️파일 타입 : " + uploadFile.getContentType());
        logger.info("⚠️파일 크기 : " + uploadFile.getSize());
        // 파일 저장 폴더 경로
        String uploadFilePath = "/Users/oyun-yeong/img"; // 로컬주소 -> img폴더 생성한것
        // 폴더 생성
        File uploadPath = new File(uploadFilePath);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String uploadFileName = uploadFile.getOriginalFilename(); // 파일 이름
        File saveFile = new File(uploadFilePath, uploadFileName); // 파일 위치, 파일 이름을 합친 File 객체
        try { // 파일 저장
            uploadFile.transferTo(saveFile);
            logger.info("🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢");
            logger.info("이미지 파일 저장 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadFileName;
    }

    @PostMapping("/boardcreate") // http://localhost:8889/api/social/boardcreate
    public BoardStyleApiResponse board_create(@RequestBody Header<BoardStyleApiRequest> request, @AuthenticationPrincipal KreamPrincipal kreamPrincipal) {
        return styleLogicService.create(request, kreamPrincipal.toFullDto());
    }

    @GetMapping("/products/full/{productIdx}")
    public List<BoardWithLikeListResponse> productFull(@PathVariable(name = "productIdx") Long productIdx, @AuthenticationPrincipal KreamPrincipal kreamPrincipal){
        if(kreamPrincipal == null){
            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx);
            return responses;

        }else{
            List<BoardWithLikeListResponse> responses = styleLogicService.getProductBoards(productIdx, kreamPrincipal.idx());
            return responses;
        }

    }


}
