package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.socialDTO.BoardDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Follow;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.repository.BoardRepository;
import com.supreme.shoekream.repository.FollowRepository;
import com.supreme.shoekream.repository.LikeRepository;
import com.supreme.shoekream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Board> getFollowingFeeds(Long idx){
        List<Follow> followings = followRepository.findAllByfollowerIdx(5L);
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

    public Member getMember(Long idx){
        Member sessionUser = memberRepository.getOne(5L);
        return sessionUser;
    }

//    public List<Member> getLikeMembers(Long idx){
//
//    }

}
