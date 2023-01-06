package com.supreme.shoekream.service;

import com.supreme.shoekream.model.config.BaseEntity;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberApiLogicService extends BaseService<MemberApiRequest, MemberApiResponse, Member> {
    private final MemberRepository memberRepository;
    private MemberApiResponse response(Member member){
        MemberApiResponse memberApiResponse = MemberApiResponse.builder()
                .idx(member.getIdx())
                .memberId(member.getMemberId())
                .memberPw(member.getMemberPw())
                .name(member.getName())
                .hp(member.getHp())
                .email(member.getEmail())
                .status(member.getStatus())
                .shoeSize(member.getShoeSize())
                .point(member.getPoint())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .gender(member.getGender())
                .birthDate(member.getBirthDate())
                .profileMemo(member.getProfileMemo())
                .imgUrl(member.getImgUrl())
                .build();
        return memberApiResponse;
    }
    @Override
    public Header<MemberApiResponse> create(Header<MemberApiRequest> request) {
        MemberApiRequest memberApiRequest = request.getData();
//        int gender =memberApiRequest.getGender()

        Member member = Member.builder()
                .memberId(memberApiRequest.getMemberId())
                .memberPw(memberApiRequest.getMemberPw())
                .name(memberApiRequest.getName())
                .hp(memberApiRequest.getHp())
                .email(memberApiRequest.getEmail())
                .shoeSize(memberApiRequest.getShoeSize())
                .birthDate(memberApiRequest.getBirthDate())
//                .gender(memberApiRequest.getGender())
                .build();
        Member newMembers = baseRepository.save(member);
        return Header.OK(response(newMembers));
    }

    @Override
    public Header<MemberApiResponse> read(Long idx) {
        return baseRepository.findById(idx).map(members -> response(members))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터 없음"));
    }
    public Header<MemberApiResponse> read(String memberId, String memberPw) {
        return memberRepository.findByMemberIdAndMemberPw(memberId,memberPw).map(
                        member -> response(member)).map(Header::OK)
                .orElseGet(()-> Header.ERROR("아이디 또는 비밀번호가 틀렸습니다.")
                );
    }

    @Override
    public Header<MemberApiResponse> update(Header<MemberApiRequest> request) {
        MemberApiRequest memberApiRequest = request.getData();
        Optional<Member> members = memberRepository.findByMemberId(memberApiRequest.getMemberId());
        return members.map(
                        member -> {
                            member.setName(memberApiRequest.getName());
                            member.setHp(memberApiRequest.getHp());
                            return member;
                        }).map(member -> memberRepository.save(member))
                .map(member -> response(member))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header delete(Long idx) {
        Optional<Member> members = baseRepository.findById(idx);
        return members.map(member->{
            baseRepository.delete(member);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<MemberApiResponse> login(Header<MemberApiRequest> request){
        MemberApiRequest memberUserApiRequest = request.getData();
        Optional<Member> memberUser =memberRepository.findByMemberIdAndMemberPw(
                memberUserApiRequest.getMemberId(),memberUserApiRequest.getMemberPw()
        );
        if (!memberUser.isEmpty()){
            return Header.OK();
        }
        return Header.ERROR("아이디 또는 비밀번호가 틀렸음!");
    }

}


