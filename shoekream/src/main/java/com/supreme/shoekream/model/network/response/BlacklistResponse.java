package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.BlacklistDTO;

import java.time.LocalDateTime;

public record BlacklistResponse(
        Long idx,
        String reason,
        Long memberIdx,
        LocalDateTime createdAt
) {
    public static BlacklistResponse of(Long idx, String reason, Long memberIdx, LocalDateTime createdAt){
        return new BlacklistResponse(idx, reason, memberIdx, createdAt);
    }
    public static BlacklistResponse from(BlacklistDTO dto){
//
//        LocalDateTime createdAt = dto.createdAt();
//        int period = dto.period();
//        LocalDateTime deadline = dto.createdAt();
//        if(period == 0 ){
//            deadline = createdAt;
//        }else{
//            deadline = createdAt.plusDays(period);
//        }
//        return new BlacklistResponse(
//                dto.idx(),
//                dto.blacklistDTO().idx(),
//                dto.blacklistDTO().reason(),
//                dto.blacklistDTO().memberIdx(),
//                dto.createdAt(),
//                deadline
//        );
//
//    }
}
