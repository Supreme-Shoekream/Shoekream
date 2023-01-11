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
@ToString                   // 부모의 toString을 사용하기 위해
//@EqualsAndHashCode(callSuper = true)           // Generating equals/hashCode 에러 없애는 방법
//@EntityListeners(AuditingEntityListener.class) // 이벤트리스너(crud되기전후에 이벤트 발생)
public class Sell {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idx; // 번호
    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product; // 판매 상품 번호
    //    private Long memberIdx; // 판매 회원 번호
    @ManyToOne
    @JoinColumn(name="member_idx")
    private Member member;
    private String type; // 판매 방법(판매입찰/즉시판매)
    private Long price; // 판매 가격
//    @Enumerated(EnumType.STRING) private Period period; // 입찰 마감기한(1일/3일/7일/30일/60일)
    private int period;
    private String cardInfo; // 패널티 결재 정보
    private String accountInfo; // 판매 정산 계좌 우리은행 *************
    private LocalDateTime createdAt; // 판매 날짜
//    private Long buy_idx; // 체결 번호
    private String sender;
    private String senderHp;
    private String senderAddress;
    private String deliveryMemo;

    private String progress; // 진행 상황(발송완료/입고완료/검수합격/검수불합격(거래실패)/정산완료/취소완료)
    private String status; // 구매 상태(입찰중/기한만료/진행중/종료)

    @OneToOne(mappedBy = "sell")
    private Buy buy;

}
