package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.CardDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.Card;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.CardApiRequest;
import com.supreme.shoekream.model.network.response.CardApiResponse;
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
    public List<Card> list(Long memberIdx, boolean isBasic){
        List<Card> card = cardRepository.findByMemberIdxAndCardBasic(memberIdx, isBasic);
        return card;
    }

    @Transactional
    public Header<CardApiResponse> create(CardDTO dto){
        cardRepository.save(dto.toEntity(dto.memberDTO().toEntity()));
        return Header.OK();
    }

    @Transactional
    public void update(Long idx, MemberDTO memberDTO){
        List<Card> cards = cardRepository.findByMemberIdx(memberDTO.idx());
        cards.forEach(
                card -> {
                    card.setCardBasic(false);
                }
        );
        Card card = cardRepository.findByIdx(idx);
        card.setCardBasic(true);
    }

    @Transactional
    public void delete(Long idx){
        Card card = cardRepository.findByIdx(idx);
        cardRepository.delete(card);
    }
}
