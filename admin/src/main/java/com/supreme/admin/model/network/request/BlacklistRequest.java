package com.supreme.admin.model.network.request;

import com.supreme.admin.model.dto.BlacklistDTO;

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
