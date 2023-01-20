package com.supreme.shoekream.model.dto.socialDTO;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Reply;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@RequiredArgsConstructor
public record ReplyDTO(
        Long idx,
        MemberDTO memberDTO,
        Long boardIdx,
        String content,
        LocalDateTime createdAt
) {

    public static ReplyDTO of(MemberDTO memberDTO, Long boardIdx, String content, LocalDateTime createdAt){
        return new ReplyDTO(null, memberDTO, boardIdx, content, createdAt);
    }

    public static ReplyDTO fromEntity(Reply reply){
        return new ReplyDTO(
                reply.getIdx(),
                MemberDTO.fromEntity(reply.getMember()),
                reply.getBoard().getIdx(),
                reply.getContent(),
                reply.getCreatedAt()
        );
    }

    public static List<ReplyDTO> fromEntity(List<Reply> rs){
        List<ReplyDTO> replies = new ArrayList<>();
        for(int i=0;i<rs.size();i++){
            replies.add(ReplyDTO.fromEntity(rs.get(i)));
        }
        return replies;
    }

    public Reply toEntity(Member member, Board board){
        return Reply.of(
//                idx,
                member,
                content,
                board,
                createdAt
        );
    }
}
