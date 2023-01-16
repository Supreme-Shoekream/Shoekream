package com.supreme.shoekream.model.network.response;


import com.supreme.shoekream.model.dto.BuyDTO;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.Progress;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

/**
 * 한 사용자에 대해 마이페이지에서 판매내역을 눌렀을때 볼 수 있는 내용
 * 상세페이지도 사용가능
 * 관리자페이지에서 사용가능
 * @param deadline : 입찰이라면, 구매날짜 + 입찰기간
 */
public record BuyResponse(
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
    String progress,
    String status,
    Long sellIdx,
    LocalDateTime deadline
) {
    public static BuyResponse of(Long idx, Long productIdx, String productImg, String productName,
                                 String productSize, String memberEmail, String type, Long price, int period, String cardInfo,
                                 String receiver, String receiverHp, String receiverAddress, String deliveryMemo,
                                 LocalDateTime createdAt, String progress, String status,
                                 Long sellIdx,LocalDateTime deadline){
        return new BuyResponse(idx,productIdx,productImg,productName,productSize,memberEmail,
        type,price,period,cardInfo,receiver,receiverHp,receiverAddress,deliveryMemo
        ,createdAt,progress,status,sellIdx,deadline);
    }
    public static BuyResponse from(BuyDTO dto){
        LocalDateTime createdAt = dto.createdAt();
        int period = dto.period();
        LocalDateTime deadline = null;
        if(period == 0 ){
            deadline = createdAt;
        }else{
            deadline = createdAt.plusDays(period);
        }
        return new BuyResponse(
                dto.idx(),
                dto.productDTO().idx(),
                dto.productDTO().img(),
                dto.productDTO().name(),
                dto.productDTO().size(),
                dto.memberDTO().email(),
                dto.type().getDescription(),
                dto.price(),
                dto.period(),
                dto.cardInfo(),
                dto.receiver(),
                dto.receiverHp(),
                dto.receiverAddress(),
                dto.deliveryMemo(),
                dto.createdAt(),
                dto.progress().getTitle(),
                dto.status().getDescription(),
                dto.sellDTO().idx(),
                deadline
        );
    }
}
