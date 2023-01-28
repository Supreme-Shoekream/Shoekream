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
        Long productIdx,
        boolean isNow,
        Long price,
        int period,
        String cardInfo,
        String accountInfo,
        String sender,
        String senderHp,
        String senderAddress,
        String deliveryMemo,
        int progressNum
) {
    public static SellRequest of( Long productIdx, boolean isNow,   // 판매 상품 번호
                                  Long price,                       // 판매 가격
                                  int period,                       // 입찰 마감기한
                                  String cardInfo,                  // 카드 번호
                                  String accountInfo,               // 계좌 번호
                                  String sender,                    // 판매자
                                  String senderHp,                  // 판매자 핸드폰번호
                                  String senderAddress,             // 판매자 주소
                                  String deliveryMemo,              // 요청사항
                                  int progressNum){                 // 진행상황
        return new SellRequest(productIdx,isNow,price,period,cardInfo,accountInfo,
                sender,senderHp,senderAddress,deliveryMemo,progressNum);
    }

    public SellDTO toDto(ProductDTO productDTO, MemberDTO memberDTO, BuyDTO buyDTO){
        Type type;
        OrderStatus status;
        Long buyIdx = null;
        if(this.isNow == true){
            //즉시판매시 진행중
            type = Type.SELL_NOW;
            status = OrderStatus.PROGRESSING;
            buyIdx=buyDTO.idx();
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
                buyIdx
        );
    }
}
