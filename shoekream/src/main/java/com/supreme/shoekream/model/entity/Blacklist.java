package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.enumclass.Reason;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@Data               //getter,setter //method.method...가능하게 만들어주는
@ToString
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String reason;
    //    private Long memberIdx;
    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
    private LocalDateTime createdAt;

    public Blacklist(Long idx, String reason, Member member) {
        this.idx = idx;
        this.reason = reason;
        this.member = member;
    }

    public static Blacklist of(Long idx, String reason, Member member){
        return new Blacklist(idx, reason, member);
    }
}
