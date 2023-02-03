package com.supreme.admin.model.network.request;

import com.supreme.admin.model.entity.Member;
import com.supreme.admin.model.entity.Product;
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
