package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Progress;
import com.supreme.shoekream.model.enumclass.Type;

import java.time.LocalDateTime;

//레코드: 데이터 클래스 - 순수하게 데이터를 보유하기 위한 특수종류 클래스

/**
 * Repository에서 나온 Entity를 값을 복사하거나
 * 사용자의 request를 Dto로 바꾼 후 이곳에서 Entity로 만들어 repository에 저장할때 사용
 * @param idx : PK
 * @param productDTO : productDTO객체
 * @param memberDTO : 구매내역을 만든 사용자 DTO객체
 * @param type : 구매입찰/ 즉시구매
 * @param price : 구매희망가/ 구매 가격
 * @param period : 입찰기간/ x
 * @param usePoint : 사용자가 사용한 포인트
 * @param cardInfo : 결재한 카드정보
 * @param receiver : 배송받을 사람
 * @param receiverHp : 배송받을 사람의 핸드폰번호
 * @param receiverAddress : 배송받을 사람의 주소
 * @param deliveryMemo : 배송메모
 * @param createdAt : 거래날짜
 * @param progress : 진행상황(채결됬을때 표시할 내용)
 * @param status : 상태(입찰중/진행중/종료)
 * @param sellIdx : 채결된 판매건 idx
 */
public record BuyDTO(
         Long idx,
         ProductDTO productDTO,
         MemberDTO memberDTO,
         Type type,
         Long price,
         int period,
         int usePoint,
         String cardInfo,
         String receiver,
         String receiverHp,
         String receiverAddress,
         String deliveryMemo,
         LocalDateTime createdAt,
         Progress progress,
         OrderStatus status,
         Long sellIdx
) {
    // 값 전달용
    public static BuyDTO of(Long idx, ProductDTO productDTO, MemberDTO memberDTO, Type type, Long price, int period,
                            int usePoint, String cardInfo, String receiver, String receiverHp,
                            String receiverAddress, String deliveryMemo, LocalDateTime createdAt,
                            Progress progress, OrderStatus status, Long sellIdx){
        return new BuyDTO(idx, productDTO, memberDTO, type, price, period, usePoint,cardInfo,receiver
        ,receiverHp,receiverAddress,deliveryMemo,createdAt,progress,status,sellIdx);
    }

    // create,update용 of
    public static BuyDTO of(ProductDTO productDTO, MemberDTO memberDTO, Type type, Long price, int period,
                            int usePoint, String cardInfo, String receiver, String receiverHp,
                            String receiverAddress, String deliveryMemo, LocalDateTime createdAt,
                            Progress progress, OrderStatus status, Long sellIdx){
        return new BuyDTO(null, productDTO, memberDTO, type, price, period, usePoint,cardInfo,receiver
                ,receiverHp,receiverAddress,deliveryMemo,createdAt,progress,status,sellIdx);
    }

    // from: 엔티티를 DTO로 만들어주는 과정
    public static BuyDTO fromEntity(Buy entity){
        if(entity.getSell() == null){
            return new BuyDTO(
                    entity.getIdx(),
                    ProductDTO.fromEntity(entity.getProduct()),
                    MemberDTO.fromEntity(entity.getMember()),
                    entity.getType(),
                    entity.getPrice(),
                    entity.getPeriod(),
                    entity.getUsePoint(),
                    entity.getCardInfo(),
                    entity.getReceiver(),
                    entity.getReceiverHp(),
                    entity.getReceiverAddress(),
                    entity.getDeliveryMemo(),
                    entity.getCreatedAt(),
                    entity.getProgress(),
                    entity.getStatus(),
                    null
            );
        }
        return new BuyDTO(
                entity.getIdx(),
                ProductDTO.fromEntity(entity.getProduct()),
                MemberDTO.fromEntity(entity.getMember()),
                entity.getType(),
                entity.getPrice(),
                entity.getPeriod(),
                entity.getUsePoint(),
                entity.getCardInfo(),
                entity.getReceiver(),
                entity.getReceiverHp(),
                entity.getReceiverAddress(),
                entity.getDeliveryMemo(),
                entity.getCreatedAt(),
                entity.getProgress(),
                entity.getStatus(),
                entity.getSell().getIdx()
        );
    }

    // create,update용 toEntity
    public Buy toEntity(Product product, Member member, Sell sell){
        return Buy.of(product,member,type,price,usePoint,period,cardInfo,receiver,
                receiverHp,receiverAddress,deliveryMemo,createdAt,progress,status,sell);
    }
}
