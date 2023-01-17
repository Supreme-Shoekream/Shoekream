package com.supreme.shoekream.model.dto.socialDTO;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Board;
import com.supreme.shoekream.model.entity.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record BoardDTO(
        Long idx,
        MemberDTO memberDTO,
        String content,
        String hashtag,
        String img,

        List<LkDTO> lks,
        List<ReplyDTO> replies,
        List<TagDTO> tags,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static BoardDTO of(Long idx, MemberDTO memberDTO, String content, String hashtag,
                              String img, List<LkDTO> likes, List<ReplyDTO> replies, List<TagDTO> tags,
                              LocalDateTime createdAt, LocalDateTime modifiedAt){
        return new BoardDTO(idx, memberDTO, content, hashtag, img, likes, replies,tags, createdAt, modifiedAt);
    }

    public static BoardDTO fromEntity(Board board){
        return new BoardDTO(
                board.getIdx(),
                MemberDTO.fromEntity(board.getMember()),
                board.getContent(),
                board.getHashtag(),
                board.getImg(),
                LkDTO.fromEntity(board.getLks()),
                ReplyDTO.fromEntity(board.getReplies()),
                TagDTO.fromEntity(board.getTags()),
                board.getCreatedAt(),
                board.getModifiedAt()
        );
    }

    public static List<BoardDTO> fromEntity(List<Board> bds){
        List<BoardDTO> boards = new ArrayList<>();
        for(int i=0;i<bds.size();i++){
            boards.add(BoardDTO.fromEntity(bds.get(i)));
        }
        return boards;
    }

}
