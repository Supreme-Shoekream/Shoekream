package com.supreme.shoekream.model.entity;

import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Progress;
import com.supreme.shoekream.model.enumclass.Type;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Table(indexes={
        @Index(columnList = "price"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "type"),
        @Index(columnList = "status"),
        @Index(columnList = "progress")
})
@Entity                                        // DB에 만들 테이블임! // getter,setter
@ToString(callSuper = true)                   // 부모의 toString을 사용하기 위해
public class Buy{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idx; // 번호
    @ManyToOne @JoinColumn(name="product_idx") private Product product;
    @ManyToOne @JoinColumn(name="member_idx") private Member member; // 세션값을 저장할예정!
    @Setter private Type type;      // 구매 방법(구매입찰2/즉시구매3)
    private Long price;     // 구매 가격
    private int period;     // 숫자로 저장하고, 받아야지 계산하기가 편함. 마감날짜 등록날짜 + period
    private int usePoint;   // 구매시 사용한 포인트
    private String cardInfo; // 결재한 카드 idx을 하지 않고, 선택된 결재방법중 할지 카드번호 BC **** **** **** 0736로 출력됨
    private String receiver;    //배송주소랑 배송요청사항 필요 - 같이 업테이트 되는 것은 아님 사용자가 입력한 값을 보낼것임 연관관계 필요없음
    private String receiverHp;
    private String receiverAddress;
    private String deliveryMemo;
    private LocalDateTime createdAt; // 구매 날짜 : now()로 설정 - 거래일시
    @Setter private Progress progress; // 진행 상황(발송완료/입고완료/검수합격/배송완료)
    @Setter private OrderStatus status; // 구매 상태(입찰중/기한만료/진행중/종료)
    @Setter @OneToOne @JoinColumn(name="sell_idx") private Sell sell; //판매 채결되면 등록 -- 같이 배송상태 update해주고싶어서

    //@NoArgsConstructor 빈 생성자
    protected Buy(){}

    //등록할때 필요한 정보들 - 내부는 private
    private Buy(Product product, Member member, Type type, Long price,
            int period, int usePoint, String cardInfo, String receiver,
                String receiverHp, String receiverAddress, String deliveryMemo,
                LocalDateTime createdAt, Progress progress, OrderStatus status, Sell sell){
        this.product=product;
        this.member=member;
        this.type=type;
        this.price=price;
        this.period=period;
        this.usePoint=usePoint;
        this.cardInfo=cardInfo;
        this.receiver=receiver;
        this.receiverHp=receiverHp;
        this.receiverAddress=receiverAddress;
        this.deliveryMemo=deliveryMemo;
        this.createdAt=createdAt;
        this.progress=progress;
        this.status=status;
        this.sell=sell;
    }

    //외부에서 사용할때 of로 사용하도록 // static 메서드:객체 생성없이 클래스를 통해 메서드를 직접 호출
    public static Buy of(Product product, Member member, Type type, Long price,
                         int period, int usePoint, String cardInfo, String receiver,
                         String receiverHp, String receiverAddress, String deliveryMemo,
                         LocalDateTime createdAt,  Progress progress, OrderStatus status, Sell sell){
        return new Buy(product,member,type,price,period,usePoint,cardInfo,receiver,
                receiverHp,receiverAddress,deliveryMemo, createdAt,progress,status,sell);
    }


}
