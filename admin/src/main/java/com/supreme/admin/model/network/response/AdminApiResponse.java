package com.supreme.admin.model.network.response;

import com.supreme.admin.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdminApiResponse {
    private Long idx;
    private String adminid;
    private String adminpw;
    private String name;
    private String hp;
    private LocalDateTime createdAt;
    private UserStatus status;
}
