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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx; // 번호
    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product; // 구매 상품 번호 - 상품 + 사이즈
    // 상품의 설명이 바뀌더라도 같이 update될 필요 없어서 연관관계 X - 페이지에서 idx 얻어옴
    // 아!! buy에 입찰로 등록된것은 판매내역에서 가격을 뽑아올때 사용하기 때문에, 그페이지를 로드할땐, 구매테이블에서 입찰중상태인 가격을 불러온다.
    // 다시 객체로 변경 : 사이즈 선택화면으로 이동할때,

    @ManyToOne @JoinColumn(name="member_idx")
    private Member member; // 세션값을 저장할예정!

    @Enumerated(EnumType.STRING) private Type type; // 구매 방법(구매입찰/즉시구매)
    private Long price; // 구매 가격
//    @Enumerated(EnumType.STRING) private Period period; // 입찰 마감기한(1일/3일/7일/30일/60일) 실제 출력: 30일 - 23/02/07까지
    private int period; // 숫자로 저장하고, 받아야지 계산하기가 편함. 마감날짜 등록날짜 + period
    private Long usePoint; // 구매시 사용한 포인트
    private String cardInfo; // 결재한 카드 idx을 하지 않고, 선택된 결재방법중 할지 카드번호 BC **** **** **** 0736로 출력됨
    private LocalDateTime createdAt; // 구매 날짜 : now()로 설정 - 거래일시
    //    private Long sell_idx; // 체결 번호 -- 없애고, 즉시구매가 될때 따로 등록
    @Enumerated(EnumType.STRING) private Progress progress; // 진행 상황(발송완료/입고완료/검수합격/배송완료)
    @Enumerated(EnumType.STRING) private SellBuyStatus status; // 구매 상태(입찰중/기한만료/진행중/종료)
    //배송주소랑 배송요청사항 필요 - 같이 업테이트 되는 것은 아님 사용자가 입력한 값을 보낼것임 연관관계 필요없음
    private String receiver;
    private String receiver_hp;
    private String receiver_address;
    private String delivery_memo;

    @OneToOne @JoinColumn(name="sell_idx") private Sell sell; //판매 채결되면 등록 -- 같이 배송상태 update해주고싶어서

}
