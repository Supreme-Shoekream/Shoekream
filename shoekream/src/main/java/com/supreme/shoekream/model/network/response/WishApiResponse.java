package com.supreme.shoekream.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WishApiResponse {
    private Long idx;
    private Long proIdx;
    private Long memIdx;
}
