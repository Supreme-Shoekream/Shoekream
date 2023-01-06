package com.supreme.shoekream.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NoticeApiResponse {
    private Long idx;
    private String title;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
