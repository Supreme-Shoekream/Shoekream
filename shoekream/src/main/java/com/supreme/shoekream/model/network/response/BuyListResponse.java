package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.entity.Buy;

import java.time.LocalDateTime;
public record BuyListResponse(
        Long idx,
        Long productIdx,
        String productImg,
        String productName,
        String productSize,
        String memberEmail,
        String type,
        Long price,
        int period,
        String cardInfo,
        String receiver,
        String receiverHp,
        String receiverAddress,
        String deliveryMemo,
        LocalDateTime createdAt,
        String progress
) {
    public static BuyListResponse of(Long idx, Long productIdx, String productImg, String productName,
                                 String productSize, String memberEmail, String type, Long price, int period, String cardInfo,
                                 String receiver, String receiverHp, String receiverAddress, String deliveryMemo,
                                 LocalDateTime createdAt, String progress){
        return new BuyListResponse(idx,productIdx,productImg,productName,productSize,memberEmail,
                type,price,period,cardInfo,receiver,receiverHp,receiverAddress,deliveryMemo
                ,createdAt,progress);
    }
    public static BuyListResponse from(Buy buy){

        return new BuyListResponse(
                buy.getIdx(),
                buy.getProduct().getIdx(),
                buy.getProduct().getImg(),
                buy.getProduct().getName(),
                buy.getProduct().getSize(),
                buy.getMember().getEmail(),
                buy.getType().getDescription(),
                buy.getPrice(),
                buy.getPeriod(),
                buy.getCardInfo(),
                buy.getReceiver(),
                buy.getReceiverHp(),
                buy.getReceiverAddress(),
                buy.getDeliveryMemo(),
                buy.getCreatedAt(),
                buy.getProgress().getTitle()
        );
    }
}
