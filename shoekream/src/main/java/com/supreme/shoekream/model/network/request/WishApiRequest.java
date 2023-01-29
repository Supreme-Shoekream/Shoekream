package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WishApiRequest {
    private Long Idx;
    private Product proIdx;
    private Member memIdx;
}
