package com.supreme.shoekream.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Progress;
import com.supreme.shoekream.model.enumclass.SellProgress;
import com.supreme.shoekream.model.enumclass.Type;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(indexes={
        @Index(columnList = "price"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "type"),
        @Index(columnList = "status"),
        @Index(columnList = "progress")
})
@Entity                                        // DB에 만들 테이블임! // getter,setter
@ToString(callSuper = true)                 // 부모의 toString을 사용하기 위해
@EqualsAndHashCode
public class Sell {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idx; // 번호
    @ManyToOne @JoinColumn(name="product_idx") private Product product; // 판매 상품 번호
    @ManyToOne @JoinColumn(name="member_idx") private Member member;
    @Setter private Type type;   // 판매 방법(판매입찰/즉시판매)
    private Long price; // 판매 가격
    private int period;
    private String cardInfo; // 패널티 결재 정보
    private String accountInfo; // 판매 정산 계좌 우리은행 *************

    private String sender;
    private String senderHp;
    private String senderAddress;
    private String deliveryMemo;
    private LocalDateTime createdAt; // 판매 날짜
    @Setter private SellProgress progress; // 진행 상황(발송완료/입고완료/검수합격/검수불합격(거래실패)/정산완료/취소완료)
    @Setter private OrderStatus status; // 구매 상태(입찰중/기한만료/진행중/종료)

    @Setter @OneToOne(mappedBy = "sell") private Buy buy;


    protected Sell(){}

    public Sell(Product product, Member member, Type type, Long price,
                       int period, String cardInfo, String accountInfo,
                       String sender, String senderHp, String senderAddress,
                       String deliveryMemo, LocalDateTime createdAt,
                SellProgress progress, OrderStatus status, Buy buy){
        this.product=product;
        this.member=member;
        this.type=type;
        this.price=price;
        this.period=period;
        this.cardInfo=cardInfo;
        this.accountInfo=accountInfo;
        this.sender=sender;
        this.senderHp=senderHp;
        this.senderAddress=senderAddress;
        this.deliveryMemo=deliveryMemo;
        this.createdAt=createdAt;
        this.progress=progress;
        this.status=status;
        this.buy=buy;
    }

    public static Sell of(Product product, Member member, Type type, Long price,
                          int period, String cardInfo, String accountInfo,
                          String sender, String senderHp, String senderAddress,
                          String deliveryMemo, LocalDateTime createdAt,
                          SellProgress progress, OrderStatus status, Buy buy){
        return new Sell(product,member,type,price,period,cardInfo,accountInfo,sender,senderHp
        ,senderAddress,deliveryMemo,createdAt,progress,status,buy);
    }
}
