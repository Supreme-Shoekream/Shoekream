package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Card;
import com.supreme.shoekream.model.entity.Member;

import java.time.LocalDateTime;

public record CardDTO(
        Long idx,
        String cardType,
        String cardNumber,
        MemberDTO memberDTO,
        String cardMm,
        String cardYy,
        String cardPw,
        String birthDate
) {
    public static CardDTO of(Long idx,
               String cardType,
               String cardNumber,
               MemberDTO memberDTO,
               String cardMm,
               String cardYy,
               String cardPw,
               String birthDate){
        return new CardDTO(idx,cardType,cardNumber,memberDTO
        ,cardMm,cardYy,cardPw,birthDate);
    }

    public static CardDTO from(Card entity){
        return new CardDTO(
                entity.getIdx(),
                entity.getCardNumber(),
                entity.getCardType(),
                MemberDTO.fromEntity(entity.getMember()),
                entity.getCardMm(),
                entity.getCardYy(),
                entity.getCardPw(),
                entity.getBirthDate()
        );
    }

    public Card toEntity(Member member){
        return Card.of(
                member,
                cardType,
                cardNumber,
                cardMm,
                cardYy,
                cardPw,
                birthDate);
    }
}
