package com.supreme.admin.model.entity;

import com.supreme.admin.model.enumclass.Reason;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity             //DB에 만들 테이블임!
@NoArgsConstructor  //매개변수 없는 기본 생성자 생성
@Data               //getter,setter
@Builder             //method.method...가능하게 만들어주는
@ToString
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private Reason reason;
    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product;
    private LocalDateTime createdAt;

    public Penalty(Long idx, Reason reason, Member member, Product product, LocalDateTime createdAt) {
        this.idx = idx;
        this.reason = reason;
        this.member = member;
        this.product = product;
        this.createdAt = createdAt;
    }

    public static Penalty of(Long idx, Reason reason, Member member, Product product, LocalDateTime createdAt){
        return new Penalty(idx, reason, member, product, createdAt);
    }
}
