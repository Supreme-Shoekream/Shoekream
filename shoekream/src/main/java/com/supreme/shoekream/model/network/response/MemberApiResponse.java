package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.enumclass.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberApiResponse {
    private Long idx;
    private String nickname;
    private String memberPw;
    private String name;
    private String hp;
    private String email;
    private Status status;
    private String shoeSize;
    private Long point;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String profileMemo;
    private String imgUrl;
    private String bank;
    private String accountNumber;
}