package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.CardDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Card;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.CardApiRequest;
import com.supreme.shoekream.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CardApiLogicService {
    private final CardRepository cardRepository;

    @Transactional
    public List<Card> list(Long memberIdx){
        List<Card> card = cardRepository.findByMemberIdx(memberIdx);
        return card;
    }

    @Transactional
    public Header<Card> create(Header<CardDTO> request){
        CardDTO dto = request.getData();
        cardRepository.save(dto.toEntity(dto.memberDTO().toEntity()));
        return Header.OK();
    }

    @Transactional
    public void update(CardDTO dto){
        try{
        Card card = cardRepository.findByIdx(dto.idx());
        if(dto.cardType() != null) {card.setCardType(dto.cardType());}
        if(dto.cardNumber() != null) {card.setCardNumber(dto.cardNumber());}
        if(dto.cardYy() != null) {card.setCardYy(dto.cardYy());}
        if(dto.cardMm() != null) {card.setCardMm(dto.cardMm());}
        if(dto.cardPw() != null) {card.setCardPw(dto.cardPw());}
        }catch (EntityNotFoundException e){
            System.out.println("제발;");
        }
    }

    @Transactional
    public void delete(Long idx){
        cardRepository.deleteByIdx(idx);
    }
}
