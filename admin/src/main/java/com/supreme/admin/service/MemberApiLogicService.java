package com.supreme.admin.service;

import com.supreme.admin.model.dto.AddressDTO;
import com.supreme.admin.model.dto.BuyDTO;
import com.supreme.admin.model.dto.MemberDTO;
import com.supreme.admin.model.entity.*;
import com.supreme.admin.model.enumclass.OrderStatus;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.Pagination;
import com.supreme.admin.model.network.request.MemberApiRequest;
import com.supreme.admin.model.network.response.MemberApiResponse;
import com.supreme.admin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberApiLogicService extends BaseService<MemberApiRequest, MemberApiResponse, Member> {
    private final MemberRepository memberRepository;
    private MemberApiResponse response(Member member){
        MemberApiResponse memberApiResponse = MemberApiResponse.builder()
                .idx(member.getIdx())
                .nickname(member.getNickname())
                .memberPw(member.getMemberPw())
                .name(member.getName())
                .hp(member.getHp())
                .email(member.getEmail())
                .status(member.getStatus())
                .shoeSize(member.getShoeSize())
                .point(member.getPoint())
                .profileMemo(member.getProfileMemo())
                .imgUrl(member.getImgUrl())
                .bank(member.getBank())
                .accountNumber(member.getAccNumber())
                .build();
        return memberApiResponse;
    }

    @Override
    public Header<MemberApiResponse> create(Header<MemberApiRequest> request) {
        MemberApiRequest memberApiRequest = request.getData();
        MemberDTO memberDTO = memberApiRequest.toDTO();
//        Member member = Member.builder()
//                .memberPw(memberDTO.memberPw())
//                .name(memberDTO.name())
//                .hp(memberDTO.hp())
//                .email(memberDTO.email())
//                .shoeSize(memberDTO.shoeSize())
//                .build();
        Member newMember = memberRepository.save(memberDTO.toEntity());

        //memberRepository.save(Member.of(password, name, hp, email, shoeSize))
        return Header.OK(response(newMember));
    }
//@Override
//public Header<MemberApiResponse> create(Header<MemberApiRequest> request) {
//    MemberApiRequest memberApiRequest = request.getData();
//    Member member = Member.builder()
//            .memberPw(memberApiRequest.memberPw())
//            .name(memberApiRequest.name())
//            .hp(memberApiRequest.hp())
//            .email(memberApiRequest.email())
//            .shoeSize(memberApiRequest.shoeSize())
//            .build();
//    Member newMember = memberRepository.save(member);
//    return Header.OK(response(newMember));
//}

    @Override
    public Header<MemberApiResponse> read(Long idx) {
        return memberRepository.findById(idx).map(members -> response(members))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public MemberDTO read2(Long idx){
        Member member = memberRepository.findByIdx(idx);
        return MemberDTO.fromEntity(member);
    }

    public Header<MemberApiResponse> read(String email, String memberPw) {
        return memberRepository.findByEmailAndMemberPw(email,memberPw).map(
                        member -> response(member)).map(Header::OK)
                .orElseGet(()-> Header.ERROR("이메일 또는 비밀번호가 틀렸습니다.")
                );
    }

    public MemberDTO readPoint(Long idx){
        Member member = memberRepository.findByIdx(idx);
        MemberDTO memberDTO = MemberDTO.fromEntity(member);
        return memberDTO;
    }

    @Override
    public Header<MemberApiResponse> update(Header<MemberApiRequest> request) {
//        MemberApiRequest memberApiRequest = request.getData();
//        Optional<Member> members = memberRepository.findByMemberId(memberApiRequest.getNickname());
//        return members.map(
//                        member -> {
//                            member.setName(memberApiRequest.getName());
//                            member.setHp(memberApiRequest.getHp());
//                            return member;
//                        }).map(member -> memberRepository.save(member))
//                .map(member -> response(member))
//                .map(Header::OK)
//                .orElseGet(()->Header.ERROR("데이터 없음")
//                );
        return null;
    }

    public Header<MemberApiResponse> updateAccount(MemberDTO memberDTO, Long idx){
        Optional<Member> member = memberRepository.findById(idx);
        return member.map(
                newMember -> {
                    newMember.setBank(memberDTO.bank());
                    newMember.setAccNumber(memberDTO.accNumber());
                    return newMember;
                }).map(newMember -> memberRepository.save(newMember)).map(
                newMember -> response(newMember)).map(Header::OK).orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long idx) {
//        Optional<Member> members = baseRepository.findById(idx);
//        return members.map(member->{
//            baseRepository.delete(member);
//            return Header.OK();
//        }).orElseGet(()->Header.ERROR("데이터 없음"));

        return null;
    }

    public Header<MemberApiResponse> login(Header<MemberApiRequest> request){
        MemberApiRequest memberApiRequest = request.getData();
        MemberDTO memberDTO = memberApiRequest.toDTO();
        Optional<Member> member = memberRepository.findByEmailAndMemberPw(
                memberDTO.email(),memberDTO.memberPw()
        );
        if (!member.isEmpty()){
            return Header.OK();
        }
        return Header.ERROR("이메일 또는 비밀번호가 틀렸음!");
    }

    public Header<List<MemberApiResponse>> search(Pageable pageable){
        Page<Member> members = memberRepository.findAll(pageable);
        List<MemberApiResponse> memberApiResponses = members.stream().map(
                member -> response(member)).collect(Collectors.toList());
        // collect: 특정 자료 구조 형태에 데이터를 담아달라는 뜻
        // Collectors.toList(): 리스트형식
        Pagination pagination = Pagination.builder()
                .totalPages(members.getTotalPages())
                .totalElements(members.getTotalElements())
                .currentPage(members.getNumber())
                .currentElements(members.getNumberOfElements())
                .build();
        return Header.OK(memberApiResponses, pagination);
    }

    @Transactional(readOnly = true)
    public Optional<MemberDTO> searchUser(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberDTO::fromEntity);
    }

    public MemberDTO saveUser(String password, String name, String hp, String email, String shoeSize) {
        return MemberDTO.fromEntity(
                memberRepository.save(Member.of(password, name, hp, email, shoeSize))
        );
    }
    public Page<MemberDTO> searchMember(String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return memberRepository.findAll(pageable).map(MemberDTO::fromEntity);
        }
        return memberRepository.findByEmailContaining(searchKeyword,pageable).map(MemberDTO::fromEntity);
    }
}


