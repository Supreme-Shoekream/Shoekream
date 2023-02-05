package com.supreme.admin.model.entity;


import com.supreme.admin.model.enumclass.Status;
import lombok.*;

import javax.persistence.*;


@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Data               //getter,setter
@Builder             //method.method...가능하게 만들어주는
@ToString(callSuper = true) //부모의 toString을 사용하기 위해
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String nickname;
    private String memberPw;        //로그인 PW
    private String name;
    private String hp;
    private String email;   //로그인ID
    @Enumerated(EnumType.ORDINAL) private Status status;
    private String shoeSize;
    private Long point;
    private String profileMemo;
    private String imgUrl;
    private String bank;
    private String accNumber;

    public Member(Long idx, String nickname, String memberPw, String name, String hp, String email, String shoeSize, String profileMemo, String imgUrl, String bank, String accNumber) {
        this.idx = idx;
        this.nickname = nickname;
        this.memberPw = memberPw;
        this.name = name;
        this.hp = hp;
        this.email = email;
        this.shoeSize = shoeSize;
        this.profileMemo = profileMemo;
        this.imgUrl = imgUrl;
        this.bank = bank;
        this.accNumber = accNumber;
    }

    public static Member of(Long idx, String nickname, String memberPw, String name, String hp, String email,
                            String shoeSize, String profileMemo, String imgUrl,String bank, String accNumber){
        return new Member(idx, nickname, memberPw, name, hp, email, shoeSize, profileMemo, imgUrl, bank, accNumber);
    }

    public static Member of(String memberPw, String name, String hp, String email,
                            String shoeSize){
        return new Member(null, null, memberPw, name, hp, email, shoeSize, null, null, null, null);
    }

}
