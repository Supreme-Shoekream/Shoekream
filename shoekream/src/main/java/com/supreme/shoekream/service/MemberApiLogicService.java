package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.AddressDTO;
import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.entity.*;
import com.supreme.shoekream.model.enumclass.Status;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.MemberApiRequest;
import com.supreme.shoekream.model.network.response.MemberApiResponse;
import com.supreme.shoekream.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberApiLogicService extends BaseService<MemberApiRequest, MemberApiResponse, Member> {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;
    private final PointRepository pointRepository;
    private final ReplyRepository replyRepository;
    private final SellRepository sellRepository;
    private final BuyRepository buyRepository;
    private final WishRepository wishRepository;
    private final BoardRepository boardRepository;
    private final BlacklistRepository blacklistRepository;
    private final PenaltyRepository penaltyRepository;
    private final LikeRepository likeRepository;
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
        if(memberDTO.imgUrl() == null && memberDTO.nickname() == null){
            memberDTO = MemberDTO.of(
                    memberDTO.idx(), memberDTO.email().split("@")[0], memberDTO.memberPw(),
                    memberDTO.name(), memberDTO.hp(), memberDTO.email(),
                    memberDTO.status(), memberDTO.shoeSize(), 0L,
                    "", "/img/kream_empty_img.png", memberDTO.bank(), memberDTO.accNumber()
            );
        }
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
    public Boolean point( Long memberidx){
        Member member = memberRepository.findById(memberidx).get();
        if(member.getPoint().intValue() >= 1000){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Header<MemberApiResponse> read(Long idx) {
        return memberRepository.findById(idx).map(members -> response(members))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public MemberDTO readProfile(Long idx){
        Member member = memberRepository.findByIdx(idx);
        String pwNum = "";
        for(int i=0; i<member.getMemberPw().substring(6).length();i++){pwNum += "•";}
        MemberDTO dto = MemberDTO.of(member.getNickname(),pwNum,member.getName(),
                member.getHp(),member.getEmail(),member.getShoeSize(), member.getImgUrl());
        return dto;
    }

    public Header<MemberApiResponse> read(String email, String memberPw) {
        return memberRepository.findByEmailAndMemberPw(email,memberPw).map(
                        member -> response(member)).map(Header::OK)
                .orElseGet(()-> Header.ERROR("이메일 또는 비밀번호가 틀렸습니다.")
                );
    }

    @Transactional
    public MemberDTO list(Long idx){
        Member member = memberRepository.findByIdx(idx);
        MemberDTO memberDTO = MemberDTO.fromEntity(member);
        return memberDTO;
    }

    @Transactional
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

    public Header<MemberApiResponse> updateProfile(MemberDTO dto, Long idx){
        Optional<Member> member = memberRepository.findById(idx);
        Member newMember = member.get();
        if(dto.email() != null) newMember.setEmail(dto.email());
        if(dto.memberPw() != null) newMember.setMemberPw("{noop}"+dto.memberPw());
        if(dto.nickname() != null) newMember.setNickname(dto.nickname());
        if(dto.hp() != null) newMember.setHp(dto.hp());
        if(dto.shoeSize() != null) newMember.setShoeSize(dto.shoeSize());
        if(dto.name() != null) newMember.setName(dto.name());
        return Header.OK();
    }

    public Header<MemberApiResponse> updateProfileImg(MemberDTO dto, Long idx){
        Optional<Member> member = memberRepository.findById(idx);
        Member newMember = member.get();
        if(dto.imgUrl() != null) newMember.setImgUrl(dto.imgUrl());
        return Header.OK();
    }
    @Override
    public Header delete(Long idx) {
        // 주소삭제
        addressRepository.deleteByMemberIdx(idx);
        // 카드삭제
        cardRepository.deleteByMemberIdx(idx);
        // 포인트삭제
        pointRepository.deleteByMemberIdx(idx);
        // 댓글삭제
        replyRepository.deleteByMemberIdx(idx);
        // 구매삭제
        buyRepository.deleteByMemberIdx(idx);
        // 판매삭제
        sellRepository.deleteByMemberIdx(idx);
        // 관심상품삭제
        wishRepository.deleteByMemberIdx(idx);
        // 블랙리스트삭제
        blacklistRepository.deleteByMemberIdx(idx);
        // 게시판삭제
        boardRepository.deleteByMemberIdx(idx);
        // 좋아요삭제
        likeRepository.deleteByMemberIdx(idx);

        // 멤버삭제
        Optional<Member> members = baseRepository.findById(idx);
        return members.map(member->{
            baseRepository.delete(member);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
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

    public MemberDTO saveUser(String nickname, String password, String name, String hp, String email, Status status, String shoeSize, String imgUrl) {
        return MemberDTO.fromEntity(
                memberRepository.save(Member.kakaoof(nickname, password, name, hp, email, status, shoeSize, imgUrl))
        );
    }

    public boolean userEmailCheck(String email, String name) {

        Optional<Member> member = memberRepository.findByEmail(email);
        if(member!=null && member.get().getName().equals(name)) {
            return true;
        }
        else {
            return false;
        }
    }
    public String userHpCheck(String hp) {

        Optional<Member> member = memberRepository.findByHp(hp);
        if(member.orElse(null)!=null && member.get().getHp().equals(hp)) {
            return member.get().getEmail();
        }
        else {
            return null;
        }
    }

    public Header<MemberApiResponse> deleteProfile(Long idx){
        Member member = memberRepository.findByIdx(idx);
        member.setImgUrl("/img/profile.png");
        return Header.OK();
    }
}


