package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.enumclass.Status;
import com.supreme.shoekream.model.network.security.KreamPrincipal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public record MemberDTO(
        Long idx,
        String nickname,
        String memberPw,
        String name,
        String hp,
        String email,
        @Enumerated(EnumType.ORDINAL) Status status,
        String shoeSize,
        Long point,
        String profileMemo,
        String imgUrl,
        String bank,
        String accNumber
) {
    public static MemberDTO of(Long idx, String nickname, String memberPw, String name,
                               String hp, String email,Status status, String shoeSize,Long point, String profileMemo, String imgUrl, String bank, String accNumber){
        return new MemberDTO(idx, nickname, memberPw, name, hp, email,status, shoeSize,point, profileMemo, imgUrl, bank, accNumber);
    }
    public static MemberDTO of(String nickname, String memberPw, String name,
                               String hp, String email,String shoeSize, String imgUrl){
        return new MemberDTO(null, nickname, memberPw, name, hp, email,Status.MEMBER, shoeSize,0L, null, imgUrl, null, null);
    }
    public static MemberDTO of(String memberPw, String name, String nickname,
                               String hp, String email, String shoeSize,
                               String bank, String accNumber,String imgUrl){
        return new MemberDTO(null, nickname, memberPw, name, hp, email,null, shoeSize,null, null, imgUrl, bank, accNumber);
    }

    public static MemberDTO fromEntity(Member member){
        return new MemberDTO(
                member.getIdx(),
                member.getNickname(),
                member.getMemberPw(),
                member.getName(),
                member.getHp(),
                member.getEmail(),
                member.getStatus(),
                member.getShoeSize(),
                member.getPoint(),
                member.getProfileMemo(),
                member.getImgUrl(),
                member.getBank(),
                member.getAccNumber()
        );
    }

    public Member toEntity(){
        String[] nickname = email.split("@");
        return Member.of(idx, nickname[0], "{noop}"+memberPw, name, hp,
                email, Status.MEMBER, shoeSize, point, profileMemo, imgUrl, bank, accNumber);
    }
}
