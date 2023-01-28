package com.supreme.shoekream.model.network.response;

import com.supreme.shoekream.model.dto.CardDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

public record CardApiResponse(
        Long idx,
        String cardType,
        String cardNumber,
        MemberDTO memberDTO,
        String cardMm,
        String cardYy,
        String cardPw,
        String birthDate,
        boolean cardBasic
) {
    public static CardApiResponse of(
            Long idx,
            String cardType,
            String cardNumber,
            MemberDTO memberDTO,
            String cardMm,
            String cardYy,
            String cardPw,
            String birthDate,
            boolean cardBasic
    ){
        return new CardApiResponse(idx,cardType,cardNumber,memberDTO,cardMm,cardYy,cardPw,birthDate,cardBasic);
    }
    public CardDTO toDTO(MemberDTO memberDTO){
        return CardDTO.of(
                idx,
                cardType,
                cardNumber,
                memberDTO,
                cardMm,
                cardYy,
                cardPw,
                birthDate,
                cardBasic
        );
    }
}
