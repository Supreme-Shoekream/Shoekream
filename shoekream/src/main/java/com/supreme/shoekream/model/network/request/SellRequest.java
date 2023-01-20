package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.dto.SellDTO;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.SellProgress;
import com.supreme.shoekream.model.enumclass.Type;

import java.time.LocalDateTime;

public record SellRequest(
        boolean isNow,
        Long price,
        int period,
        String cardInfo,
        String accountInfo,
        String sender,
        String senderHp,
        String senderAddress,
        String deliveryMemo
) {
    public static SellRequest of( boolean isNow,
                                  Long price,
                                  int period,
                                  String cardInfo,
                                  String accountInfo,
                                  String sender,
                                  String senderHp,
                                  String senderAddress,
                                  String deliveryMemo){
        return new SellRequest(isNow,price,period,cardInfo,accountInfo,
                sender,senderHp,senderAddress,deliveryMemo);
    }

    public SellDTO toDto(ProductDTO productDTO, MemberDTO memberDTO, BuyDTO buyDTO){
        Type type;
        OrderStatus status;
        if(this.isNow == true){
            //즉시판매시 진행중
            type = Type.SELL_NOW;
            status = OrderStatus.PROGRESSING;
        }else{
            type = Type.SELL_BID;
            status = OrderStatus.BIDDING;
        }
        return SellDTO.of(
                productDTO,
                memberDTO,
                type,
                price,
                period,
                cardInfo,
                accountInfo,
                sender,
                senderHp,
                senderAddress,
                deliveryMemo,
                LocalDateTime.now(),
                SellProgress.SHIPMENT_REQUEST,
                status,
                buyDTO
        );
    }
}
