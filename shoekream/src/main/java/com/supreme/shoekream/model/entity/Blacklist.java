package com.supreme.shoekream.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Data               //getter,setter
@Builder             //method.method...가능하게 만들어주는
@ToString
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String reason;
    private Long memberIdx;
    private LocalDateTime createdAt;

    public Blacklist(Long idx, String reason, Long memberIdx, LocalDateTime createdAt, String period) {
        this.idx = idx;
        this.reason = reason;
        this.memberIdx = memberIdx;
        this.createdAt = createdAt;
    }

//    public static Blacklist of(Long idx, String reason, Long memberIdx, LocalDateTime createdAt, String period){
//        return new Blacklist(idx, reason, memberIdx, createdAt, period);
//    }
}
