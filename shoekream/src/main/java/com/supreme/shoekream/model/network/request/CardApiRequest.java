package com.supreme.shoekream.model.network.request;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.CardDTO;
import com.supreme.shoekream.model.dto.MemberDTO;

public record CardApiRequest(
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
    public static CardApiRequest of(
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
        return new CardApiRequest(idx,cardType,cardNumber,memberDTO,cardMm,cardYy,cardPw,birthDate, cardBasic);
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
