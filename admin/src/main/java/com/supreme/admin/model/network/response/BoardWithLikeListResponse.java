package com.supreme.admin.model.network.response;

import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.dto.socialDTO.BoardDTO;
import com.supreme.admin.model.dto.socialDTO.LkDTO;
import com.supreme.admin.model.dto.socialDTO.ReplyDTO;
import com.supreme.admin.model.dto.socialDTO.TagDTO;
import com.supreme.admin.model.entity.Board;
import com.supreme.admin.model.entity.Lk;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record BoardWithLikeListResponse(
        Long idx,
        MemberDTO memberDTO,
        String content,
        String img,

        String hashtag,
        List<LkDTO> lks,
        List<ReplyDTO> replies,
        List<TagDTO> tags,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        boolean islike
) {
    public static BoardWithLikeListResponse of(Long idx,MemberDTO memberDTO,
                                               String content, String img, String hashtag, List<LkDTO> lks,
                                               List<ReplyDTO> replies, List<TagDTO> tags,
                                               LocalDateTime createdAt, LocalDateTime modifiedAt, boolean islike){
        return new BoardWithLikeListResponse(idx, memberDTO, content, img, hashtag, lks, replies, tags, createdAt, modifiedAt, islike);
    }

    public static BoardWithLikeListResponse fromEntity(Board board){
        return BoardWithLikeListResponse.of(
                board.getIdx(),
                MemberDTO.fromEntity(board.getMember()),
                board.getContent(),
                board.getImg(),
                board.getHashtag(),
                LkDTO.fromEntity(board.getLks()),
                ReplyDTO.fromEntity(board.getReplies()),
                TagDTO.fromEntity(board.getTags()),
                board.getCreatedAt(),
                board.getModifiedAt(),
                false
        );
    }

    public static List<BoardWithLikeListResponse> fromEntity(List<Board> bds){
        List<BoardWithLikeListResponse> boards = new ArrayList<>();
        for (int i=0;i<bds.size();i++){
            boards.add(BoardWithLikeListResponse.fromEntity(bds.get(i)));
        }
        return boards;
    }

}
