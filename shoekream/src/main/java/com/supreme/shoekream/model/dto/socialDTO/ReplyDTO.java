package com.supreme.shoekream.model.dto.socialDTO;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Reply;

import java.util.ArrayList;
import java.util.List;

public record ReplyDTO(
//        Long idx,
        MemberDTO memberDTO,
        Long boardIdx,
        String content
) {
    public static ReplyDTO of(MemberDTO memberDTO, Long boardIdx, String content){
        return new ReplyDTO(memberDTO, boardIdx, content);
    }

    public static ReplyDTO fromEntity(Reply reply){
        return new ReplyDTO(
                MemberDTO.fromEntity(reply.getMember()),
                reply.getBoard().getIdx(),
                reply.getContent()
        );
    }

    public static List<ReplyDTO> fromEntity(List<Reply> rs){
        List<ReplyDTO> replies = new ArrayList<>();
        for(int i=0;i<rs.size();i++){
            replies.add(ReplyDTO.fromEntity(rs.get(i)));
        }
        return replies;
    }

}
