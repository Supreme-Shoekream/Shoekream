package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.enumclass.Reason;
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
    @JoinColumn(name="sell_idx")
    private Sell sell;
    private LocalDateTime createdAt;

    public Penalty(Long idx, Reason reason,  Sell sell, LocalDateTime createdAt) {
        this.idx = idx;
        this.reason = reason;
        this.sell = sell;
        this.createdAt = createdAt;
    }

    public static Penalty of(Long idx, Reason reason, Sell sell, LocalDateTime createdAt){
        return new Penalty(idx, reason,  sell, createdAt);
    }
    public static Penalty of( Reason reason, Sell sell, LocalDateTime createdAt){
        return  Penalty.of(null, reason,  sell, createdAt);
    }
}
