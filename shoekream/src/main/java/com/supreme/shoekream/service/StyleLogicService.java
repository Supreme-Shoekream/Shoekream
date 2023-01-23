package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.dto.socialDTO.LkDTO;
import com.supreme.shoekream.model.dto.socialDTO.ReplyDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Follow;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Reply;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.ReplyApiRequest;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class StyleLogicService {
    private final BoardRepository boardRepository;
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;
    private final ReplyRepository replyRepository;

    @Transactional(readOnly=true)
    public List<BoardDTO> list(){
//        System.out.println(boardRepository.findAll());
        return BoardDTO.fromEntity(boardRepository.findAll());
    }

    public Board read(Long idx){
        Board board = boardRepository.findByIdx(idx);
        return board;
    }

    public Header delete(Long idx){
        Optional<Board> board = boardRepository.findById(idx);
        return board.map(bd ->{
            boardRepository.delete(bd);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header comment_delete(Long replyIdx){
        Optional<Reply> reply = replyRepository.findById(replyIdx);
        return reply.map(rp -> {
            replyRepository.delete(rp);
            return
                     Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public List<Board> getFollowingFeeds(Long idx){
        List<Follow> followings = followRepository.findAllByfollowerIdx(idx);
        List<Board> feed;
        feed = new ArrayList<>();
        for(int i=0; i<followings.size(); i++){
            List<Board> sub = boardRepository.findAllByMemberIdx(followings.get(i).getFollowingIdx());
            for(int j=0; j<sub.size();j++){
                feed.add(sub.get(j));
            }
        }
        return feed;
    }

    public MemberDTO getMember(Long idx){
        MemberDTO sessionUser = MemberDTO.fromEntity(memberRepository.getReferenceById(5L));
        return sessionUser;
    }

    public List<MemberDTO> getLikeMembers(Long boardIdx){
        List<LkDTO> likes = LkDTO.fromEntity(likeRepository.findAllByBoardIdx(boardIdx));
        List<MemberDTO> members = new ArrayList<>();
        for (int i = 0; i < likes.size(); i++){
            members.add(likes.get(i).memberDTO());
        }
        return members;
    }

    public Header<ReplyDTO> createReply(ReplyDTO replyDTO){
//        System.out.println("============서비스============"+replyDTO);  // ✔
        Member member = memberRepository.getReferenceById(replyDTO.memberDTO().idx());
        Board board = boardRepository.getReferenceById(replyDTO.boardIdx());
        Reply newR = replyRepository.save(replyDTO.toEntity(member, board));
//        System.out.println("저장 완료!!!!!!!!!!!!!!!!!");
        ReplyDTO response = ReplyDTO.fromEntity(newR);
        return Header.OK(response);
    }

    public List<BoardDTO> findTopSevenStylePick(){
        List<Board> bds = boardRepository.findAll();
        List<BoardDTO> boards = BoardDTO.fromEntity(bds);
        for (int i=0;i<boards.size()-1;i++){
            for(int j=1;j<boards.size();j++){
                if(boards.get(i).lks().size() > boards.get(j).lks().size()){
                    BoardDTO temp = BoardDTO.of(
                            boards.get(i).idx(),
                            boards.get(i).memberDTO(),
                            boards.get(i).content(),
                            boards.get(i).hashtag(),
                            boards.get(i).img(),
                            boards.get(i).lks(),
                            boards.get(i).replies(),
                            boards.get(i).tags(),
                            boards.get(i).createdAt(),
                            boards.get(i).modifiedAt()
                            );
                    boards.set(i, boards.get(j));
                    boards.set(j, temp);
                }
            }
        }
        List<BoardDTO> response = new ArrayList<>();
        for(int i=0;i<7;i++){
            response.add(boards.get(i));
        }

        return response;
    }


}
