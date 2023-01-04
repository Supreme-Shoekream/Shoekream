package com.supreme.shoekream.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdminApiRequest {
    //입력되는 것만 적어준다.(ex)회원가입
    private String adminid;
    private String adminpw;
    private String name;
    private String hp;
}
