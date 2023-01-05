package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.config.Auditable;
import com.supreme.shoekream.model.config.BaseEntity;
import com.supreme.shoekream.model.enumclass.Period;
import com.supreme.shoekream.model.enumclass.Progress;
import com.supreme.shoekream.model.enumclass.SellBuyStatus;
import com.supreme.shoekream.model.enumclass.Type;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity                                        // DB에 만들 테이블임!
@NoArgsConstructor                             // 매개변수 없는 기본 생성자 생성
@AllArgsConstructor                            // 모든 필드 값을 파라미터로 받는 생성자 생성
@Data                                          // getter,setter
@Builder                                       // method.method...가능하게 만들어주는
@ToString                    // 부모의 toString을 사용하기 위해
//@EqualsAndHashCode(callSuper = true)           // Generating equals/hashCode 에러 없애는 방법
//@EntityListeners(AuditingEntityListener.class) // 이벤트리스너(crud되기전후에 이벤트 발생)
public class Buy{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idx; // 번호
    private Long product_idx; // 구매 상품 번호
    private Long member_idx; // 구매 회원 번호
    @Enumerated(EnumType.STRING) private Type type; // 구매 방법(구매입찰/즉시구매)
    private Long price; // 구매 가격
    @Enumerated(EnumType.STRING) private Period period; // 입찰 마감기한(1일/3일/7일/30일/60일)
    private Long point; // 구매시 사용한 포인트
    private Long card_idx; // 카드 번호
    private LocalDateTime createdAt; // 구매 날짜
//    private Long sell_idx; // 체결 번호
    @Enumerated(EnumType.STRING) private Progress progress; // 진행 상황(발송완료/입고완료/검수합격/배송완료)
    @Enumerated(EnumType.STRING) private SellBuyStatus status; // 구매 상태(입찰중/기한만료/진행중/종료)
}
