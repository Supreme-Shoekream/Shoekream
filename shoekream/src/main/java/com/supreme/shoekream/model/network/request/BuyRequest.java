package com.supreme.shoekream.model.network.request;


import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Type;

import java.time.LocalDateTime;

public record BuyRequest(
        boolean isNow,
        Long price,
        int period,
        int usePoint,
        String cardInfo,
        String receiver,
        String receiverHp,
        String receiverAddress,
        String deliveryMemo
) {
    public static BuyRequest of( boolean isNow, Long price, int period,
                                int usePoint, String cardInfo, String receiver, String receiverHp,
                                String receiverAddress, String deliveryMemo){
        return new BuyRequest( isNow, price, period, usePoint,cardInfo,receiver
                ,receiverHp,receiverAddress,deliveryMemo);
    }

    public BuyDTO toDto(ProductDTO productDTO, MemberDTO memberDTO, SellDTO sellDTO){
        Type type;
        OrderStatus status;
        if(this.isNow == true){
            // 즉시구매시 진행중
            type = Type.BUY_NOW;
            status = OrderStatus.PROGRESSING;
        }else{
            // 구매입찰시 입찰
            type = Type.BUY_BID;
            status = OrderStatus.BIDDING;
        }
        return BuyDTO.of(
                productDTO,
                memberDTO,
                type,
                price,
                period,
                usePoint,
                cardInfo,
                receiver,
                receiverHp,
                receiverAddress,
                deliveryMemo,
                LocalDateTime.now(),
                null,   // 판매자가 발송하기 전까지 null
                status,
                sellDTO
        );
    }

}
