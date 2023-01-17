//package com.supreme.shoekream.model.dto;
//
//import com.supreme.shoekream.model.entity.Blacklist;
//import com.supreme.shoekream.model.entity.Member;
//import com.supreme.shoekream.model.entity.Penalty;
//import com.supreme.shoekream.model.enumclass.Reason;
//
//import java.time.LocalDateTime;
//
//import static com.supreme.shoekream.model.entity.QPenalty.penalty;
//
//public record PenaltyDTO(
//        Long idx,
//        Reason reason,
//        Long memberIdx,
//        Member member,
//        Long productIdx,
//        LocalDateTime createdAt
//) {
//    public static PenaltyDTO of(Long idx, Reason reason, Long memberIdx, Member member, Long productIdx, LocalDateTime createdAt){
//        return new PenaltyDTO(idx, reason, memberIdx, member, productIdx, createdAt);
//    }
//
//    public static PenaltyDTO fromEntity(Penalty penalty){
//        return new PenaltyDTO(
//                penalty.getIdx(),
//                penalty.getReason(),
//                penalty.getMemberIdx(),
//                penalty.getMember(),
//                penalty.getProductIdx(),
//                penalty.getCreatedAt()
//        );
//    }
//
//    public Penalty toEntity(){
//        return Penalty.of(idx, reason, memberIdx, member, productIdx, createdAt);
//    }
//}
