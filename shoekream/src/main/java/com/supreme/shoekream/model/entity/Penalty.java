package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.enumclass.Reason;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Data               //getter,setter
@Builder             //method.method...가능하게 만들어주는
@ToString
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Enumerated(EnumType.STRING) private Reason reason;
    //    private Long memberIdx;
    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
    private Long productIdx;
    private LocalDateTime createdAt;
}
