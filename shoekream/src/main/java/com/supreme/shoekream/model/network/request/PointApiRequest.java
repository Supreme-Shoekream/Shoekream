package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.CardDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.PointDTO;
import com.supreme.shoekream.model.enumclass.PointType;

import java.time.LocalDateTime;

public record PointApiRequest(
        Long idx,
        Long point,
        PointType reason,
        LocalDateTime regDate
) {
    public static PointApiRequest of(
            Long idx,
            Long point,
            PointType reason,
            LocalDateTime regDate
    ){
        return new PointApiRequest(idx,point,reason,regDate);
    }
    public PointDTO toDTO(){
        return PointDTO.of(
                idx,
                point,
                reason,
                regDate
        );
    }
}
