package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.EventMember;
import com.supreme.shoekream.model.entity.EventProduct;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public record EventMemberDTO(
        Long idx,
        Long eventProductIdx,

        Long memberIdx
) {
    public static EventMemberDTO fromEntity(EventMember entity){
        return new EventMemberDTO(
                entity.getIdx(),
                entity.getEventProduct().getIdx(),
                entity.getMember().getIdx()

        );
    }
    public static List<EventMemberDTO> fromEntity(List<EventMember> eventMembers){
        List<EventMemberDTO> eventMember = new ArrayList<>();
        if(eventMembers==null) return null;
        for(int i=0;i<eventMembers.size();i++){
            eventMember.add(EventMemberDTO.fromEntity(eventMembers.get(i)));
        }
        return eventMember;
    }

    public EventMember toEntity( EventProduct eventProduct,Member member){
        return EventMember.of(
                null,
                eventProduct,
                member
        );
    }
}