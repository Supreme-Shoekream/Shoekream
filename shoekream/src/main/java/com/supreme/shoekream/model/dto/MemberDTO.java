package com.supreme.shoekream.model.dto;

import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.enumclass.Status;

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
        String accountNumber
) {
    public static MemberDTO of(Long idx, String nickname, String memberPw, String name,
<<<<<<< HEAD
                               String hp, String email,Status status, String shoeSize,Long point, String profileMemo, String imgUrl, String bank, String accountNumber){
        return new MemberDTO(idx, nickname, memberPw, name, hp, email,status, shoeSize,point, profileMemo, imgUrl, bank, accountNumber);
    }
    public static MemberDTO of(String nickname, String memberPw, String name,
                               String hp, String email,String shoeSize){
        return new MemberDTO(null, nickname, memberPw, name, hp, email,Status.MEMBER, shoeSize,0L, null, null, null, null);
=======
                               String hp, String email, String shoeSize, String profileMemo, String imgUrl, String bank, String accountNumber){
        return new MemberDTO(idx, nickname, memberPw, name, hp, email,null, shoeSize,null, profileMemo, imgUrl, bank, accountNumber);
>>>>>>> 24b8aaa4effea2890eea3af44860f17ab88ae5ab
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
                member.getAccountNumber()
        );
    }

    public Member toEntity(){
        return Member.of(idx, nickname, memberPw, name, hp,
                email, shoeSize, profileMemo, imgUrl, bank, accountNumber);
    }
}
