package com.supreme.shoekream.model.dto.socialDTO;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Lk;

import java.util.ArrayList;
import java.util.List;

public record LkDTO(
        MemberDTO memberDTO,
        Long boardIdx
) {
    public static LkDTO of(MemberDTO memberDTO, Long boardIdx){
        return new LkDTO(memberDTO, boardIdx);
    }

    public static LkDTO fromEntity(Lk lk) {
        return new LkDTO(
                MemberDTO.fromEntity(lk.getMember()),
                lk.getBoard().getIdx()
                );
    }

    public static List<LkDTO> fromEntity(List<Lk> lks){
        List<LkDTO> likes = new ArrayList<>();
        for(int i=0;i<lks.size();i++){
            likes.add(LkDTO.fromEntity(lks.get(i)));
        }
        return likes;
    }
}
