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
public record BuyDto(
         Long id,
         Product product,
         Member member,
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
         Sell sell
) {
    //즉시 구매일 경우 of
    public static BuyDto of(Long id, Product product, Member member, Type type, Long price, int period,
                            int usePoint, String cardInfo, String receiver, String receiverHp,
                            String receiverAddress, String deliveryMemo, LocalDateTime createdAt,
                            Progress progress, OrderStatus status, Sell sell){
        return new BuyDto(id, product, member, type, price, period, usePoint,cardInfo,receiver
        ,receiverHp,receiverAddress,deliveryMemo,createdAt,progress,status,sell);
    }

    // 구매 입찰일 경우 of


    // from: 엔티티를 DTO로 만들어주는 과정
    public static BuyDto from(Buy entity){
        return new BuyDto(
                entity.getIdx(),
                entity.getProduct(),
                entity.getMember(),
                entity.getType(),
                entity.getPrice(),
                entity.getUsePoint(),
                entity.getPeriod(),
                entity.getCardInfo(),
                entity.getReceiver(),
                entity.getReceiverHp(),
                entity.getReceiverAddress(),
                entity.getDeliveryMemo(),
                entity.getCreatedAt(),
                entity.getProgress(),
                entity.getStatus(),
                entity.getSell()
        );
    }

    //toEntity 질문 필요

}
