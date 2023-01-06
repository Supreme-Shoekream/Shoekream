package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.enumclass.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberApiRequest {
    private String memberId;
    private String memberPw;
    private String name;
    private String hp;
    private String email;
    private Status status;
    private String shoeSize;
    private Long point;
    private String gender;
    private String birthDate;
    private String profileMemo;
    private String imgUrl;
}