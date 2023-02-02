package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Buy;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.entity.Sell;
import com.supreme.shoekream.model.enumclass.OrderStatus;
import com.supreme.shoekream.model.enumclass.SellProgress;
import com.supreme.shoekream.model.enumclass.Type;

import java.time.LocalDateTime;

public record SellDTO(
        Long idx,
        ProductDTO productDTO,
        MemberDTO memberDTO,
        Type type,
        Long price,
        int period,
        String cardInfo,
        String accountInfo,
        String sender,
        String senderHp,
        String senderAddress,
        String deliveryMemo,
        LocalDateTime createdAt,
        SellProgress progress,
        OrderStatus status,
        Long buyIdx
) {
    public static SellDTO of(Long idx,ProductDTO productDTO, MemberDTO memberDTO, Type type, Long price,
                             int period, String cardInfo, String accountInfo,
                             String sender, String senderHp, String senderAddress,
                             String deliveryMemo, LocalDateTime createdAt,
                             SellProgress progress, OrderStatus status, Long buyIdx)
    {
        return new SellDTO(idx, productDTO, memberDTO, type, price, period, cardInfo, accountInfo,
                sender, senderHp, senderAddress, deliveryMemo, createdAt, progress, status, buyIdx);
    }
    public static SellDTO of(ProductDTO productDTO, MemberDTO memberDTO, Type type, Long price,
                             int period, String cardInfo, String accountInfo,
                             String sender, String senderHp, String senderAddress,
                             String deliveryMemo, LocalDateTime createdAt,
                             SellProgress progress, OrderStatus status, Long buyIdx)
    {
        return new SellDTO(null, productDTO,memberDTO, type, price,period,cardInfo,accountInfo,
                sender,senderHp,senderAddress, deliveryMemo,createdAt,progress,status,buyIdx);
    }

    public static SellDTO fromEntity(Sell entity){
        if(entity.getBuy() == null){
            return new SellDTO(
                    entity.getIdx(),
                    ProductDTO.fromEntity(entity.getProduct()),
                    MemberDTO.fromEntity(entity.getMember()),
                    entity.getType(),
                    entity.getPrice(),
                    entity.getPeriod(),
                    entity.getCardInfo(),
                    entity.getAccountInfo(),
                    entity.getSender(),
                    entity.getSenderHp(),
                    entity.getSenderAddress(),
                    entity.getDeliveryMemo(),
                    entity.getCreatedAt(),
                    entity.getProgress(),
                    entity.getStatus(),
                    null
            );
        }else{
            return new SellDTO(
                    entity.getIdx(),
                    ProductDTO.fromEntity(entity.getProduct()),
                    MemberDTO.fromEntity(entity.getMember()),
                    entity.getType(),
                    entity.getPrice(),
                    entity.getPeriod(),
                    entity.getCardInfo(),
                    entity.getAccountInfo(),
                    entity.getSender(),
                    entity.getSenderHp(),
                    entity.getSenderAddress(),
                    entity.getDeliveryMemo(),
                    entity.getCreatedAt(),
                    entity.getProgress(),
                    entity.getStatus(),
                    entity.getBuy().getIdx()
            );
        }

    }
    public Sell toEntity(Product product, Member member, Buy buy){
        return Sell.of(product,member,type,price,period,cardInfo,accountInfo
        ,sender,senderHp,senderAddress,deliveryMemo,createdAt,progress,status,buy);
    }
}
