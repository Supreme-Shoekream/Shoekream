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
    private String memberPw;
    private String name;
    private String hp;
    private String email;
    private String shoeSize;
    private String gender;
    private String birthDate;
}