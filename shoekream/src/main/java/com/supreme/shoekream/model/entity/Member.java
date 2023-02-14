package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import com.supreme.shoekream.model.enumclass.Status;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
//@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
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


    public Member(Long idx, String nickname, String memberPw, String name, String hp, String email, Status status,String shoeSize, Long point, String profileMemo, String imgUrl, String bank, String accNumber) {
        this.idx = idx;
        this.nickname = nickname;
        this.memberPw = memberPw;
        this.name = name;
        this.hp = hp;
        this.email = email;
        this.status=status;
        this.shoeSize = shoeSize;
        this.point = point;
        this.profileMemo = profileMemo;
        this.imgUrl = imgUrl;
        this.bank = bank;
        this.accNumber = accNumber;
    }

    public static Member of(Long idx, String nickname, String memberPw, String name, String hp, String email, Status status,
                            String shoeSize, Long point, String profileMemo, String imgUrl,String bank, String accNumber){
        return new Member(idx, nickname, memberPw, name, hp, email, status, shoeSize, point, profileMemo, imgUrl, bank, accNumber);
    }

    public static Member of(String memberPw, String name, String hp, String email, Status status,
                            String shoeSize){
        return new Member(null, null, memberPw, name, hp, email, status, shoeSize,null, null, null, null, null);
    }
    public static Member kakaoof(String nickname, String memberPw, String name, String hp, String email, Status status,
                            String shoeSize, String imgUrl){
        return new Member(null, nickname, memberPw, name, hp, email, status, shoeSize, null, null, imgUrl, null, null);
    }
}
