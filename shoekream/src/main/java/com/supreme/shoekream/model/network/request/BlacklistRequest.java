package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.BlacklistDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

public record BlacklistRequest(
        Long idx,
        String reason
) {
    public BlacklistDTO toDTO(){
        return BlacklistDTO.of(
                idx,
                reason
        );
    }
}
