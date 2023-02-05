package com.supreme.admin.model.network.request;

import com.supreme.admin.model.dto.CardDTO;
import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.dto.PointDTO;
import com.supreme.admin.model.enumclass.PointType;

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
