package com.supreme.admin.model.network.response;

import com.supreme.admin.model.dto.SellDTO;
import com.supreme.admin.model.enumclass.Progress;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * 한 사용자에 대해 마이페이지에서 판매내역을 눌렀을때 볼 수 있는 내용
 * 상세페이지도 사용가능
 * 관리자페이지에서 사용가능
 * @param deadline : 입찰이라면, 판매날짜+입찰기간
 */
public record SellResponse(
        Long idx,           // 번호
        Long productIdx,    // 판매 상품 번호
        String productImg,  // 제품 사진
        String productName, //
        String productSize,
        String memberEmail,
        String type,
        Long price,
        int period,
        String cardInfo,
        String accountInfo,
        String sender,
        String senderHp,
        String senderAddress,
        String deliveryMemo,
        LocalDateTime createdAt,
        String progress,
        String status,
        Long buyIdx,
        LocalDateTime deadline,
        String fees,
        String totalPrice
) {

    public static SellResponse from(SellDTO dto){
        String progress = "-";
        if(dto.progress() !=null ) progress = dto.progress().getTitle();
        LocalDateTime createdAt = dto.createdAt();
        int period = dto.period();
        LocalDateTime deadline = dto.createdAt();
        if(period == 0 ){
            deadline = createdAt;
        }else{
            deadline = createdAt.plusDays(period);
        }
        Long price = dto.price();
        DecimalFormat format = new DecimalFormat("###,###");
        String fees = format.format(Math.floor(price*0.015/100)*100);
        String totalPrice = format.format(price-Math.floor(price*0.015/100)*100);
        return new SellResponse(
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
                dto.accountInfo(),
                dto.sender(),
                dto.senderHp(),
                dto.senderAddress(),
                dto.deliveryMemo(),
                dto.createdAt(),
                progress,
                dto.status().getDescription(),
                dto.buyIdx(),
                deadline,
                fees,
                totalPrice
        );
    }
}
